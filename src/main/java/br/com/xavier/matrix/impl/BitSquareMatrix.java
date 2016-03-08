package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.impl.parser.DefaultSquareMatrixParser;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public final class BitSquareMatrix extends DefaultSquareMatrix<Integer> {
	
	public BitSquareMatrix() {
		super();
	}
	
	public BitSquareMatrix(DefaultSquareMatrixParser<Integer> parser, int size) {
		super(size, parser);
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
		Integer oldValue = get(column, row);
		super.set(column, row, 1);
		return oldValue;
	}
	
	public Integer unset(int column, int row) {
		Integer oldValue = get(column, row);
		super.set(column, row, 0);
		return oldValue;
	}

	public boolean isSet(int column, int row){
		Integer bit = get(column, row);
		boolean isUnset = bit.equals(representsEmpty());
		return !isUnset;
	}
}
