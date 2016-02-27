package br.com.xavier.matrix.interfaces.parser;

import java.io.IOException;

import br.com.xavier.matrix.impl.DefaultMatrix;
import br.com.xavier.matrix.interfaces.Matrix;

public interface MatrixParser<T> {
	
	public String toString(Matrix<T> matrix);
	public DefaultMatrix<T> fromString(String matrixString) throws IOException;

}
