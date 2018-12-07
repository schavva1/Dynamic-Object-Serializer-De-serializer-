package genericCheckpointing.util;

public class SerializeTypes {

	public SerializeTypes() {
		// TODO Auto-generated constructor stub
	}

	public static String convertFieldToXML(String fieldName, Class<?> fieldType, Object fieldValue) {
		if (fieldType == int.class && (int) fieldValue >= 10) {
			return serializeInt(fieldName, fieldValue);
		} else if (fieldType == long.class && (long) fieldValue >= 10) {
			return serializeLong(fieldName, fieldValue);
		} else if (fieldType == double.class && (double) fieldValue >= 10) {
			return serializeDouble(fieldName, fieldValue);
		} else if (fieldType == String.class) {
			return serializeString(fieldName, fieldValue);
		} else if (fieldType == boolean.class) {
			return serializeBoolean(fieldName, fieldValue);
		} else if (fieldType == char.class) {
			return serializeChar(fieldName, fieldValue);
		} else if (fieldType == short.class) {
			return serializeShort(fieldName, fieldValue);
		} else if (fieldType == float.class) {
			return serializeFloat(fieldName, fieldValue);
		} else {
			return null;
		}
	}

	private static String serializeFloat(String fieldName, Object fieldValue) {
		return "    <" + fieldName + "xsi:type=\"xsd:float\">"+(float)fieldValue+"</" + fieldName + ">\n";
	}

	private static String serializeShort(String fieldName, Object fieldValue) {
		return "    <" + fieldName + "xsi:type=\"xsd:short\">"+(short)fieldValue+"</" + fieldName + ">\n";
	}

	private static String serializeChar(String fieldName, Object fieldValue) {
		return "    <" + fieldName + "xsi:type=\"xsd:char\">"+(char)fieldValue+"</" + fieldName + ">\n";
	}

	private static String serializeBoolean(String fieldName, Object fieldValue) {
		return "    <" + fieldName + "xsi:type=\"xsd:boolean\">"+(boolean)fieldValue+"</" + fieldName + ">\n";
	}

	private static String serializeString(String fieldName, Object fieldValue) {
		return "    <" + fieldName + "xsi:type=\"xsd:string\">"+(String)fieldValue+"</" + fieldName + ">\n";
	}

	private static String serializeDouble(String fieldName, Object fieldValue) {
		return "    <" + fieldName + "xsi:type=\"xsd:double\">"+(double)fieldValue+"</" + fieldName + ">\n";
	}

	private static String serializeLong(String fieldName, Object fieldValue) {
		return "    <" + fieldName + "xsi:type=\"xsd:long\">"+(long)fieldValue+"</" + fieldName + ">\n";
	}

	private static String serializeInt(String fieldName, Object fieldValue) {
		return "    <" + fieldName + "xsi:type=\"xsd:int\">"+(int)fieldValue+"</" + fieldName + ">\n";
	}

}
