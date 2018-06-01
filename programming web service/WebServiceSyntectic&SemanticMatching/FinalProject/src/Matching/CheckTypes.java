/**
 * 
 */
package Matching;

/**
 * @author Chenchen Cheng
 *
 */
public class CheckTypes {

	private static String[] primitives = { "string", "integer", "int", "long", "short", "decimal", "float", "double",
			"boolean", "byte", "QName", "dateTime", "base64Binary", "hexBinary", "unsignedInt", "unsignedShort",
			"unsignedByte", "time", "date", "g", "anySimpleType", "anySimpleType", "duration", "NOTATION" };

	public boolean isPrimitive(String input) {
		for (String type : primitives) {
			if (input.equals(type))
				return true;
		}
		return false;
	}

}
