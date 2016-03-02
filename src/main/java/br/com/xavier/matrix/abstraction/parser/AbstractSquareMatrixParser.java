package br.com.xavier.matrix.abstraction.parser;

import java.util.ArrayList;
import java.util.Map;

import br.com.xavier.matrix.abstraction.SquareMatrix;

public abstract class AbstractSquareMatrixParser<T> extends AbstractMatrixParser<T>{

	public AbstractSquareMatrixParser(
		String representationStartDelimiter, 
		String representationEndDelimiter,
		String matrixRepresentatitionStartDelimiter, 
		String matrixRepresentatitionEndDelimiter,
		String matrixRepresentationRowSeparator, 
		String matrixRepresentationRowElementsSeparator
	) {
		super(
			representationStartDelimiter, 
			representationEndDelimiter, 
			matrixRepresentatitionStartDelimiter,
			matrixRepresentatitionEndDelimiter, 
			matrixRepresentationRowSeparator, 
			matrixRepresentationRowElementsSeparator
		);
	}

	@Override
	public abstract SquareMatrix<T> generateEmptyMatrix();
	
	@Override
	public abstract SquareMatrix<T> generateMatrix(Map<Integer, ArrayList<T>> map, int columnsSize);

}
