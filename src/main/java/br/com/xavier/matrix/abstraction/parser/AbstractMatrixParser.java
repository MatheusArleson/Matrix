package br.com.xavier.matrix.abstraction.parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.interfaces.Matrix;
import br.com.xavier.matrix.interfaces.parser.MatrixParser;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public abstract class AbstractMatrixParser<T> extends AbstractParser implements MatrixParser<T> {
	
	public AbstractMatrixParser(
		String representationStartDelimiter, 
		String representationEndDelimiter,
		String matrixRepresentatitionStartDelimiter, 
		String matrixRepresentatitionEndDelimiter,
		String matrixRepresentationRowSeparator, 
		String matrixRepresentationRowElementsSeparator
	) {
		super(
			representationStartDelimiter, 
			representationEndDelimiter, 
			matrixRepresentatitionStartDelimiter,
			matrixRepresentatitionEndDelimiter, 
			matrixRepresentationRowSeparator, 
			matrixRepresentationRowElementsSeparator
		);
	}

	@Override
	public String toString(Matrix<T> matrix) {
		if(matrix == null){
			throw new NullPointerException(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		T empty = matrix.representsEmpty();
		String emptyRepresentation = (empty == null ? "null" : empty.toString());
		
		int columns = matrix.getColumnCount();
		int rows = matrix.getRowCount();
		
		StringBuilder sb = new StringBuilder();
		sb.append(getRepresentationStartDelimiter());
		sb.append(getMatrixRepresentatitionStartDelimiter());
		
		for (int rowCount = 0; rowCount < rows; rowCount++) {
			StringBuffer rowBuffer = new StringBuffer();
			
			for (int columnCount = 0; columnCount < columns; columnCount++) {
				
				T matrixElement = matrix.get(columnCount, rowCount);
				boolean isEmpty = matrix.checkEmpty(matrixElement);
				rowBuffer.append(isEmpty ? emptyRepresentation : matrixElement.toString());
				rowBuffer.append(getMatrixRepresentationRowElementsSeparator());
			}
			
			String lineStr = rowBuffer.toString();
			if(lineStr.endsWith(getMatrixRepresentationRowElementsSeparator())){
				lineStr = lineStr.substring(0, lineStr.length() - getMatrixRepresentationRowElementsSeparator().length());
			}
			
			sb.append(lineStr);
			sb.append(getMatrixRepresentationRowSeparator());
		}
		
		sb.append(getMatrixRepresentatitionEndDelimiter());
		sb.append(getRepresentationEndDelimiter());
		return sb.toString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Matrix<T> fromString(String matrixString) throws InvalidMatrixRepresentation {
		
		matrixString = validate(matrixString);
		
		if(matrixString.isEmpty()){
			return generateEmptyMatrix();
		}
		
		String[] lines = matrixString.split(getMatrixRepresentationRowSeparator());
		int rows = lines.length;
		int columns = 0;
		
		Map<Integer, ArrayList<T>> map = new LinkedHashMap<Integer, ArrayList<T>>();
		for (int lineNumber = 0; lineNumber < rows; lineNumber++) {
			
			String line = lines[lineNumber];
			String[] elements = line.split(getMatrixRepresentationRowElementsSeparator());
			if(elements.length > columns){
				columns = elements.length;
			}
			
			map.put(lineNumber, new ArrayList<T>());
			
			for (String element : elements) {
				T obj = (T) element;
				map.get(lineNumber).add(obj);
			}
		}
		
		return generateMatrix(map, columns);
	}

	public abstract Matrix<T> generateEmptyMatrix();

	public abstract Matrix<T> generateMatrix(Map<Integer, ArrayList<T>> map, int columnsSize);
}
