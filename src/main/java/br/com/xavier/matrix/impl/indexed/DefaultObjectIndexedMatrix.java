package br.com.xavier.matrix.impl.indexed;

import br.com.xavier.matrix.abstraction.indexed.AbstractObjectIndexedMatrix;
import br.com.xavier.matrix.impl.DefaultMatrix;

public class DefaultObjectIndexedMatrix<O, T> extends AbstractObjectIndexedMatrix<O, DefaultMatrix<T>, T> {

	public DefaultObjectIndexedMatrix() throws Exception {
		super();
	}

	
	@Override
	public T representsEmpty() {
		return null;
	}
	
	
}
