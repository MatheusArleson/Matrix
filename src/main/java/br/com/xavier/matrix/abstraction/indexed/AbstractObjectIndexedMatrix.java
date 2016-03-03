package br.com.xavier.matrix.abstraction.indexed;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.xavier.matrix.abstraction.AbstractMatrix;
import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.interfaces.indexed.ObjectIndexedMatrix;
import br.com.xavier.matrix.util.messages.IndexAwareSet;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;
import br.com.xavier.matrix.validation.NullValidator;
import br.com.xavier.matrix.validation.ObjectIndexValidator;

public abstract class AbstractObjectIndexedMatrix<O, M extends AbstractMatrix<T>, T> implements ObjectIndexedMatrix<O, M, T>{
	
	//XXX CLASS PROPERTIES
	private Class<M> matrixClass;
	private M matrix;
	
	private IndexAwareSet<O> rowsObjectsSet;
	private IndexAwareSet<O> columnsObjectsSet;
	
	//XXX CONSTRUCTOR
	public AbstractObjectIndexedMatrix() throws Exception {
		getMatrixClass();
		getMatrixInstance();
		clearInternalStructure();
	}
	
	public AbstractObjectIndexedMatrix(LinkedHashSet<O> rowsSet, LinkedHashSet<O> columnsSet, M matrix) throws Exception {
		validateContext(matrix, rowsSet, columnsSet);
		
		this.matrix = matrix;
		this.rowsObjectsSet = new IndexAwareSet<O>(rowsSet);
		this.columnsObjectsSet = new IndexAwareSet<O>(columnsSet);
	}

	@SuppressWarnings("unchecked")
	private void getMatrixClass(){
		if(matrixClass != null){
			return;
		}
		
		Type genericSuperclass = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		
		Type matrixType = actualTypeArguments[1];
		ParameterizedType parameterizedMatrixType = (ParameterizedType)matrixType;
		
		Type matrixRawType = parameterizedMatrixType.getRawType();	
		Class<M> persistentClass = (Class<M>) matrixRawType;
		
		this.matrixClass = persistentClass;
	}
	
	private void getMatrixInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		this.matrix = (M) matrixClass.getConstructor(int.class, int.class).newInstance(0,0);
	}

	private void clearInternalStructure() {
		matrix.clear();
		this.rowsObjectsSet = new IndexAwareSet<O>(new LinkedHashSet<O>());
		this.columnsObjectsSet = new IndexAwareSet<O>(new LinkedHashSet<O>());
	}
	
	private void validateContext(M matrix, LinkedHashSet<O> rowsSet, LinkedHashSet<O> columnsSet) {
		NullValidator.checkNullParameter(rowsSet, columnsSet, matrix);
		
		int objRowCount = rowsSet.size();
		int objColumnCount = columnsSet.size();
		int matrixRowCount = matrix.getRowCount();
		int matrixColumnCount = matrix.getColumnCount();
		
		boolean isRowCountDifferent = objRowCount != matrixRowCount;
		boolean isColumnCountDifferent = objColumnCount != matrixColumnCount;
		
		if(isRowCountDifferent || isColumnCountDifferent){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
	}

	//XXX OPERATION METHODS
	
	@Override
	public void clear() {
		clearInternalStructure();
	}
	
	@Override
	public T get(O columnObj, O rowObj) {
		ObjectIndexValidator.checkIllegalColumnObjectIndex(this, columnObj);
		ObjectIndexValidator.checkIllegalRowObjectIndex(this, rowObj);
		
		int column = getMatrixColumnNumber(columnObj);
		int row = getMatrixRowNumber(rowObj);
		
		T value = matrix.get(column, row);
		return value;
	}

	@Override
	public T set(O columnObj, O rowObj, T value) {
		ObjectIndexValidator.checkIllegalColumnObjectIndex(this, columnObj);
		ObjectIndexValidator.checkIllegalRowObjectIndex(this, rowObj);
		
		if(value == null && representsEmpty() != null){
			throw new InvalidMatrixRepresentation();
		}
		
		int column = getMatrixColumnNumber(columnObj);
		int row = getMatrixRowNumber(rowObj);
		
		T oldValue = matrix.set(column, row, value);
		return oldValue;
	}

	@Override
	public boolean checkEmpty(T obj) {
		if(obj == null){
			return ( representsEmpty() == null );
		}
		
		return obj.equals(representsEmpty());
	}

	//ROW METHODS
	
	public Set<O> getRowsObjects() {
		return Collections.unmodifiableSet(rowsObjectsSet);
	}
	
	@Override
	public boolean isRowObject(O obj){
		NullValidator.checkNullParameter(obj);
		
		if(rowsObjectsSet.isEmpty()){
			return false;
		}
		
		return rowsObjectsSet.contains(obj);
	}
	
	public boolean isValidForRow(O obj){
		return !isRowObject(obj);
	}
	
	public int getMatrixRowNumber(O obj){
		ObjectIndexValidator.checkIllegalRowObjectIndex(this, obj);
		return rowsObjectsSet.getIndex(obj); 
	}
	
	@Override
	public void addRow(O rowObj) {
		NullValidator.checkNullParameter(rowObj);
		
		boolean isValidForRow = isValidForRow(rowObj);
		if(!isValidForRow){
			return;
		}
		
		rowsObjectsSet.add(rowObj);
		matrix.addRow();
	}
	
	@Override
	public void removeRow(O rowObj) {
		ObjectIndexValidator.checkIllegalRowObjectIndex(this, rowObj);
		
		int matrixRowNumber = getMatrixRowNumber(rowObj);
		matrix.removeRow(matrixRowNumber);
		rowsObjectsSet.remove(rowObj);
	}
	
	@Override
	public int getRowCount() {
		return rowsObjectsSet.size();
	}
	
	//COLUMN METHODS
	
	public Set<O> getColumnsObjects() {
		return Collections.unmodifiableSet(columnsObjectsSet);
	}
	
	public boolean isColumnObject(O obj){
		NullValidator.checkNullParameter(obj);
		
		if(columnsObjectsSet.isEmpty()){
			return false;
		}
		
		return columnsObjectsSet.contains(obj);
	}
	
	public boolean isValidForColumn(O obj){
		return !isColumnObject(obj);
	}

	public int getMatrixColumnNumber(O obj){
		ObjectIndexValidator.checkIllegalColumnObjectIndex(this, obj);
		return columnsObjectsSet.getIndex(obj); 
	}

	@Override
	public void addColumn(O columnObj) {
		NullValidator.checkNullParameter(columnObj);
		
		if(!isValidForColumn(columnObj)){
			return;
		}
		
		columnsObjectsSet.add(columnObj);
		matrix.addColumn();
	}
	
	@Override
	public void removeColumn(O columnObj) {
		ObjectIndexValidator.checkIllegalColumnObjectIndex(this, columnObj);
		
		int matrixColumnNumber = getMatrixColumnNumber(columnObj);
		matrix.removeColumn(matrixColumnNumber);
		columnsObjectsSet.remove(columnObj);
	}
	
	@Override
	public int getColumnCount() {
		return columnsObjectsSet.size();
	}
	
	//CLOUMN AND ROW METHODS
	
	@Override
	public void addColumAndRow(O colRowObj) {
		NullValidator.checkNullParameter(colRowObj);
		
		rowsObjectsSet.add(colRowObj);
		columnsObjectsSet.add(colRowObj);
		matrix.addColumAndRow();
	}

	@Override
	public void removeColumAndRow(O colRowObj) {
		ObjectIndexValidator.checkIllegalColumnRowObjectIndex(this, colRowObj);		
		
		int index = (getRowCount() > getColumnCount()) ? getMatrixRowNumber(colRowObj) : getMatrixColumnNumber(colRowObj);
		
		rowsObjectsSet.remove(colRowObj);
		columnsObjectsSet.remove(colRowObj);
		matrix.removeColumAndRow(index);
	}
	
}
