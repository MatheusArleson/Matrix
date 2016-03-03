package br.com.xavier.matrix.impl.parser;

import java.util.ArrayList;
import java.util.Map;

import br.com.xavier.matrix.abstraction.parser.AbstractMatrixParser;
import br.com.xavier.matrix.impl.DefaultMatrix;

public class DefaultMatrixParser<T> extends AbstractMatrixParser<T> {
	
	private static final DefaultMatrixParserValues DEFAULT_PARSER_VALUES = new DefaultMatrixParserValues();
	
	//XXX CONSTRUCTOR
	public DefaultMatrixParser() {
		super(
			DEFAULT_PARSER_VALUES.getMatrixRepresentationStartDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationEndDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentatitionMatrixStartDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentatitionMatrixEndDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationMatrixRowSeparator(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationMatrixRowElementsSeparator()
		);
	}
	
	public DefaultMatrixParser(
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
	public DefaultMatrix<T> generateEmptyMatrix() {
		return new DefaultMatrix<T>(0, 0, this);
	}
	
	@Override
	public DefaultMatrix<T> generateMatrix(Map<Integer, ArrayList<T>> map, int columnsSize) {
		int rows = map.keySet().size();
		
		DefaultMatrix<T> dm = new DefaultMatrix<T>(columnsSize, rows, this);
		
		for (Integer rowNumber : map.keySet()) {
			ArrayList<T> rowElements = map.get(rowNumber);
			for (int columnNumber = 0; columnNumber < rowElements.size(); columnNumber++) {
				dm.set(columnNumber, rowNumber, rowElements.get(columnNumber));
			}
		}
		
		if(dm.representsEmpty() != null){
			for (int col = 0; col < dm.getColumnCount(); col++) {
				for (int row = 0; row < dm.getRowCount(); row++) {
					boolean isNull = (dm.get(col, row) == null);
					if(isNull){
						dm.set(col, row, dm.representsEmpty());
					}
				}
			}
		}
		
		return dm;
	}
}
