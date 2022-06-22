package co.l1x.decode.util;

import java.util.Arrays;

public class ToString {

	public static String formatPrefix(String superValue, Object... values) {
		return doFormat(false, false, superValue, values);
	}

	public static String format(Object... values) {
		return doFormat(false, false, null, values);
	}

	static String doFormat(boolean tabulate, 
		boolean newLines, String prefix, Object... values) {
		
		if (values == null) {
			throw new IllegalArgumentException("values");
		}
		
		if (values.length % 2 != 0) {
			
			throw new IllegalArgumentException("values.length must be even for: " +
				Arrays.toString(values));
		}	
		
		StringBuilder result = new StringBuilder("{");
	
		if (prefix != null) {
			
			result.
				append(prefix).
				append(" ");
		}
		
		for (int i = 0; i < values.length; i += 2) {
			
			String key = String.valueOf(values[i]);
			
			Object rawValue = values[i+1];
			
			String value = (rawValue instanceof Character) ?
				"'" + rawValue + "'" :
				String.valueOf(rawValue);
			
			if ((tabulate) && (i >= 2)) {
				result.append("\t");
			}
			
			result.
				append(key).
				append(": ");
			
			if (tabulate) {
				result.append(value.replace(System.lineSeparator(), ""));
			} else {
				result.append(value);
			}
			
			if (i < values.length - 2) {
				
				if (newLines) {
					result.append(System.lineSeparator());
				} else {
					result.append(", ");
				}
			}
		}
		
		return result.append("}").toString();
	}
}