package co.l1x.decode.template.segment;

import java.io.IOException;
import java.io.Writer;

import co.l1x.decode.event.EventLexer;
import co.l1x.decode.util.ToString;
import co.l1x.decode.util.chars.CharArraySequence;

public class VarSegment extends Segment {

	private final short varIndex;

	public VarSegment(int varIndex) {
		this.varIndex = (short) varIndex;
	}

	@Override
	public SegmentType type() {
		return SegmentType.Var;
	}

	public int varIndex() {
		return this.varIndex;
	}

	@Override
	public void write(Writer writer, EventLexer eventLexer) throws IOException {
		CharArraySequence varValue = eventLexer.varValue(varIndex);

		writer.write(varValue.array(), varValue.start(), varValue.length());
	}

	@Override
	public String toString() {

		return ToString.format(
			"type", type(),
			"index", varIndex());
	}
}
