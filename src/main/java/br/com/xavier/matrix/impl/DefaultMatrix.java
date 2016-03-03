package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.AbstractMatrix;
import br.com.xavier.matrix.impl.parser.DefaultMatrixParser;

public final class DefaultMatrix<T> extends AbstractMatrix<T> {

	public DefaultMatrix() {
		super();
	}
	
	public DefaultMatrix(int columns, int rows) {
		super(columns, rows);
	}
	
	public DefaultMatrix(int columns, int rows, DefaultMatrixParser<T> parser) {
		super(columns, rows, parser);
	}
	
	@Override
	public T representsEmpty() {
		return null;
	}
	
}
