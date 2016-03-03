package br.com.xavier.matrix.validation;

import br.com.xavier.matrix.exception.IllegalMatrixIndex;
import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.interfaces.Matrix;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public final class MatrixValidator {
	
	//XXX CONSTRUCTOR
	//defeat instantiation
	private MatrixValidator(){}

	//XXX METHODS
	public static void checkInvalidRowColumnSize(int size){
		if(size < 0){
			throw new IllegalMatrixIndex(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_INDEX));
		}
	}
	
	public static void checkInvalidRowIndex(Matrix<?> matrix, int rowIndex){
		checkInvalidRowColumnSize(rowIndex);
		
		if(rowIndex > matrix.getRowCount()){
			throw new IllegalMatrixIndex(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_INDEX));
		}
	}
	
	public static void checkInvalidColumnIndex(Matrix<?> matrix, int columnIndex){
		checkInvalidRowColumnSize(columnIndex);
		
		if(columnIndex > matrix.getColumnCount()){
			throw new IllegalMatrixIndex(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_INDEX));
		}
	}
	
	public static void checkInvalidColumnRowIndex(Matrix<?> matrix, int columnIndex){
		checkInvalidColumnIndex(matrix, columnIndex);
		checkInvalidRowIndex(matrix, columnIndex);
	}
	
	
	//XXX CHECK METHODS
	public static void checkContains(String str, String containedStr){
		if(!str.contains(containedStr)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
	}
	
	public static void checkDelimiterIsBefore(int indexOfSecond, int indexOfFirst){
		if(indexOfSecond < indexOfFirst){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
	}
}
