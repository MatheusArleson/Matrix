package br.com.xavier.matrix.impl.parser;

import java.util.ArrayList;
import java.util.Map;

import br.com.xavier.matrix.abstraction.parser.AbstractMatrixParser;
import br.com.xavier.matrix.impl.DefaultMatrix;

public class DefaultMatrixParser<T> extends AbstractMatrixParser<T> {
	
	private static final DefaultParserValues DEFAULT_PARSER_VALUES = new DefaultParserValues();
	
	//XXX CONSTRUCTOR
	public DefaultMatrixParser() {
		super(
			DEFAULT_PARSER_VALUES.getRepresentationStartDelimiter(), 
			DEFAULT_PARSER_VALUES.getRepresentationEndDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentatitionStartDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentatitionEndDelimiter(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationRowSeparator(), 
			DEFAULT_PARSER_VALUES.getMatrixRepresentationRowElementsSeparator()
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
		return new DefaultMatrix<T>(this, 0, 0);
	}
	
	@Override
	public DefaultMatrix<T> generateMatrix(Map<Integer, ArrayList<T>> map, int columnsSize) {
		int rows = map.keySet().size();
		
		DefaultMatrix<T> dm = new DefaultMatrix<T>(this, columnsSize, rows);
		
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
