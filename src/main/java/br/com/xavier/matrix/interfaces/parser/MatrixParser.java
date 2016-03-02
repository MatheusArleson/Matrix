package br.com.xavier.matrix.interfaces.parser;

import br.com.xavier.matrix.interfaces.Matrix;

public interface MatrixParser<T> {
	
	public String toString(Matrix<T> matrix);
	public Matrix<T> fromString(String matrixString);

}
