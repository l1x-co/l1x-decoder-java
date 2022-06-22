package co.l1x.decode.util.chars;

public class CharArraySubSequence extends CharArraySequence {

	private int start;
	private int length;
	
	public CharArraySubSequence() {
	}
	
	public CharArraySubSequence(char[] value) {
		reset(value, 0, value.length);
	}
	
	public CharArraySubSequence(StringBuilder value) {
		super(value);
	}
		
	public CharArraySubSequence(char[] value, int start, int length) {
		reset(value, start, length);
	}
	
	public CharArraySubSequence(Object o) {
		super(o);
	}
	
	public CharArraySubSequence(String s) {
		super(s);
	}
	
	@Override
	public void reset(char[] value, int start, int length) {
		
		super.reset(value, start, length);
		this.start = start;
		this.length = length;
	}
	
	@Override
	public int length() {
		return this.length;
	}
	
	@Override
	public int start() {
		return this.start;
	}
}