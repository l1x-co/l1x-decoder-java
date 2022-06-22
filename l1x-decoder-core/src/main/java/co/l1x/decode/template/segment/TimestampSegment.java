package co.l1x.decode.template.segment;

import java.io.IOException;
import java.io.Writer;

import co.l1x.decode.event.EventLexer;
import co.l1x.decode.timestamp.Timestamp;
import co.l1x.decode.util.ToString;

public class TimestampSegment extends Segment {

	private final short timestampIndex;
	private final Timestamp timestamp;

	public TimestampSegment(int timestampIndex, Timestamp timestamp) {
		this.timestampIndex = (short) timestampIndex;
		this.timestamp = timestamp;
	}

	@Override
	public SegmentType type() {
		return SegmentType.Timestamp;
	}

	public int timestampIndex() {
		return this.timestampIndex;
	}

	public Timestamp timestamp() {
		return this.timestamp;
	}

	@Override
	public void write(Writer writer, EventLexer eventLexer) throws IOException {
		long epoch = eventLexer.timestampEpoch(timestampIndex);

		this.timestamp.format(epoch, writer);
	}

	@Override
	public String toString() {

		return ToString.format(
			"type", type(),
			"pattern", "'" + timestamp.pattern() + "'");
	}
}
