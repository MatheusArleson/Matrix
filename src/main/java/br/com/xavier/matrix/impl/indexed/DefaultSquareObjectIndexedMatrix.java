package br.com.xavier.matrix.impl.indexed;

import br.com.xavier.matrix.abstraction.SquareMatrix;
import br.com.xavier.matrix.abstraction.indexed.SquareObjectIndexedMatrix;

public class DefaultSquareObjectIndexedMatrix<O,T> extends SquareObjectIndexedMatrix<O, SquareMatrix<T>, T>{

	public DefaultSquareObjectIndexedMatrix() throws Exception {
		super();
	}


	@Override
	public T representsEmpty() {
		return null;
	}

}
