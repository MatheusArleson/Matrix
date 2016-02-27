package br.com.xavier.matrix.impl.parser;

import br.com.xavier.matrix.abstraction.parser.AbstractMatrixParser;

public final class DefaultMatrixParser<T> extends AbstractMatrixParser<T> {
	
	//XXX DEFAULT PARAMETERS VALUES
	private static final String START_DELIMITER = "["; 
	private static final String END_DELIMITER = "]";
	private static final String ROW_SEPARATOR = "\n"; 
	private static final String ROW_ELEMENTS_SEPARATOR = ",";
	
	//XXX CONSTRUCTOR
	public DefaultMatrixParser() {
		super(START_DELIMITER, END_DELIMITER, ROW_SEPARATOR, ROW_ELEMENTS_SEPARATOR);
	}
	
	public DefaultMatrixParser(
		String startDelimiter, 
		String endDelimiter, 
		String rowSeparator,
		String rowElementsSeparator
	) {
		super(startDelimiter, endDelimiter, rowSeparator, rowElementsSeparator);
	}
}
