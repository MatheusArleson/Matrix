package br.com.xavier.matrix.abstraction;

import br.com.xavier.matrix.abstraction.parser.AbstractSquareMatrixParser;
import br.com.xavier.matrix.impl.parser.DefaultSquareMatrixParser;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public abstract class AbstractSquareMatrix<T> extends AbstractMatrix<T> {
	
	//XXX CONSTRUCTOR
	public AbstractSquareMatrix() {
		super();
	}
	
	public AbstractSquareMatrix(int size) {
		super(size, size);
	}
	
	public AbstractSquareMatrix(int size, AbstractSquareMatrixParser<T> parser) {
		super(size, size, parser);
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
	
	@Override
	public String toString() {
		if(getMatrixParser() == null){
			setMatrixParser(new DefaultSquareMatrixParser<>());
		}
		
		return getMatrixParser().toString();
	}


}
