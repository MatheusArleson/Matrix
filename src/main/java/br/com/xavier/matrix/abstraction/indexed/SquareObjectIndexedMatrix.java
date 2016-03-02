package br.com.xavier.matrix.abstraction.indexed;

import br.com.xavier.matrix.abstraction.SquareMatrix;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public abstract class SquareObjectIndexedMatrix<O, M extends SquareMatrix<T>, T> extends AbstractObjectIndexedMatrix<O, M, T>{
	
	public SquareObjectIndexedMatrix() throws Exception {
		super();
	}
	
	@Override
	public void addColumn(O obj) {
		throw new UnsupportedOperationException(MessageManager.getDefaultMessage(DefaultMessagesKey.UNSUPPORTED_OPERATION_EXCEPTION));
	}

	@Override
	public void addRow(O obj) {
		throw new UnsupportedOperationException(MessageManager.getDefaultMessage(DefaultMessagesKey.UNSUPPORTED_OPERATION_EXCEPTION));
	}

	@Override
	public void removeColumn(O obj) {
		throw new UnsupportedOperationException(MessageManager.getDefaultMessage(DefaultMessagesKey.UNSUPPORTED_OPERATION_EXCEPTION));
	}
	
	@Override
	public void removeRow(O obj) {
		throw new UnsupportedOperationException(MessageManager.getDefaultMessage(DefaultMessagesKey.UNSUPPORTED_OPERATION_EXCEPTION));
	}
	
}
