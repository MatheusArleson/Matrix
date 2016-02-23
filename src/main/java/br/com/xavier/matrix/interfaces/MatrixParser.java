package br.com.xavier.matrix.interfaces;

import java.io.IOException;

import br.com.xavier.matrix.impl.DefaultMatrix;

public interface MatrixParser<T> {
	
	public String toString(Matrix<T> matrix);
	public DefaultMatrix fromString(String str, String rowSeparator, String rowElementsSeparator) throws IOException;

}
