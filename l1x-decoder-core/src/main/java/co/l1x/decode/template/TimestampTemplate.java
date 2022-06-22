package co.l1x.decode.template;

import co.l1x.decode.timestamp.Timestamp;
import co.l1x.decode.util.ToString;

public class TimestampTemplate {

	protected final Timestamp timestamp;

	protected final int startToken;
	protected final int endToken;

	public TimestampTemplate(Timestamp timestamp, int startIndex, int endIndex) {

		this.timestamp = timestamp;
		this.startToken = startIndex;
		this.endToken = endIndex;
	}

	public Timestamp timestamp() {
		return this.timestamp;
	}

	public int tokenLength() {
		return endToken - startToken;
	}

	public int tokenIndex() {
		return this.startToken;
	}

	public String pattern() {
		return timestamp.pattern();
	}

	@Override
	public String toString() {

		return ToString.format(
				"startToken", tokenIndex(),
				"tokenLength", tokenLength(),
				"timestamp", timestamp);
	}
}