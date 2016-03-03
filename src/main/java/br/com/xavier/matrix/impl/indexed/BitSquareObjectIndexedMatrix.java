package br.com.xavier.matrix.impl.indexed;

public class BitSquareObjectIndexedMatrix<O> extends DefaultSquareObjectIndexedMatrix<O, Integer> {

	public BitSquareObjectIndexedMatrix()throws Exception {
		super();
	}
	
	@Override
	public Integer representsEmpty() {
		return 0;
	}

}
