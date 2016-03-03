package br.com.xavier.matrix.abstraction.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.exception.InvalidMatrixRepresentationDelimiter;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;
import br.com.xavier.matrix.validation.MatrixValidator;
import br.com.xavier.matrix.validation.StringValidator;

public abstract class AbstractParser  {
	
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String MATRIX_ELEMENTS_SEPARATOR_BASE_REGEXP = "^([A-Za-z0-9]+)(#[A-Za-z0-9]+)*$";
	
	//XXX CLASS ELEMENTS
	
	private final String matrixRepresentationStartDelimiter; 
	private final String matrixRepresentationEndDelimiter;
	private final String matrixRepresentatitionMatrixStartDelimiter;
	private final String matrixRepresentatitionMatrixEndDelimiter;
	private final String matrixRepresentationMatrixRowSeparator;
	private final String matrixRepresentationMatrixRowElementsSeparator;
	
	private Pattern matrixRepresentationPattern;
	private String matrixRowSeparatorRegexPatternString;
	
	//XXX CONSTRUCTOR
	protected AbstractParser(
		String matrixRepresentationStartDelimiter, 
		String matrixRepresentationEndDelimiter, 
		String matrixRepresentatitionMatrixStartDelimiter,
		String matrixRepresentatitionMatrixEndDelimiter,
		String matrixRepresentationMatrixRowSeparator,
		String matrixRepresentationMatrixRowElementsSeparator
	) {
		super();
		
		boolean isInvalid = StringValidator.anyNullOrEmpty(
			matrixRepresentationStartDelimiter, 
			matrixRepresentationEndDelimiter, 
			matrixRepresentatitionMatrixStartDelimiter,
			matrixRepresentatitionMatrixEndDelimiter,
			matrixRepresentationMatrixRowSeparator,
			matrixRepresentationMatrixRowElementsSeparator
		);
		
		if(isInvalid){
			throw new InvalidMatrixRepresentationDelimiter();
		}
		
		this.matrixRepresentationStartDelimiter = matrixRepresentationStartDelimiter;
		this.matrixRepresentationEndDelimiter = matrixRepresentationEndDelimiter;
		this.matrixRepresentatitionMatrixStartDelimiter = matrixRepresentatitionMatrixStartDelimiter;
		this.matrixRepresentatitionMatrixEndDelimiter = matrixRepresentatitionMatrixEndDelimiter;
		this.matrixRepresentationMatrixRowSeparator = matrixRepresentationMatrixRowSeparator;
		this.matrixRepresentationMatrixRowElementsSeparator = matrixRepresentationMatrixRowElementsSeparator;
		
		this.matrixRowSeparatorRegexPatternString = Pattern.compile(matrixRepresentationMatrixRowSeparator).toString();
		getMatrixRepresentationPattern();
	}
	
	private void getMatrixRepresentationPattern(){
		String regexp = MATRIX_ELEMENTS_SEPARATOR_BASE_REGEXP.replace("#", this.matrixRepresentationMatrixRowElementsSeparator);
		this.matrixRepresentationPattern = Pattern.compile(regexp);
	}
	
	//XXX METHODS
	
	public String validateMatrixRepresentation(String representationString) {
		if(StringValidator.isNullOrEmpty(representationString)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		checkContainsDelimiters(representationString);
		checkIndexOfDelimiters(representationString);
		
		String matrixContents = getMatrixContent(representationString);
		if(!matrixContents.isEmpty()){
			checkMatrixContent(matrixContents);
		}
		
		return matrixContents;
	}

	//XXX REPRESENTATION METHODS
	public void checkContainsDelimiters(String representationString) {
		MatrixValidator.checkContains(representationString, matrixRepresentationStartDelimiter);
		MatrixValidator.checkContains(representationString, matrixRepresentationEndDelimiter);
		MatrixValidator.checkContains(representationString, matrixRepresentatitionMatrixStartDelimiter);
		MatrixValidator.checkContains(representationString, matrixRepresentatitionMatrixEndDelimiter);
	}
	
	public void checkIndexOfDelimiters(String representationString) {
		
		int indexOfRepStartDel = representationString.indexOf(matrixRepresentationStartDelimiter);
		int indexOfRepEndDel = representationString.indexOf(matrixRepresentationEndDelimiter);
		int indexOfRepMatrixStartDel = representationString.indexOf(matrixRepresentatitionMatrixStartDelimiter);
		int indexOfRepMatrixEndDel = representationString.indexOf(matrixRepresentatitionMatrixEndDelimiter);
		
		//end of representation is before start of representation
		MatrixValidator.checkDelimiterIsBefore(indexOfRepEndDel, indexOfRepStartDel);
		
		//end of matrix representation is before start of matrix representation
		MatrixValidator.checkDelimiterIsBefore(indexOfRepMatrixEndDel, indexOfRepMatrixStartDel);
		
		//start of matrix representation is before start of representation
		MatrixValidator.checkDelimiterIsBefore(indexOfRepMatrixStartDel, indexOfRepStartDel);
		
		//end of matrix representation is before end of representation
		MatrixValidator.checkDelimiterIsBefore(indexOfRepEndDel, indexOfRepMatrixEndDel);
	}
	
	//XXX MATRIIX CONTENT METHODS
	
	public String getMatrixContent(String representationString){
		//removes the start and end delimiters
		int indexOfRepStartDel = representationString.indexOf(matrixRepresentationStartDelimiter) + matrixRepresentationStartDelimiter.length();
		int indexOfRepEndDel = representationString.indexOf(matrixRepresentationEndDelimiter);
		
		String strippedStr = representationString.substring(indexOfRepStartDel, indexOfRepEndDel);
		
		int indexOfMatrixStartDel = strippedStr.indexOf(matrixRepresentatitionMatrixStartDelimiter) + matrixRepresentatitionMatrixStartDelimiter.length();
		int indexOfMatrixEndDel = strippedStr.indexOf(matrixRepresentatitionMatrixEndDelimiter);
		
		strippedStr = strippedStr.substring(indexOfMatrixStartDel, indexOfMatrixEndDel);
		
		return strippedStr;
	}
	
	public void checkMatrixContent(String matrixRepresentation){
		if(matrixRepresentation.isEmpty()){
			return;
		}
		
		String toCompareStr = new String(matrixRepresentation);
		
		boolean containsRowSeparator = matrixRepresentation.contains(matrixRepresentationMatrixRowSeparator);
		if(containsRowSeparator){ 
			toCompareStr = toCompareStr.replaceAll(matrixRowSeparatorRegexPatternString, "");
		}
		
		boolean result = checkAgainstMatrixRepresentationPattern(toCompareStr);
		if(!result){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION)); 
		}
	}
	
	private boolean checkAgainstMatrixRepresentationPattern(String matrixRepresentation){
		Matcher matcher = this.matrixRepresentationPattern.matcher(matrixRepresentation);
		return matcher.matches();
	} 
	
	//XXX GETTERS
	
	public static String getLineseparator() {
		return LINE_SEPARATOR;
	}
	
	public String getMatrixRepresentationStartDelimiter() {
		return matrixRepresentationStartDelimiter;
	}
	
	public String getMatrixRepresentationEndDelimiter() {
		return matrixRepresentationEndDelimiter;
	}
	
	public String getMatrixRepresentatitionMatrixStartDelimiter() {
		return matrixRepresentatitionMatrixStartDelimiter;
	}
	
	public String getMatrixRepresentatitionMatrixEndDelimiter() {
		return matrixRepresentatitionMatrixEndDelimiter;
	}
	
	public String getMatrixRepresentationMatrixRowSeparator() {
		return matrixRepresentationMatrixRowSeparator;
	}
	
	public String getMatrixRepresentationMatrixRowElementsSeparator() {
		return matrixRepresentationMatrixRowElementsSeparator;
	}
}
