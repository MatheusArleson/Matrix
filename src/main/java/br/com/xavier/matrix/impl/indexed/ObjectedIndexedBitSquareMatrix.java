package br.com.xavier.matrix.impl.indexed;

import br.com.xavier.matrix.abstraction.indexed.SquareObjectIndexedMatrix;
import br.com.xavier.matrix.impl.BitSquareMatrix;

public class ObjectedIndexedBitSquareMatrix<O> extends SquareObjectIndexedMatrix<O, BitSquareMatrix, Integer> {

	public ObjectedIndexedBitSquareMatrix() throws Exception {
		super();
	}

	
	@Override
	public Integer representsEmpty() {
		return 0;
	}

}
