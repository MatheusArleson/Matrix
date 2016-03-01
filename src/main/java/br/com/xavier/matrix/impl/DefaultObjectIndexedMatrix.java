package br.com.xavier.matrix.impl;

import br.com.xavier.matrix.abstraction.ObjectIndexedMatrix;

public class DefaultObjectIndexedMatrix<O> extends ObjectIndexedMatrix<O, DefaultMatrix<Integer>, Integer> {

	public DefaultObjectIndexedMatrix() throws Exception {
		super();
	}

	public static void main(String[] args) throws Exception {
		DefaultObjectIndexedMatrix<Float> oim = new DefaultObjectIndexedMatrix<Float>();
		
	}
}
