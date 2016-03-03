package br.com.xavier.matrix.impl.parser;

import java.util.ArrayList;
import java.util.Map;

import br.com.xavier.matrix.abstraction.parser.AbstractSquareMatrixParser;
import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.impl.DefaultSquareMatrix;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public class DefaultSquareMatrixParser<T> extends AbstractSquareMatrixParser<T> {

	private static final DefaultMatrixParserValues DEFAULT_PARSER_VALUES = new DefaultMatrixParserValues();
	
	//XXX CONSTRUCTOR
	public DefaultSquareMatrixParser() {
		super(
			DEFAULT_PARSER_VALUES.getMatrixRepresentationStartDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationEndDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentatitionMatrixStartDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentatitionMatrixEndDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationMatrixRowSeparator(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationMatrixRowElementsSeparator()
		);
	}
	
	public DefaultSquareMatrixParser(
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
	public DefaultSquareMatrix<T> generateEmptyMatrix() {
		return new DefaultSquareMatrix<T>(0, this);
	}

	@Override
	public DefaultSquareMatrix<T> generateMatrix(Map<Integer, ArrayList<T>> map, int columnsSize) {
		int rowCount = map.keySet().size();
		if(rowCount != columnsSize){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		DefaultSquareMatrix<T> dsm = new DefaultSquareMatrix<T>(rowCount, this);
		
		for (Integer rowNumber : map.keySet()) {
			ArrayList<T> rowElements = map.get(rowNumber);
			for (int columnNumber = 0; columnNumber < rowElements.size(); columnNumber++) {
				dsm.set(columnNumber, rowNumber, rowElements.get(columnNumber));
			}
		}
		
		if(dsm.representsEmpty() != null){
			for (int col = 0; col < dsm.getColumnCount(); col++) {
				for (int row = 0; row < dsm.getRowCount(); row++) {
					boolean isNull = (dsm.get(col, row) == null);
					if(isNull){
						dsm.set(col, row, dsm.representsEmpty());
					}
				}
			}
		}
		
		return dsm;
	}

}
