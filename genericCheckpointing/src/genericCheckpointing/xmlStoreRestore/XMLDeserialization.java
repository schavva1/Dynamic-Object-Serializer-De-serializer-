package genericCheckpointing.xmlStoreRestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import genericCheckpointing.util.DeserializeTypes;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class XMLDeserialization implements SerStrategy {
	FileProcessor reader;
	SerializableObject sObject;
	
	public XMLDeserialization() {
		// TODO Auto-generated constructor stub
	}

	public FileProcessor getReader() {
		return reader;
	}

	public void setReader(FileProcessor reader) {
		this.reader = reader;
	}

	@Override
	public void processInput(SerializableObject sObject) {
		String line = reader.readLine();
		while (line != null) {
			if (line.trim().equalsIgnoreCase("<DPSerialization>")) {
				createObject();
				break;
			}
			line = reader.readLine();
		}
	}

	private void createObject() {
		String line;
		line = reader.readLine();

		String methodName = null;

		while (line != null && !line.trim().equals("</DPSerialization>")) {
			if (line.trim().substring(1, 12).equalsIgnoreCase("complexType")) {
				Matcher m = Pattern.compile("\"([^\"]*)\"").matcher(line);
				if (m.find()) methodName = m.group(1).trim();
				try {
	//reflection
					sObject = (SerializableObject) Class.forName(methodName).newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (!line.trim().substring(2, 13).equalsIgnoreCase("complexType")) {
				String fieldName = null;
				String type = null;
				String value = null;

				Matcher nameMatcher = Pattern.compile("<(.*?)xsi").matcher(line);
				Matcher typeMatcher = Pattern.compile("\"xsd:(.*?)\"").matcher(line);;
				Matcher valueMatcher = Pattern.compile("\\>(.*?)\\<").matcher(line);

				if (nameMatcher.find()) fieldName = nameMatcher.group(1).trim();
				if (typeMatcher.find()) type = typeMatcher.group(1).trim();
				if (valueMatcher.find()) value = valueMatcher.group(1).trim();
// reflection
				try {
					String setMethod = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

					sObject.getClass()
					.getMethod(setMethod, DeserializeTypes.getSignatureType(type))
					.invoke(sObject, DeserializeTypes.getArgValue(type, value));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			line = reader.readLine();
		}
	}

	public SerializableObject getsObject() {
		return sObject;
	}
}
