package co.l1x.decode.util.chars;

import java.io.CharArrayWriter;
import java.util.Arrays;

public class DirectCharArrayWriter extends CharArrayWriter {

	public void ensureCapacity(int newcount) {
       
		synchronized (lock) {
            if (newcount > buf.length) {
                buf = Arrays.copyOf(buf, Math.max(buf.length << 1, newcount));
            }
        }
    }
	
	public void write(StringBuilder s) {
		write(s, 0, s.length());
	}
	
	public void write(StringBuilder s, int start, int len) {
		
		int currSize = this.size();

		ensureCapacity(currSize + len);

		s.getChars(start, len, buf(), currSize);
		this.count += len;
	}
	
	public DirectCharArrayWriter(int initialSize) {
		super(initialSize);
	}
	
	public char[] buf() {
		return this.buf;
	}	
}
