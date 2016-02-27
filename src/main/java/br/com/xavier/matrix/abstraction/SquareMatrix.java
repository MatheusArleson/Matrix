package br.com.xavier.matrix.abstraction;

import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public abstract class SquareMatrix<T> extends AbstractMatrix<T> {
	
	//XXX CONSTRUCTOR
	public SquareMatrix(int size) {
		super(size, size);
	}

	//XXX OVERRIDE METHODS
	@Override
	public void addColumn() {
		throw new UnsupportedOperationException(MessageManager.getDefaultMessage(DefaultMessagesKey.UNSUPPORTED_OPERATION_EXCEPTION));
	}

	@Override
	public void addRow() {
		throw new UnsupportedOperationException(MessageManager.getDefaultMessage(DefaultMessagesKey.UNSUPPORTED_OPERATION_EXCEPTION));
	}

	@Override
	public void removeColumn(int columnNumber) {
		throw new UnsupportedOperationException(MessageManager.getDefaultMessage(DefaultMessagesKey.UNSUPPORTED_OPERATION_EXCEPTION));
	}
	
	@Override
	public void removeRow(int rowNumber) {
		throw new UnsupportedOperationException(MessageManager.getDefaultMessage(DefaultMessagesKey.UNSUPPORTED_OPERATION_EXCEPTION));
	}
}
