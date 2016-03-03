package br.com.xavier.matrix.abstraction.indexed;

import java.util.LinkedHashSet;

import br.com.xavier.matrix.abstraction.AbstractSquareMatrix;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public abstract class AbstractSquareObjectIndexedMatrix<O, M extends AbstractSquareMatrix<T>, T> 
				extends AbstractObjectIndexedMatrix<O, M, T>{
	
	public AbstractSquareObjectIndexedMatrix() throws Exception {
		super();
	}
	
	public AbstractSquareObjectIndexedMatrix(LinkedHashSet<O> rowsSet, LinkedHashSet<O> columnsSet, M matrix) throws Exception {
		super(rowsSet, columnsSet, matrix);
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
