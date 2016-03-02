package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.SquareMatrix;
import br.com.xavier.matrix.abstraction.parser.AbstractSquareMatrixParser;
import br.com.xavier.matrix.impl.parser.DefaultSquareMatrixParser;

public class DefaultSquareMatrix<T> extends SquareMatrix<T>{

	public DefaultSquareMatrix() {
		super(new DefaultSquareMatrixParser<T>());
	}
	
	public DefaultSquareMatrix(AbstractSquareMatrixParser<T> parser, int size) {
		super(parser, size);
	}	

	@Override
	public T representsEmpty() {
		return null;
	}
}
