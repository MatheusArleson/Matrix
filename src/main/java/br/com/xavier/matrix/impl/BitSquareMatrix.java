package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.SquareMatrix;

public class BitSquareMatrix extends SquareMatrix<Integer> {

	public BitSquareMatrix(int size) {
		super(size);
	}

	@Override
	public Integer representsEmpty() {
		return 0;
	}

	@Override
	public Integer set(int column, int row, Integer obj) {
		throw new UnsupportedOperationException("Method now allowed.");
	}
	
	public Integer set(int column, int row) {
		Integer oldValue = this.matrix[column][row];
		this.matrix[column][row] = 1;
		return oldValue;
	}
	
	public Integer unset(int column, int row) {
		Integer oldValue = this.matrix[column][row];
		this.matrix[column][row] = 0;
		return oldValue;
	}
}
