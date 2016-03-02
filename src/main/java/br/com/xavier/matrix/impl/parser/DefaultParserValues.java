package br.com.xavier.matrix.impl.parser;

import br.com.xavier.matrix.abstraction.parser.AbstractParser;

public class DefaultParserValues extends AbstractParser{

	//XXX DEFAULT PARAMETERS VALUES
	private static final String REPRESENTATION_START_DELIMITER = "{";
	private static final String REPRESENTATION_END_DELIMITER = "}";
	private static final String MATRIX_START_DELIMITER = "["; 
	private static final String MATRIX_END_DELIMITER = "]";
	private static final String MATRIX_ROW_SEPARATOR = "\n"; 
	private static final String MATRIX_ROW_ELEMENTS_SEPARATOR = ",";
	
	protected DefaultParserValues(
	) {
		super(
			REPRESENTATION_START_DELIMITER, 
			REPRESENTATION_END_DELIMITER, 
			MATRIX_START_DELIMITER,
			MATRIX_END_DELIMITER, 
			MATRIX_ROW_SEPARATOR, 
			MATRIX_ROW_ELEMENTS_SEPARATOR
		);
	}

}
