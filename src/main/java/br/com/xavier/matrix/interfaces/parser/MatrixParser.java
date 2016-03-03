package br.com.xavier.matrix.interfaces.parser;

import br.com.xavier.matrix.interfaces.Matrix;

public interface MatrixParser<T> {
	
	public String toMatrixString(Matrix<T> matrix);
	public Matrix<T> fromMatrixString(String matrixString);

}
