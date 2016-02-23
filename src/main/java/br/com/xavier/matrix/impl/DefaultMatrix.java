package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.AbstractMatrix;

public class DefaultMatrix<E> extends AbstractMatrix<E> {

	public DefaultMatrix(int columns, int rows) {
		super(columns, rows);
	}
	
	@Override
	public E representsEmpty() {
		return null;
	}

}
