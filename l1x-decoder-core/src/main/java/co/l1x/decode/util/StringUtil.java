package co.l1x.decode.util;

public class StringUtil {
	
	public static final char MINUS = '-';
		
	private static final int MAX_PRINT_LEN = 1024;
	
	public static boolean isNullOrBlank(String val) {
		return ((val == null) || (val.isBlank()));
	}
	
	public static String formatList(Object... values) {
		return ToString.doFormat(false, true, null, values);
	}
			
	public static String print(char[] chars, int start, int len) {
		
		if (chars == null) {
			return null;
		}
		
		if ((start == 0) && (len == 0)) {
			return "";
		}
		
		if ((len < 0) || (len > chars.length)) {
			return "len " + len + " invalid for " + chars.length;
		}
		
		if ((start < 0) || (start >= chars.length)) {
			return "start " + start + " invalid for " + chars.length;
		}
		
		if (start + len > chars.length) {
			return "start " + start + " + len " + len + " invalid for " + chars.length;
		}
		
		return new String(chars, start, Math.min(len, MAX_PRINT_LEN));	
	}
	
	public static boolean regionMatches(char[] chars, int index, String other) {
		
		int len = other.length();
		
		for (int i = 0; i < len; i++) {
						
			char regionChar = chars[index + i];
			char otherChar =  other.charAt(i);
			
			if (regionChar != otherChar) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isNumeral(char c) {
		
		return (c >= '0') && (c <= '9');		
	}

	public static boolean isNegativeNum(char[] content,
		char c, int index, int end) {
		
		if (c != MINUS) {
			return false;
		}
		
		if (index >= end - 1) {
			return false;
		}
		
		char n = content[index + 1];
		
		if (!isNumeral(n)) {
			return false;
		}
		
		return true;
	}
	
	public static char[] getChars(StringBuilder builder) {
		
		char[] result = new char[builder.length()];
		builder.getChars(0, builder.length(), result, 0);	
		
		return result;
	}
	
	public static int stringHash(char[] value, int start, int len) {
		return stringHash(value, start, len, 0);
	}
	
	private static int stringHash(char[] value, int start, int len, int initial) {

		int result = initial;
		
		for (int i = 0; i < len; i++) {
			result = 31 * result + value[start + i];
		}

		return result;
	}
}
