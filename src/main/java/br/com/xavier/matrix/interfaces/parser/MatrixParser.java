package br.com.xavier.matrix.interfaces.parser;

import java.io.IOException;

import br.com.xavier.matrix.impl.DefaultMatrix;
import br.com.xavier.matrix.interfaces.Matrix;

public interface MatrixParser<T> {
	
	public String toString(Matrix<T> matrix);
	public DefaultMatrix fromString(String matrixString, String startDelimiter, String endDelimiter, String rowSeparator, String rowElementsSeparator) throws IOException;

}
