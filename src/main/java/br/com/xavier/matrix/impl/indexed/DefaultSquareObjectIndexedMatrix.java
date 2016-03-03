package br.com.xavier.matrix.impl.indexed;

import java.util.LinkedHashSet;

import br.com.xavier.matrix.abstraction.indexed.AbstractSquareObjectIndexedMatrix;
import br.com.xavier.matrix.impl.DefaultSquareMatrix;

public class DefaultSquareObjectIndexedMatrix<O,T> extends AbstractSquareObjectIndexedMatrix<O, DefaultSquareMatrix<T>, T>{

	public DefaultSquareObjectIndexedMatrix() throws Exception {
		super();
	}
	
	public DefaultSquareObjectIndexedMatrix(
		LinkedHashSet<O> rowsObjsSet, 
		LinkedHashSet<O> columnsObjsSet, 
		DefaultSquareMatrix<T> matrix
	) throws Exception {
		super(rowsObjsSet, columnsObjsSet, matrix);
	}

	@Override
	public T representsEmpty() {
		return null;
	}

}
