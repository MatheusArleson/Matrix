package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.AbstractMatrix;

public final class DefaultMatrix<T> extends AbstractMatrix<T> {

	public DefaultMatrix(int columns, int rows) {
		super(columns, rows);
	}
	
	@Override
	public T representsEmpty() {
		return null;
	}

}
