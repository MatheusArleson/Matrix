package br.com.xavier.matrix.impl.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.impl.DefaultMatrix;
import br.com.xavier.matrix.interfaces.Matrix;
import br.com.xavier.matrix.interfaces.parser.MatrixParser;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public class DefaultMatrixParser<G> implements MatrixParser<G> {
	
	//XXX CONSTRUCTOR
	public DefaultMatrixParser() {}
	
	@Override
	public String toString(Matrix<G> matrix) {
		if(matrix == null){
			return MessageManager.getDefaultMessage(DefaultMessagesKey.EMPTY_MATRIX);
		}
		
		G empty = matrix.representsEmpty();
		String emptyRepresentation = (empty == null ? "null" : empty.toString());
		
		int columns = matrix.getColumnCount();
		int rows = matrix.getRowCount();
		
		StringBuilder sb = new StringBuilder(rows * (columns + 1));
		sb.append("[\n");
		for (int rowCount = 0; rowCount < rows; rowCount++) {
			for (int columnCount = 0; columnCount < columns; columnCount++) {
				boolean isEmpty = matrix.checkEmpty(matrix.get(columnCount, rowCount));
				sb.append(isEmpty ? emptyRepresentation : matrix.get(columnCount, rowCount).toString());
				sb.append(",");
			}
			sb.append("\n");
		}
		String result = sb.substring(0, sb.length() - 2);
		result = result + "\n]";
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public DefaultMatrix<G> fromString(
		String matrixString, String startDelimiter, String endDelimiter, 
		String rowSeparator, String rowElementsSeparator
	) throws IOException, InvalidMatrixRepresentation {
		
		validate(matrixString, startDelimiter, endDelimiter, rowSeparator, rowElementsSeparator);
		matrixString = strip(matrixString, startDelimiter, endDelimiter);
		
		String[] lines = matrixString.split(rowSeparator);
		int rows = lines.length;
		int columns = 0;
		
		Map<Integer, ArrayList<G>> map = new LinkedHashMap<Integer, ArrayList<G>>();
		for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
			String line = lines[lineNumber];
			if(line.endsWith(rowElementsSeparator)){
				line = line.substring(0, line.length() - 1);
			}
			
			String[] elements = line.split(rowElementsSeparator);
			if(elements.length > columns){
				columns = elements.length;
			}
			
			map.put(lineNumber, new ArrayList<G>());
			
			for (String element : elements) {
				G obj = (G) element;
				map.get(lineNumber).add(obj);
			}
		}
		
		DefaultMatrix<G> dm = new DefaultMatrix<G>(columns, rows);
		
		for (Integer rowNumber : map.keySet()) {
			ArrayList<G> rowElements = map.get(rowNumber);
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

	private void validate(String matrixString, String startDelimiter, String endDelimiter, String rowSeparator, String rowElementsSeparator) {
		if(matrixString == null || matrixString.isEmpty()){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!matrixString.contains(startDelimiter)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!matrixString.contains(endDelimiter)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!matrixString.contains(rowSeparator)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!matrixString.contains(rowElementsSeparator)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
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
