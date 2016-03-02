package br.com.xavier.matrix.impl.parser;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.impl.BitSquareMatrix;
import br.com.xavier.matrix.interfaces.Matrix;


public class DefaultMatrixParserTest {
//	@Test
//	public void toStringTest() {
//		DefaultSquareMatrixParser<Integer> dsmp = new DefaultSquareMatrixParser<Integer>();
//		BitSquareMatrix toStringMatrix = new BitSquareMatrix(dsmp, 4);
//		String str = toStringMatrix.toString();
//		
//		Assert.assertNotNull(str);
//	}
	
	@Test
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
		
		Assert.assertEquals(representationStartDelimiter, parser.getRepresentationStartDelimiter());
		Assert.assertEquals(representationEndDelimiter, parser.getRepresentationEndDelimiter());
		Assert.assertEquals(matrixRepresentatitionStartDelimiter, parser.getMatrixRepresentatitionStartDelimiter());
		Assert.assertEquals(matrixRepresentatitionEndDelimiter, parser.getMatrixRepresentatitionEndDelimiter());
		Assert.assertEquals(matrixRepresentationRowSeparator, parser.getMatrixRepresentationRowSeparator());
		Assert.assertEquals(matrixRepresentationRowElementsSeparator, parser.getMatrixRepresentationRowElementsSeparator());
		
		String matrixString = new String(
			"{[\n" +
				//"0,0,0,0,\n" +
				//"0,0,0,0,\n" +
				//"0,0,0,0,\n" +
				//"0,0,0,0" +
				//"0,0,\n" +
			"\n]}"
		); 
		
		Matrix<Integer> matrix = parser.fromString(matrixString);
		String matrixStr = matrix.toString();
		
		Assert.assertEquals(matrixString, matrixStr);
	}
}
