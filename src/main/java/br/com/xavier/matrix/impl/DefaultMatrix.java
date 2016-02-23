package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.AbstractMatrix;

public class DefaultMatrix extends AbstractMatrix<Object> {

	public DefaultMatrix(int columns, int rows) {
		super(columns, rows);
	}

	@Override
	public Object representsEmpty() {
		return null;
	}

}
