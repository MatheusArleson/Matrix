package br.com.xavier.matrix.impl.parser;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.impl.BitSquareMatrix;
import br.com.xavier.matrix.impl.indexed.DefaultObjectIndexedMatrix;
import br.com.xavier.matrix.interfaces.Matrix;


public class DefaultMatrixParserTest {
	
	@Test
	public void aTest() throws Exception{
		DefaultObjectIndexedMatrix<Integer, Integer> doim = new DefaultObjectIndexedMatrix<>();
		doim.addRow(1);
		doim.addColumn(1);
		doim.set(1, 1, 0);
		
		Integer inPlace = doim.get(1, 1);
		boolean isEqual = inPlace.equals(0);
		
		Assert.assertTrue(isEqual);
		
	}
	
	@Test @Ignore
	public void toStringTest() {
		DefaultSquareMatrixParser<Integer> dsmp = new DefaultSquareMatrixParser<Integer>();
		BitSquareMatrix toStringMatrix = new BitSquareMatrix(dsmp, 4);
		String str = toStringMatrix.toString();
		
		Assert.assertNotNull(str);
	}
	
	@Test @Ignore
	public void fromStringTest() throws IOException, InvalidMatrixRepresentation {
		String representationStartDelimiter = "{";
		String representationEndDelimiter = "}";
		String matrixRepresentatitionStartDelimiter = "[\n";
		String matrixRepresentatitionEndDelimiter = "\n]";
		String matrixRepresentationRowSeparator = "\n";
		String matrixRepresentationRowElementsSeparator = ",";
		
		DefaultMatrixParser<Integer> parser = new DefaultMatrixParser<Integer>(
			representationStartDelimiter, 
			representationEndDelimiter, 
			matrixRepresentatitionStartDelimiter, 
			matrixRepresentatitionEndDelimiter, 
			matrixRepresentationRowSeparator, 
			matrixRepresentationRowElementsSeparator
		);
		
		String matrixString = new String(
			"{[\n" +
				"0,0,0,0\n" +
				"0,0,0,0\n" +
				"0,0,0,0\n" +
				"0,0,0,0\n" +
				"0,0,null,null\n" +
			"\n]}"
		); 
		
		Matrix<Integer> matrix = parser.fromMatrixString(matrixString);
		String matrixStr = matrix.toString();
		
		Assert.assertEquals(matrixString, matrixStr);
	}
}
