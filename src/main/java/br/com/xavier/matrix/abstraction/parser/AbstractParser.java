package br.com.xavier.matrix.abstraction.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.xavier.matrix.exception.InvalidMatrixRepresentation;
import br.com.xavier.matrix.exception.InvalidMatrixRepresentationDelimiter;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;
import br.com.xavier.matrix.validation.StringValidator;

public abstract class AbstractParser  {
	
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String MATRIX_ELEMENTS_SEPARATOR_BASE_REGEXP = "^([A-Za-z0-9]+)(#[A-Za-z0-9]+)*$";
	
	//XXX CLASS ELEMENTS
	
	private final String representationStartDelimiter; 
	private final String representationEndDelimiter;
	private final String matrixRepresentatitionStartDelimiter;
	private final String matrixRepresentatitionEndDelimiter;
	private final String matrixRepresentationRowSeparator;
	private final String matrixRepresentationRowElementsSeparator;
	
	private Pattern matrixRepresentationPattern;
	private String matrixRowSeparatorRegexPatternString;
	
	//XXX CONSTRUCTOR
	protected AbstractParser(
		String representationStartDelimiter, 
		String representationEndDelimiter, 
		String matrixRepresentatitionStartDelimiter,
		String matrixRepresentatitionEndDelimiter,
		String matrixRepresentationRowSeparator,
		String matrixRepresentationRowElementsSeparator
	) {
		super();
		
		boolean isInvalid = StringValidator.anyNullOrEmpty(
			representationStartDelimiter, 
			representationEndDelimiter, 
			matrixRepresentatitionStartDelimiter,
			matrixRepresentatitionEndDelimiter,
			matrixRepresentationRowSeparator,
			matrixRepresentationRowElementsSeparator
		);
		
		if(isInvalid){
			throw new InvalidMatrixRepresentationDelimiter();
		}
		
		this.representationStartDelimiter = representationStartDelimiter;
		this.representationEndDelimiter = representationEndDelimiter;
		this.matrixRepresentatitionStartDelimiter = matrixRepresentatitionStartDelimiter;
		this.matrixRepresentatitionEndDelimiter = matrixRepresentatitionEndDelimiter;
		this.matrixRepresentationRowSeparator = matrixRepresentationRowSeparator;
		this.matrixRepresentationRowElementsSeparator = matrixRepresentationRowElementsSeparator;
		
		this.matrixRowSeparatorRegexPatternString = Pattern.compile(matrixRepresentationRowSeparator).toString();
		getMatrixRepresentationPattern();
	}
	
	private void getMatrixRepresentationPattern(){
		String regexp = MATRIX_ELEMENTS_SEPARATOR_BASE_REGEXP.replace("#", this.matrixRepresentationRowElementsSeparator);
		this.matrixRepresentationPattern = Pattern.compile(regexp);
	}
	
	//XXX METHODS
	
	public String validate(String representationString) {
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
	private void checkContainsDelimiters(String representationString) {
		if(!representationString.contains(representationStartDelimiter)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!representationString.contains(representationEndDelimiter)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!representationString.contains(matrixRepresentatitionStartDelimiter)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		if(!representationString.contains(matrixRepresentatitionEndDelimiter)){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
	}
	
	private void checkIndexOfDelimiters(String representationString) {
		
		int indexOfRepStartDel = representationString.indexOf(representationStartDelimiter);
		int indexOfRepEndDel = representationString.indexOf(representationEndDelimiter);
		int indexOfRepMatrixStartDel = representationString.indexOf(matrixRepresentatitionStartDelimiter);
		int indexOfRepMatrixEndDel = representationString.indexOf(matrixRepresentatitionEndDelimiter);
		
		//end of representation is before start of representation
		boolean indexError = indexOfRepEndDel < indexOfRepStartDel;
		if(indexError){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		//end of matrix representation is before start of matrix representation
		indexError = indexOfRepMatrixEndDel < indexOfRepMatrixStartDel;
		if(indexError){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		//start of matrix representation is before start of representation
		indexError = indexOfRepMatrixStartDel < indexOfRepStartDel;
		if(indexError){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
		
		//end of matrix representation is before end of representation
		indexError = indexOfRepEndDel < indexOfRepMatrixEndDel;
		if(indexError){
			throw new InvalidMatrixRepresentation(MessageManager.getDefaultMessage(DefaultMessagesKey.INVALID_MATRIX_REPRESENTATION));
		}
	}
	
	//XXX MATRIIX CONTENT METHODS
	
	private String getMatrixContent(String representationString){
		//removes the start and end delimiters
		int indexOfRepStartDel = representationString.indexOf(representationStartDelimiter) + representationStartDelimiter.length();
		int indexOfRepEndDel = representationString.indexOf(representationEndDelimiter);
		
		String strippedStr = representationString.substring(indexOfRepStartDel, indexOfRepEndDel);
		
		int indexOfMatrixStartDel = strippedStr.indexOf(matrixRepresentatitionStartDelimiter) + matrixRepresentatitionStartDelimiter.length();
		int indexOfMatrixEndDel = strippedStr.indexOf(matrixRepresentatitionEndDelimiter);
		
		strippedStr = strippedStr.substring(indexOfMatrixStartDel, indexOfMatrixEndDel);
		
		return strippedStr;
	}
	
	private void checkMatrixContent(String matrixRepresentation){
		if(matrixRepresentation.isEmpty()){
			return;
		}
		
		String toCompareStr = new String(matrixRepresentation);
		
		boolean containsRowSeparator = matrixRepresentation.contains(matrixRepresentationRowSeparator);
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
	
	public String getRepresentationStartDelimiter() {
		return representationStartDelimiter;
	}
	
	public String getRepresentationEndDelimiter() {
		return representationEndDelimiter;
	}
	
	public String getMatrixRepresentatitionStartDelimiter() {
		return matrixRepresentatitionStartDelimiter;
	}
	
	public String getMatrixRepresentatitionEndDelimiter() {
		return matrixRepresentatitionEndDelimiter;
	}
	
	public String getMatrixRepresentationRowSeparator() {
		return matrixRepresentationRowSeparator;
	}
	
	public String getMatrixRepresentationRowElementsSeparator() {
		return matrixRepresentationRowElementsSeparator;
	}
}
