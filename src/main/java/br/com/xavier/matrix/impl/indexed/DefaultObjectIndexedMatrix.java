package br.com.xavier.matrix.impl.indexed;

import java.util.LinkedHashSet;

import br.com.xavier.matrix.abstraction.indexed.AbstractObjectIndexedMatrix;
import br.com.xavier.matrix.impl.DefaultMatrix;

public class DefaultObjectIndexedMatrix<O, T> extends AbstractObjectIndexedMatrix<O, DefaultMatrix<T>, T> {
	
	public DefaultObjectIndexedMatrix() throws Exception {
		super();
	}
	
	public DefaultObjectIndexedMatrix(
		LinkedHashSet<O> rowsObjsSet, 
		LinkedHashSet<O> columnsObjsSet, 
		DefaultMatrix<T> matrix
	) throws Exception {
		super(rowsObjsSet, columnsObjsSet, matrix);
	}
	
	@Override
	public T representsEmpty() {
		return null;
	}

}
