package br.com.xavier.matrix.validation;

import br.com.xavier.matrix.exception.IllegalObjectIndex;
import br.com.xavier.matrix.interfaces.indexed.ObjectIndexedMatrix;
import br.com.xavier.matrix.util.messages.MessageManager;
import br.com.xavier.matrix.util.messages.enums.DefaultMessagesKey;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class ObjectIndexValidator {
	
	//XXX CONSTRUCTOR
	//defeat instantiation
	public ObjectIndexValidator() {}

	public static void checkIllegalRowObjectIndex(ObjectIndexedMatrix oim, Object...objects){
		NullValidator.checkNullParameter(oim);
		NullValidator.checkNullParameter(objects);
		
		for (Object objIndex : objects) {
			boolean isRowObject = oim.isRowObject(objIndex);
			if(isRowObject){
				continue;
			} else {
				handleIllegalObjectIndex();
			}
		}
	}
	
	public static void checkIllegalColumnObjectIndex(ObjectIndexedMatrix oim, Object...objects){
		NullValidator.checkNullParameter(oim);
		NullValidator.checkNullParameter(objects);
		
		for (Object objIndex : objects) {
			boolean isColumnObject = oim.isColumnObject(objIndex);
			if(isColumnObject){
				continue;
			} else {
				handleIllegalObjectIndex();
			}
		}
	}
	
	public static void checkIllegalColumnRowObjectIndex(ObjectIndexedMatrix oim, Object...objects){
		NullValidator.checkNullParameter(oim);
		NullValidator.checkNullParameter(objects);
		
		for (Object objIndex : objects) {
			boolean isRowObject = oim.isRowObject(objIndex);
			boolean isColumnObject = oim.isColumnObject(objIndex);
			if(isRowObject && isColumnObject){
				continue;
			} else {
				handleIllegalObjectIndex();
			}
		}
	}

	private static void handleIllegalObjectIndex() {
		throw new IllegalObjectIndex(MessageManager.getDefaultMessage(DefaultMessagesKey.ILLEGAL_OBJECT_INDEX));
	}
	
}
