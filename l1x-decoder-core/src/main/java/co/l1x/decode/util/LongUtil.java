package co.l1x.decode.util;

public class LongUtil {
	
	public static int digitLen(long value) {
		
		if (value == 0) {
			return 1;
		}
		
		if (value < 0) {
			throw new IllegalArgumentException(String.valueOf(value));
		}
		
		return (int) (Math.log10(value) + 1 + ((value < 0) ? 1 : 0));
	}

	public static long parse(char[] value, int start, int end, long defaultValue) {
		
		long result = 0;
		
		for (int i = start; i < end; i++) {
			
			char c = value[i];
			
			if (!Character.isDigit(c)) {
				return defaultValue;
			}
			
			result = result * 10 + DigitUtil.digitValue(c);
		}
		
		return result;	
	}
}
