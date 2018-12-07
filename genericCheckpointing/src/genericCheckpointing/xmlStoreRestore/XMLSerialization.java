package genericCheckpointing.xmlStoreRestore;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import genericCheckpointing.util.Results;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.util.SerializeTypes;

public class XMLSerialization implements SerStrategy {

	Results writer;

	public XMLSerialization() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processInput(SerializableObject sObject) {
		try {
			writer.writeToFile("<DPSerialization>\n");
			writer.writeToFile("  <complexType xsi:type=\"" + sObject.getClass().getName() + "\">\n");
			
			Field[] dataMemebers = sObject.getClass().getDeclaredFields();
			
			for (Field dataMember : dataMemebers) {
				try {
					String fieldName = dataMember.getName();
					Class<?> fieldType = dataMember.getType();
					
					String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
					// reflction
					Object fieldValue = sObject.getClass().getMethod(methodName).invoke(sObject);
					
					String text = SerializeTypes.convertFieldToXML(fieldName, fieldType, fieldValue);
					if (text != null) writer.writeToFile(text);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			writer.writeToFile("  </complexType>\n");
			writer.writeToFile("</DPSerialization>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setWriter(Results writer) {
		this.writer = writer;
	}

}
	