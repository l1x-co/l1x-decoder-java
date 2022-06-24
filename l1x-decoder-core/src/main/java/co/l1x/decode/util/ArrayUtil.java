package co.l1x.decode.util;

public class ArrayUtil {

	public static boolean equals(char[] a, int aFromIndex, int aToIndex, char[] b, int bFromIndex, int bToIndex) {

		rangeCheck(a.length, aFromIndex, aToIndex);
		rangeCheck(b.length, bFromIndex, bToIndex);

		int aLength = aToIndex - aFromIndex;
		int bLength = bToIndex - bFromIndex;

		if (aLength != bLength) {
			return false;
		}

		for (int i = 0; i < aLength; i++) {
			if (a[aFromIndex + i] != b[bFromIndex + i]) {
				return false;
			}
		}

		return true;

	}

	private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
		if (fromIndex > toIndex) {
			throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
		}
		if (fromIndex < 0) {
			throw new ArrayIndexOutOfBoundsException(fromIndex);
		}
		if (toIndex > arrayLength) {
			throw new ArrayIndexOutOfBoundsException(toIndex);
		}
	}
}
