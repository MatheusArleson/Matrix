package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.parser.AbstractSquareMatrixParser;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public final class BitSquareMatrix extends DefaultSquareMatrix<Integer> {
	
	public BitSquareMatrix() {
		super();
	}
	
	public BitSquareMatrix(AbstractSquareMatrixParser<Integer> parser, int size) {
		super(parser, size);
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

	public boolean isSet(int column, int row){
		Integer bit = get(column, row);
		boolean isUnset = bit.equals(representsEmpty());
		return !isUnset;
	}
}
