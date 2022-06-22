package co.l1x.decode.template.segment;

import java.io.IOException;
import java.io.Writer;

import co.l1x.decode.event.EventLexer;
import co.l1x.decode.util.StringUtil;
import co.l1x.decode.util.ToString;

public class PatternSegment extends Segment {

	private final char[] value;
	private final int start;
	private final short length;

	public PatternSegment(char[] value, int start, int length) {
		this.value = value;
		this.start = start;
		this.length = (short) length;
	}

	@Override
	public SegmentType type() {
		return SegmentType.Pattern;
	}

	@Override
	public void write(Writer writer, EventLexer eventLexer) throws IOException {
		writer.write(value, start, length);
	}

	public char[] value() {
		return this.value;
	}

	public int start() {
		return this.start;
	}

	public int length() {
		return this.length;
	}

	@Override
	public String toString() {

		return ToString.format(
			"type", type(),
			"value", "'" + StringUtil.print(value, start, length) + "'");
	}
}
