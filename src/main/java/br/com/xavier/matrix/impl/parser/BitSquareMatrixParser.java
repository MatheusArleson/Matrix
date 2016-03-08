package br.com.xavier.matrix.impl.parser;

import java.util.ArrayList;
import java.util.Map;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.impl.BitSquareMatrix;
import br.com.xavier.matrix.interfaces.Matrix;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public class BitSquareMatrixParser extends DefaultSquareMatrixParser<Integer>{

private static final DefaultMatrixParserValues DEFAULT_PARSER_VALUES = new DefaultMatrixParserValues();
	
	//XXX CONSTRUCTOR
	public BitSquareMatrixParser() {
		super(
			DEFAULT_PARSER_VALUES.getMatrixRepresentationStartDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationEndDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationMatrixStartDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationMatrixEndDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationMatrixRowSeparator(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationMatrixRowElementsSeparator()
		);
	}
	
	public BitSquareMatrixParser(
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
	public BitSquareMatrix generateEmptyMatrix() {
		return new BitSquareMatrix();
	}
	
	@Override
	public BitSquareMatrix generateMatrix(Map<Integer, ArrayList<Integer>> map, int columnsSize) {
		int rowCount = map.keySet().size();
		if(rowCount != columnsSize){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		BitSquareMatrix bsm = new BitSquareMatrix(this, rowCount);
		
		for (Integer rowNumber : map.keySet()) {
			ArrayList<Integer> rowElements = map.get(rowNumber);
			for (int columnNumber = 0; columnNumber < rowElements.size(); columnNumber++) {
				Integer value = Integer.valueOf( String.valueOf(rowElements.get(columnNumber)));
				if(value.equals(bsm.representsEmpty())){
					bsm.unset(columnNumber, rowNumber);
				} else {
					bsm.set(columnNumber, rowNumber);
				}
			}
		}
		
		if(bsm.representsEmpty() != null){
			for (int col = 0; col < bsm.getColumnCount(); col++) {
				for (int row = 0; row < bsm.getRowCount(); row++) {
					boolean isNull = (bsm.get(col, row) == null);
					if(isNull){
						bsm.unset(col, row);
					}
				}
			}
		}
		
		return bsm;
	}
	
	public static void main(String[] args) {
		String str = new String(
			"{[0,1,\n" +
			"1,0]}"
		);
		
		BitSquareMatrixParser bsmp = new BitSquareMatrixParser();
		Matrix<Integer> matrix = bsmp.fromMatrixString(str);
	}
}
