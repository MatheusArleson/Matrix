package br.com.xavier.matrix.util.messages;

import br.com.xavier.matrix.exception.IllegalMatrixIndex;
import br.com.xavier.matrix.interfaces.Matrix;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public class Util {
	
	//XXX CONSTRUCTOR
	//defeat instantiation
	private Util(){}

	//XXX METHODS
	public static void checkNullParameter(Object...objects){
		for (Object object : objects) {
			if(object == null){
				handleNullParameter();
			}
		}
	}

	private static void handleNullParameter() {
		throw new NullPointerException(MessageManager.getDefaultMessage(DefaultMessagesKey.PARAMETER_NULL));
	}
	
	public static void checkInvalidRowIndex(Matrix<?> matrix, int rowIndex){
		if(rowIndex < 0){
			throw new IllegalMatrixIndex(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_INDEX));
		}
		
		if(rowIndex > matrix.getRowCount()){
			throw new IllegalMatrixIndex(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_INDEX));
		}
	}
	
	public static void checkInvalidColumnIndex(Matrix<?> matrix, int columnIndex){
		if(columnIndex < 0){
			throw new IllegalMatrixIndex(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_INDEX));
		}
		
		if(columnIndex > matrix.getColumnCount()){
			throw new IllegalMatrixIndex(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_INDEX));
		}
	}
	
	public static void checkInvalidColumnRowIndex(Matrix<?> matrix, int columnIndex){
		checkInvalidColumnIndex(matrix, columnIndex);
		checkInvalidColumnRowIndex(matrix, columnIndex);
	}
}
