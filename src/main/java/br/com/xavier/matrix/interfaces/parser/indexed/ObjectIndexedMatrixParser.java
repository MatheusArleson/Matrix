package br.com.xavier.matrix.interfaces.parser.indexed;

import br.com.xavier.matrix.interfaces.indexed.ObjectIndexedMatrix;

public interface ObjectIndexedMatrixParser<O, T> {
	
	public String toString(ObjectIndexedMatrix<O, T> matrix);
	public ObjectIndexedMatrix<O, T> fromString(String str);

}
