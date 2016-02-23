package br.com.xavier.matrix.impl.parser;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.impl.BitSquareMatrix;
import br.com.xavier.matrix.impl.DefaultMatrix;


public class DefaultMatrixParserTest {
	@Test
	public void toStringTest() {
		BitSquareMatrix toStringMatrix = new BitSquareMatrix(4);
		String str = toStringMatrix.toString();
		
		Assert.assertNotNull(str);
	}
	
	@Test
	public void fromStringTest() throws IOException, InvalidMatrixRepresentation {
		DefaultMatrixParser<Integer> parser = new DefaultMatrixParser<Integer>();
		
		String matrixString = new String(
			"[\n" +
				"0,0,0,0,\n" +
				"0,0,0,0,\n" +
				"0,0,0,0,\n" +
				"0,0,0,0" +
			"\n]"
		); 
		
		DefaultMatrix<Integer> fromStringMatrix = parser.fromString(matrixString, "[\n", "\n]", "\n", ",");
		String matrixStr = fromStringMatrix.toString();
		
		Assert.assertEquals(matrixString, matrixStr);
	}
}
