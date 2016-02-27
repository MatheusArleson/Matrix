package br.com.xavier.matrix.abstraction.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.exception.InvalidMatrixRepresentationDelimiter;
import br.com.xavier.matrix.impl.DefaultMatrix;
import br.com.xavier.matrix.interfaces.Matrix;
import br.com.xavier.matrix.interfaces.parser.MatrixParser;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;
import br.com.xavier.matrix.validation.StringValidator;

public abstract class AbstractMatrixParser<T> implements MatrixParser<T> {
	
	private String startDelimiter; 
	private String endDelimiter;
	private String rowSeparator; 
	private String rowElementsSeparator;
	
	public AbstractMatrixParser(
		String startDelimiter, 
		String endDelimiter, 
		String rowSeparator,
		String rowElementsSeparator
	) {
		super();
		
		boolean isInvalid = StringValidator.anyNullOrEmpty(
			startDelimiter, endDelimiter, rowSeparator, rowElementsSeparator
		);
		
		if(isInvalid){
			throw new InvalidMatrixRepresentationDelimiter();
		}
		
		this.startDelimiter = startDelimiter;
		this.endDelimiter = endDelimiter;
		this.rowSeparator = rowSeparator;
		this.rowElementsSeparator = rowElementsSeparator;
	}

	@Override
	public String toString(Matrix<T> matrix) {
		if(matrix == null){
			return MessageManager.getDefaultMessage(DefaultMessagesKey.EMPTY_MATRIX);
		}
		
		T empty = matrix.representsEmpty();
		String emptyRepresentation = (empty == null ? "null" : empty.toString());
		
		int columns = matrix.getColumnCount();
		int rows = matrix.getRowCount();
		
		StringBuilder sb = new StringBuilder(rows * (columns + 1));
		sb.append(startDelimiter + rowSeparator);
		for (int rowCount = 0; rowCount < rows; rowCount++) {
			for (int columnCount = 0; columnCount < columns; columnCount++) {
				boolean isEmpty = matrix.checkEmpty(matrix.get(columnCount, rowCount));
				sb.append(isEmpty ? emptyRepresentation : matrix.get(columnCount, rowCount).toString());
				sb.append(rowElementsSeparator);
			}
			sb.append(rowSeparator);
		}
		String result = sb.substring(0, sb.length() - 2);
		result = result + rowSeparator + endDelimiter;
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public DefaultMatrix<T> fromString(String matrixString) throws IOException, InvalidMatrixRepresentation {
		
		validate(matrixString);
		matrixString = strip(matrixString, startDelimiter, endDelimiter);
		
		if(matrixString.isEmpty()){
			return new DefaultMatrix<T>(0, 0);
		}
		
		String[] lines = matrixString.split(rowSeparator);
		int rows = lines.length;
		int columns = 0;
		
		Map<Integer, ArrayList<T>> map = new LinkedHashMap<Integer, ArrayList<T>>();
		for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
			String line = lines[lineNumber];
			if(line.endsWith(rowElementsSeparator)){
				line = line.substring(0, line.length() - 1);
			}
			
			String[] elements = line.split(rowElementsSeparator);
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
	
	private void validate(String matrixString) {
		if(StringValidator.isNullOrEmpty(matrixString)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!matrixString.startsWith(startDelimiter)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!matrixString.endsWith(endDelimiter)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		boolean isEmptyMatrix = matrixString.length() == startDelimiter.length() + endDelimiter.length();
		if(!isEmptyMatrix){
			if(!matrixString.contains(rowSeparator)){
				throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
			}
			
			if(!matrixString.contains(rowElementsSeparator)){
				throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
			}			
		}
	}
	
	private String strip(String matrixString, String startDelimiter, String endDelimiter){
		//removes the start and end delimiters
		int indexOfStartDelimiter = matrixString.indexOf(startDelimiter) + startDelimiter.length();
		int indexOfEndDelimiter = matrixString.indexOf(endDelimiter);
		matrixString = matrixString.substring(indexOfStartDelimiter, indexOfEndDelimiter);
		
		return matrixString;
	}
}
