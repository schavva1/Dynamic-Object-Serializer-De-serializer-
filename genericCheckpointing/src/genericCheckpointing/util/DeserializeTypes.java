package genericCheckpointing.util;

public class DeserializeTypes {

	public DeserializeTypes() {
		// TODO Auto-generated constructor stub
	}

	public static Class<?> getSignatureType(String type) {

		switch(type) {
		case "int" : return int.class;
		case "char" : return char.class;
		case "string" : return String.class;
		case "boolean" : return boolean.class;
		case "float" : return float.class;
		case "double" : return double.class;
		case "long" : return long.class;
		case "short" : return short.class;
		}
		return null;
	}

	public static Object getArgValue(String type, String value) {

		switch(type) {
		case "int" : return new Integer(Integer.parseInt(value));
		case "char" : return new Character(value.charAt(0));
		case "string" : return value;
		case "boolean" : return Boolean.valueOf(value);
		case "float" : return Float.parseFloat(value);
		case "double" : return Double.parseDouble(value);
		case "long" : return Long.parseLong(value);
		case "short" : return Short.parseShort(value);
		}
		return null;
	}
}
