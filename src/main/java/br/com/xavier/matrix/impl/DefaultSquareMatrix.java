package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.AbstractSquareMatrix;
import br.com.xavier.matrix.impl.parser.DefaultSquareMatrixParser;

public class DefaultSquareMatrix<T> extends AbstractSquareMatrix<T>{

	public DefaultSquareMatrix() {
		super();
	}
	
	public DefaultSquareMatrix(int size) {
		super(size);
	}	
	
	public DefaultSquareMatrix(int size, DefaultSquareMatrixParser<T> parser) {
		super(size, parser);
	}	

	@Override
	public T representsEmpty() {
		return null;
	}
}
