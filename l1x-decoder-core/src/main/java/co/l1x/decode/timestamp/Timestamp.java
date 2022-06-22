package co.l1x.decode.timestamp;

import java.io.Writer;

public abstract class Timestamp {

	private final String pattern;

	protected Timestamp(String pattern) {

		this.pattern = pattern;
	}

	public String pattern() {
		return this.pattern;
	}

	public abstract String format(long epoch);
	public abstract void format(long epoch, Writer writer);

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Timestamp)) {
			return false;
		}

		Timestamp other = (Timestamp) obj;

		if (!pattern.equals(other.pattern)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return pattern.hashCode();
	}

	@Override
	public String toString() {
		return this.pattern;
	}
}
