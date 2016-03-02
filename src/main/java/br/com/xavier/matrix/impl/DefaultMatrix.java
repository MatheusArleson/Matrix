package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.AbstractMatrix;
import br.com.xavier.matrix.impl.parser.DefaultMatrixParser;
import br.com.xavier.matrix.interfaces.parser.MatrixParser;

public final class DefaultMatrix<T> extends AbstractMatrix<T> {

	public DefaultMatrix() {
		super(new DefaultMatrixParser<T>());
	}
	
	public DefaultMatrix(MatrixParser<T> parser, int columns, int rows) {
		super(parser, columns, rows);
	}
	
	@Override
	public T representsEmpty() {
		return null;
	}

}
