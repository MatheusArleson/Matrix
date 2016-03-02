package br.com.xavier.matrix.util.messages.enums;

/**
 * Enum holding the keys for the default messages that must be in 
 * the messages file.
 * 
 * @author Matheus Xavier
 *
 */
public enum DefaultMessagesKey {
	
	//XXX ENUM MEMBERS
	MESSAGE_KEY_NOT_FOUND("message.notfound"),
	PARAMETER_NULL("parameter.null"),
	EMPTY_MATRIX("matrix.empty"),
	UNSUPPORTED_OPERATION_EXCEPTION("exception.operation.unsupported"), 
	INVALID_MATRIX_INDEX("matrix.index.invalid"), 
	INVALID_MATRIX_REPRESENTATION("matrix.representation.invalid"),
	ILLEGAL_OBJECT_INDEX("obj.index.illegal");
	
	//XXX ENUM PROPERTIES
	private final String key;
	
	//XXX CONSTRUCTOR
	private DefaultMessagesKey(String key) {
		this.key = key;
	}
	
	//XXX GETTERS
	public String getKey() {
		return key;
	}

}
