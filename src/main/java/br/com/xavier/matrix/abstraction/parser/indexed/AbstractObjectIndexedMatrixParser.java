package br.com.xavier.matrix.abstraction.parser.indexed;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import br.com.xavier.matrix.abstraction.parser.AbstractParser;
import br.com.xavier.matrix.exception.InvalidMatrixRepresentationDelimiter;
import br.com.xavier.matrix.impl.DefaultMatrix;
import br.com.xavier.matrix.impl.indexed.DefaultObjectIndexedMatrix;
import br.com.xavier.matrix.interfaces.indexed.ObjectIndexedMatrix;
import br.com.xavier.matrix.interfaces.parser.indexed.ObjectIndexedMatrixParser;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;
import br.com.xavier.matrix.validation.StringValidator;

public abstract class AbstractObjectIndexedMatrixParser<O,T> extends AbstractParser implements ObjectIndexedMatrixParser<O, T> {
	
	private final String rowsObjectsRepresentationStartDelimiter;
	private final String rowsObjectsRepresentationEndDelimiter;
	private final String columnsObjectsRepresentationStartDelimiter;
	private final String columnsObjectsRepresentationEndDelimiter;
	
	public AbstractObjectIndexedMatrixParser(
		String representationStartDelimiter, 
		String representationEndDelimiter,
		String matrixRepresentatitionStartDelimiter, 
		String matrixRepresentatitionEndDelimiter,
		String matrixRepresentationRowSeparator, 
		String matrixRepresentationRowElementsSeparator,
		String rowsObjectsRepresentationStartDelimiter,
		String rowsObjectsRepresentationEndDelimiter,
		String columnsObjectsRepresentationStartDelimiter,
		String columnsObjectsRepresentationEndDelimiter
	) {
		super(
			representationStartDelimiter, 
			representationEndDelimiter, 
			matrixRepresentatitionStartDelimiter,
			matrixRepresentatitionEndDelimiter, 
			matrixRepresentationRowSeparator, 
			matrixRepresentationRowElementsSeparator
		);
		
		boolean isInvalid = StringValidator.anyNullOrEmpty(
			rowsObjectsRepresentationStartDelimiter, 
			rowsObjectsRepresentationEndDelimiter, 
			columnsObjectsRepresentationStartDelimiter,
			columnsObjectsRepresentationEndDelimiter
		);
		
		if(isInvalid){
			throw new InvalidMatrixRepresentationDelimiter();
		}
		
		this.rowsObjectsRepresentationStartDelimiter = rowsObjectsRepresentationStartDelimiter;
		this.rowsObjectsRepresentationEndDelimiter = rowsObjectsRepresentationEndDelimiter;
		this.columnsObjectsRepresentationStartDelimiter = columnsObjectsRepresentationStartDelimiter;
		this.columnsObjectsRepresentationEndDelimiter = columnsObjectsRepresentationEndDelimiter;
	}
	

	@Override
	public String toString(ObjectIndexedMatrix<O, T> matrix) {
		if(matrix == null){
			return MessageManager.getDefaultMessage(DefaultMessagesKey.EMPTY_MATRIX);
		}
		
		T empty = matrix.representsEmpty();
		String emptyRepresentation = (empty == null ? "null" : empty.toString());
		
		Set<O> rowsObjects = matrix.getRowsObjects();
		Set<O> columnsObjects = matrix.getColumnsObjects();
		
		String matrixRepresentation = getMatrixString(matrix, emptyRepresentation, rowsObjects, columnsObjects);
		
		
		
		String rowsRepresentation = getObjectRowsString(rowsObjects);
		String columnsRepresentation = getObjectColumnsString(rowsObjects);
		
		StringBuffer sb = new StringBuffer();
		
		return matrixRepresentation;
	}

	private String getObjectRowsString(Set<O> rowsObjects) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getObjectColumnsString(Set<O> rowsObjects) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getMatrixString(ObjectIndexedMatrix<O, T> matrix, String emptyRepresentation, Set<O> rowsObjects, Set<O> columnsObjects) {
		StringBuilder sb = new StringBuilder();
		sb.append(getRepresentationStartDelimiter());
		
		
		for (O rowObj : rowsObjects) {
			for (O columnObj : columnsObjects) {
				
				T matrixElement = matrix.get(columnObj, rowObj);
				
				boolean isEmpty = matrix.checkEmpty(matrixElement);
				
				sb.append(isEmpty ? emptyRepresentation : matrixElement.toString());
				sb.append(getRowElementsSeparator());
			}
			
			sb.append(getRowSeparator());
		}
		
		String matrixRepresentation = sb.substring(0, sb.length() - 2);
		matrixRepresentation = matrixRepresentation + getRowSeparator() + getEndDelimiter();
		
		
		sb.append(getRepresentationEndDelimiter());
		return sb.toString();
	}
	
	@Override
	public ObjectIndexedMatrix<O, T> fromString(String matrixString) {
		validate(matrixString);
		matrixString = strip(matrixString, getStartDelimiter(), getEndDelimiter());
		
		if(matrixString.isEmpty()){
			return new DefaultObjectIndexedMatrix<O,T>();
		}
		
		String[] lines = matrixString.split(getRowSeparator());
		int rows = lines.length;
		int columns = 0;
		
		Map<Integer, ArrayList<T>> map = new LinkedHashMap<Integer, ArrayList<T>>();
		for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
			String line = lines[lineNumber];
			if(line.endsWith(getRowElementsSeparator())){
				line = line.substring(0, line.length() - 1);
			}
			
			String[] elements = line.split(getRowElementsSeparator());
			if(elements.length > columns){
				columns = elements.length;
			}
			
			map.put(lineNumber, new ArrayList<T>());
			
			for (String element : elements) {
				T obj = (T) element;
				map.get(lineNumber).add(obj);
			}
		}
		
		DefaultMatrix<T> dm = new DefaultMatrix<T>(columns, rows);
		
		for (Integer rowNumber : map.keySet()) {
			ArrayList<T> rowElements = map.get(rowNumber);
			for (int columnNumber = 0; columnNumber < rowElements.size(); columnNumber++) {
				dm.set(columnNumber, rowNumber, rowElements.get(columnNumber));
			}
		}
		
		if(dm.representsEmpty() != null){
			for (int col = 0; col < dm.getColumnCount(); col++) {
				for (int row = 0; row < dm.getRowCount(); row++) {
					boolean isNull = (dm.get(col, row) == null);
					if(isNull){
						dm.set(col, row, dm.representsEmpty());
					}
				}
			}
		}
		
		return dm;
	}
	
	//XXX GETTERS
	
	public String getColumnsObjectsRepresentationStartDelimiter() {
		return columnsObjectsRepresentationStartDelimiter;
	}
	
	public String getColumnsObjectsRepresentationEndDelimiter() {
		return columnsObjectsRepresentationEndDelimiter;
	}
	
	public String getRowsObjectsRepresentationStartDelimiter() {
		return rowsObjectsRepresentationStartDelimiter;
	}
	
	public String getRowsObjectsRepresentationEndDelimiter() {
		return rowsObjectsRepresentationEndDelimiter;
	}
}
