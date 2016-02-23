package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.SquareMatrix;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public class BitSquareMatrix extends SquareMatrix<Integer> {

	public BitSquareMatrix(int size) {
		super(size);
	}

	@Override
	public Integer representsEmpty() {
		return 0;
	}

	@Override
	public Integer set(int column, int row, Integer obj) {
		throw new UnsupportedOperationException(MessageManager.getDefaultMessage(DefaultMessagesKey.UNSUPPORTED_OPERATION_EXCEPTION));
	}
	
	public Integer set(int column, int row) {
		Integer oldValue = this.matrix[column][row];
		this.matrix[column][row] = 1;
		return oldValue;
	}
	
	public Integer unset(int column, int row) {
		Integer oldValue = this.matrix[column][row];
		this.matrix[column][row] = 0;
		return oldValue;
	}

	public static void main(String[] args) {
		BitSquareMatrix bsm = new BitSquareMatrix(4);
		System.out.println(bsm.toString());
	}
}
