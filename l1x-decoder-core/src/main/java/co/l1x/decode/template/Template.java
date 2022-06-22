package co.l1x.decode.template;

import co.l1x.decode.template.segment.Segment;
import co.l1x.decode.template.segment.Segment.SegmentType;
import co.l1x.decode.util.StringUtil;

public class Template {

	private final String encodedTokenHash;
	private final Segment[] segments;
	private final int timestampSize;

	public Template(String encodedTokenHash, Segment[] segments) {

		this.encodedTokenHash = encodedTokenHash;
		this.segments = segments;

		this.timestampSize = calcTimestampSize();
	}

	private int calcTimestampSize() {
		int result = 0;

		for (int i = 0; i < segmentSize(); i++) {
			if (segment(i).type() == SegmentType.Timestamp) {
				result++;
			}
		}

		return result;
	}

	public int segmentSize() {
		return (segments == null ? 0 : segments.length);
	}

	public Segment segment(int i) {
		if ((segments == null) ||
			(i < 0) ||
			(i >= segmentSize())) {

			throw new ArrayIndexOutOfBoundsException(i);
		}

		return segments[i];
	}

	public int timestampSize() {
		return timestampSize;
	}

	@Override
	public String toString() {

		return StringUtil.formatList(
			"encodedTokenHash", encodedTokenHash,
			"segmentsSize", (segments != null) ? segments.length : null,
			"segments", Segment.printSegments(segments));
	}
}
