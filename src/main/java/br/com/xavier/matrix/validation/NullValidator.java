package br.com.xavier.matrix.validation;

import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

public final class NullValidator {
	
	//XXX CONSTRUCTOR
	//defeat instantiation
	public NullValidator() {}
	
	public static void checkNullParameter(Object...objects){
		if(objects == null){
			handleNullParameter();
		}
		
		for (Object object : objects) {
			if(object == null){
				handleNullParameter();
			}
		}
	}

	private static void handleNullParameter() {
		throw new NullPointerException(MessageManager.getDefaultMessage(DefaultMessagesKey.PARAMETER_NULL));
	}
	
}
