package br.com.xavier.matrix.abstraction;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.xavier.matrix.validation.NullValidator;

public abstract class ObjectIndexedMatrix<O, M extends AbstractMatrix<T>, T> {
	
	//XXX CLASS PROPERTIES
	private Class<M> matrixClass;
	private M matrix;
	
	private List<O> rowsObjectsList;
	private List<O> columnsObjectsList;
	
	//XXX CONSTRUCTOR
	public ObjectIndexedMatrix() throws Exception{
		getMatrixClass();
        getMatrixInstance();
        
		this.rowsObjectsList = new ArrayList<O>();
		this.columnsObjectsList = new ArrayList<O>();
	}

	@SuppressWarnings("unchecked")
	private void getMatrixClass() throws ClassNotFoundException{
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
	
	private void getMatrixInstance() throws Exception {
		this.matrix = (M) matrixClass.getConstructor(int.class, int.class).newInstance(0,0);
	}

	//XXX CHECK METHODS
	
	//ROW METHODS
	
	public boolean isRowObject(O obj){
		NullValidator.checkNullParameter(obj);
		
		if(rowsObjectsList.isEmpty()){
			return false;
		}
		
		return rowsObjectsList.contains(obj);
	}
	
	public boolean isValidForRow(O obj){
		return !isRowObject(obj);
	}
	
	//COLUMN METHODS
	
	public boolean isColumnObject(O obj){
		NullValidator.checkNullParameter(obj);
		
		if(columnsObjectsList.isEmpty()){
			return false;
		}
		
		return columnsObjectsList.contains(obj);
	}
	
	public boolean isValidForColumn(O obj){
		return !isColumnObject(obj);
	}
	
}
