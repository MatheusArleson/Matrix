package br.com.xavier.matrix.impl.parser.indexed;

import br.com.xavier.matrix.abstraction.parser.indexed.AbstractObjectIndexedMatrixParser;

public class DefaultObjectIndexedMatrixParser<O, T> extends AbstractObjectIndexedMatrixParser<O, T>{

	public DefaultObjectIndexedMatrixParser(
		String startDelimiter, 
		String endDelimiter, 
		String rowSeparator,
		String rowElementsSeparator
	) {
		super(startDelimiter, endDelimiter, rowSeparator, rowElementsSeparator);
	}

}
