package co.l1x.decode.options;

import co.l1x.decode.util.ToString;

public class EventEncodeOptions {

	public static final char ENCODED_LINE_PREFIX = '~';
	public static final char VALUE_SEPERATOR = ' ';

	public static final int VALUE_SEPERATOR_LEN = String.valueOf(VALUE_SEPERATOR).length();

	public final char encodedLinePrefix;
	public final char encodeDelimiter;

	public EventEncodeOptions() {
		this(VALUE_SEPERATOR);
	}

	public EventEncodeOptions(char encodeDelimiter) {
		this((char) 0, encodeDelimiter);
	}

	public EventEncodeOptions(char encodedLinePrefix, char encodeDelimiter) {

		this.encodedLinePrefix = encodedLinePrefix;
		this.encodeDelimiter = encodeDelimiter;
	}

	public char encodedLinePrefix() {
		return this.encodedLinePrefix;
	}

	public char encodeDelimiter() {
		return this.encodeDelimiter;
	}

	@Override
	public String toString() {

		return ToString.format(
			"encodedLinePrefix", encodedLinePrefix,
			"encodeDelimiter", encodeDelimiter);
	}
}
