package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.SerializableObject;

public interface SerStrategy {
	/**
	 * @param sObject
	 */
	void processInput(SerializableObject sObject);
	
}
