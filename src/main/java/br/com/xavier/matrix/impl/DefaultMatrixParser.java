package br.com.xavier.matrix.impl;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.interfaces.Matrix;
import br.com.xavier.matrix.interfaces.MatrixParser;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public class DefaultMatrixParser<T> implements MatrixParser<T> {
	
	//XXX STATIC BLOCK
	private Class<T> clazz;
    { initClazz(); }
    
    @SuppressWarnings("unchecked")
    private void initClazz() {
    	this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
    private ObjectMapper mapper;
    
	//XXX CONSTRUCTOR
	public DefaultMatrixParser() { 
		this.mapper = new ObjectMapper();
	}
	
	@Override
	public String toString(Matrix<T> matrix) {
		if(matrix == null){
			return MessageManager.getDefaultMessage(DefaultMessagesKey.EMPTY_MATRIX);
		}
		
		String emptyStr = matrix.representsEmpty().toString();
		int columns = matrix.getColumnCount();
		int rows = matrix.getRowCount();
		
		StringBuilder result = new StringBuilder(rows * (columns + 1));
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				T obj = matrix.get(x, y);
				boolean isEmpty = matrix.checkEmpty(obj);
				result.append(isEmpty ? emptyStr : obj.toString());
				result.append(",");
			}
			result.append("\n");
		}
		return result.toString();
	}
	
	@Override
	public DefaultMatrix fromString(String str, String rowSeparator, String rowElementsSeparator) throws IOException {
		if(str == null || str.isEmpty()){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!str.contains(rowSeparator)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!str.contains(rowElementsSeparator)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		String[] lines = str.split(rowSeparator);
		int rows = lines.length;
		int columns = 0;
		
		Map<Integer, ArrayList<T>> map = new LinkedHashMap<Integer, ArrayList<T>>();
		for (int lineNumber = 0; lineNumber < lines.length; lineNumber++) {
			String line = lines[lineNumber];
			String[] elements = line.split(rowElementsSeparator);
			if(elements.length > columns){
				columns = elements.length;
			}
			
			map.put(lineNumber, new ArrayList<T>());
			
			for (String element : elements) {
				T obj = mapper.readValue(element, clazz);
				map.get(lineNumber).add(obj);
			}
		}
		
		DefaultMatrix dm = new DefaultMatrix(columns, rows);
		for (Integer rowNumber : map.keySet()) {
			ArrayList<T> rowElements = map.get(rowNumber);
			for (int columnNumber = 0; columnNumber < rowElements.size(); columnNumber++) {
				dm.set(columnNumber, rowNumber, rowElements.get(columnNumber));
			}
		}
		
		return dm;
	}
}
