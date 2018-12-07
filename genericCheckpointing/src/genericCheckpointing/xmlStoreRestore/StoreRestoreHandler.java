package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler {

	String fileName;
	FileProcessor reader;
	Results writer;
	
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		if (m.getName() == "readObj") {
			SerializableObject sObject = null;
			XMLDeserialization XMLDeserialize = new XMLDeserialization();
			XMLDeserialize.setReader(reader);
			
			serializeData(sObject, XMLDeserialize);
			return XMLDeserialize.getsObject();
		} else if (m.getName() == "writeObj") {
			XMLSerialization serialize = new XMLSerialization();
			serialize.setWriter(writer);
			
			serializeData((SerializableObject) args[0], serialize);
		}
		return null;
	}

	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
		sStrategy.processInput(sObject);
	}

	public void setWriter(Results writer2) {
		this.writer = writer2;
	}

	public void setReader(FileProcessor fp) {
		this.reader = fp;
	}
}