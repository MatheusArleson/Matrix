package br.com.xavier.matrix.abstraction.parser.indexed;

import br.com.xavier.matrix.abstraction.parser.AbstractParser;
import br.com.xavier.matrix.exception.InvalidMatrixRepresentationDelimiter;
import br.com.xavier.matrix.validation.StringValidator;

public abstract class AbstractObjectIndexedParser extends AbstractParser{

	//XXX CLASS PROPERTIES
	private final String rowObjectsStartDelimiter;
	private final String rowObjectsEndDelimiter;
	private final String columnsObjectsStartDelimiter;
	private final String columnsObjectsEndDelimiter;
	
	//XXX CONSTRUCTOR
	protected AbstractObjectIndexedParser(
		String rowObjectsStartDelimiter,
		String rowObjectsEndDelimiter,
		String columnsObjectsStartDelimiter,
		String columnsObjectsEndDelimiter,
		String matrixStartDelimiter, 
		String matrixEndDelimiter, 
		String matrixRowSeparator,
		String matrixRowElementsSeparator
	) {
		super(matrixStartDelimiter, matrixEndDelimiter, matrixRowSeparator, matrixRowElementsSeparator);
		
		boolean isInvalid = StringValidator.anyNullOrEmpty(
			rowObjectsStartDelimiter, rowObjectsEndDelimiter, 
			columnsObjectsStartDelimiter, columnsObjectsEndDelimiter
		);
		
		if(isInvalid){
			throw new InvalidMatrixRepresentationDelimiter();
		}
		
		this.rowObjectsStartDelimiter = rowObjectsStartDelimiter;
		this.rowObjectsEndDelimiter = rowObjectsEndDelimiter;
		this.columnsObjectsStartDelimiter = columnsObjectsStartDelimiter;
		this.columnsObjectsEndDelimiter = columnsObjectsEndDelimiter;
	}
	
	//XXX OVERRIDE METHODS
	
	@Override
	public void validate(String matrixString) {
		super.validate(matrixString);
		
		
	}

	//XXX GETTERS
	public String getRowObjectsStartDelimiter() {
		return rowObjectsStartDelimiter;
	}
	
	public String getRowObjectsEndDelimiter() {
		return rowObjectsEndDelimiter;
	}
	
	public String getColumnsObjectsStartDelimiter() {
		return columnsObjectsStartDelimiter;
	}
	
	public String getColumnsObjectsEndDelimiter() {
		return columnsObjectsEndDelimiter;
	}
}
