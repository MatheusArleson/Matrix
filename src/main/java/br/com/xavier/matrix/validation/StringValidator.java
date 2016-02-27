package br.com.xavier.matrix.validation;

public final class StringValidator {
	
	//XXX CONSTRUCTOR
	//defeat instantiation
	public StringValidator() {}
	
	//XXX SINGLE STRING METHODS
	
	public static boolean isNull(String str){
		return str == null;
	}
	
	private static boolean isEmpty(String str){
		return str.isEmpty();
	}
	
	public static boolean isNullOrEmpty(String str){
		return isNull(str) || isEmpty(str);
	}
	
	public static boolean isNotNull(String str){
		return !isNull(str);
	}
	
	public static boolean isNotNullOrEmpty(String str){
		return isNotNull(str) && !isEmpty(str); 
	}

	//XXX MULTIPLE STRINGS METHODS
	
	public static boolean anyNull(String... strings){
		return doCheck(true, false, strings);
	}
	
	public static boolean anyEmpty(String... strings){
		return doCheck(false, true, strings);
	}
	
	public static boolean anyNullOrEmpty(String... strings){
		return doCheck(true, true, strings);
	}
	
	private static boolean doCheck(boolean checkNull, boolean checkEmpty, String... strings){
		if(strings == null){
			return true;
		}
		
		for (String str : strings) {
			if( checkNull && isNull(str) ) { return true; }
			if( checkEmpty && isEmpty(str) ) { return true; }
		}
		
		return false;
	}
	
}
