package co.l1x.decode.util;

public class DigitUtil {
	
	public static int digitValue(char c) {
		return c - '0';
	}
	
	public static int parseInt(final CharSequence arr, int defaultValue) {
		return parseInt(arr, 0, arr.length(), defaultValue);
	}
	
	private static int parseInt(final CharSequence arr, int start, int length,
		int defaultValue) {
			
		int result = 0;
		int sign = 1;

		for (int i = 0; i < length; i++) {
			
			char c = arr.charAt(start + i);

			if (!Character.isDigit(c)) {

				if (i == 0) {

					if (c == '-') {
						sign = -1;
						continue;
					} else {
						return defaultValue;
					}

				} else {
					return defaultValue;
				}
			}

			int digit = digitValue(c);
			
			result *= 10;
			result += digit;
		}

		return result * sign;
	}
	
	public static long parseLong(final char[] arr, int start, int length, long defaultValue) {
		
		long result = 0;
		int sign = 1;

		for (int i = 0; i < length; i++) {
			
			char c = arr[start + i];

			if (!Character.isDigit(c)) {

				if (i == 0) {

					if (c == '-') {
						sign = -1;
						continue;
					} else {
						return defaultValue;
					}

				} else {
					return defaultValue;
				}
			}

			int digit = digitValue(c);
			
			result *= 10;
			result += digit;
		}

		return result * sign;
	}
}
