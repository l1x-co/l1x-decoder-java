package co.l1x.decode.util.chars;

import java.util.Arrays;

import co.l1x.decode.util.StringUtil;

public class CharArraySequence implements CharSequence, Cloneable {

	protected char[] value;
	private int hashcode;
	
	public CharArraySequence() {
	}
	
	public CharArraySequence(char[] value) {
		reset(value, 0, value.length);
	}
	
	public CharArraySequence(StringBuilder value) {
		reset(value);
	}
	
	protected CharArraySequence(char[] value, int start, int length) {
		reset(value, start, length);
	}
	
	public CharArraySequence(Object o) {
		
		if (o instanceof String) {
			reset((String)o);
		} else if (o instanceof StringBuilder) {
			reset((StringBuilder)o);
		} else if (o instanceof char[]) {
			reset((char[])o);
		} else {
			reset(String.valueOf(o));
		}
	}
		
	public CharArraySequence(String s) {
		reset(s);
	}
	
	public int start() {
		return 0;
	}
	
	@Override
	public int length() {
		return (this.array() != null) ? this.array().length :0;
	}
	
	private void reset(String s) {
		reset(s.toCharArray(), 0, s.length());
	}
	
	private void reset(StringBuilder s) {
		reset(StringUtil.getChars(s));
	}
	
	public void reset(int start, int length) {
		reset(this.array(), start, length);
	}
	
	public void reset(CharArraySequence value) {
		reset(value.array(), value.start(), value.length());
	}
	
	public void reset(char[] value) {
		reset(value, 0, value.length);
	}
	
	/**
	 * @param start  
	 * @param length 
	 */
	public void reset(char[] value, int start, int length) {
		
		this.value = value;
		this.hashcode = 0;
	}
	
	public char[] array() {
		return this.value;
	}

	@Override
	public char charAt(int index) {
		
		return this.array()[this.start() + index];
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		
		return new CharArraySubSequence(this.array(), this.start() + start,
			(end - start));
	}
	
	private boolean equals(CharSequence other) {
				
		if (this.length() != other.length()) {
			return false;
		}
				
		int length = this.length();
		int start = this.start();
		
		for (int i = 0; i < length; i++) {
			
			if (this.array()[start + i] != other.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return contentEquals(obj);
	}
	
	public boolean contentEquals(Object obj) {
		
		if (obj instanceof CharArraySequence) {
			
			CharArraySequence other = (CharArraySequence)obj;
			
			return Arrays.equals(
				this.array(), this.start(), this.start() + this.length(),
				other.array(), other.start(), other.start() + other.length());			
		}
		
		if (obj instanceof char[]) {
			
			char[] array = (char[])obj;
						
			int len = this.length();
			
			if ((len < array.length) || 
				(len > array.length)) {
				
				return false;
			}
			
			int start =  this.start();
			
			return (Arrays.equals(this.array(), start, start + len,
				array, 0, array.length));
		}

		if ((obj instanceof String) && 
			((String)obj).contentEquals(this)) {
				
			return true;
		}
		
		if (obj instanceof CharSequence) {	
			return equals((CharSequence)obj);
		}
		
		
		return false;	
	}

	@Override
	public int hashCode() {
		
		if ((this.hashcode == 0) && (this.length() > 0)) {
			this.hashcode = StringUtil.stringHash(this.array(), start(), length());
		}
		
		return this.hashcode;
	}
	
	@Override
	public CharArraySequence clone() {
	
		int l = this.length();
		
		char[] a = new char[l];
		System.arraycopy(this.array(), this.start(), a, 0, l);
		
		return new CharArraySequence(a);	
	}
	
	public String asString() {
		return new String(this.array(), start(), length());
	}
	
	@Override
	public String toString() {
		return StringUtil.print(this.array(), start(), length());
	}
}