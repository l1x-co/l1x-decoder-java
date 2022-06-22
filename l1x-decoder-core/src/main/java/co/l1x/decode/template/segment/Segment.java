package co.l1x.decode.template.segment;

import java.io.IOException;
import java.io.Writer;

import co.l1x.decode.event.EventLexer;
import co.l1x.decode.lexer.TokenType;

public abstract class Segment {

	public enum SegmentType {

		Pattern,
		Var,
		Timestamp;

		public static SegmentType from(TokenType tokenType) {

			switch (tokenType) {
				case Delim:
				case MinusOne:
				case Symbol:
					return Pattern;
				case Var:
					return Var;
			}

			throw new IllegalStateException("Unknown token type " + tokenType);
		}
	}

	public abstract SegmentType type();

	public abstract void write(Writer writer, EventLexer eventLexer) throws IOException;

	public static String printSegments(Segment[] segments) {

		if (segments == null) {
			return null;
		}

		int index = 0;
		StringBuilder result = new StringBuilder("\n\t");

		for (Segment segment : segments) {

			result
				.append(index)
				.append(" = ")
				.append(segment.toString());

			result
				.append((index < segments.length - 1) ? "\n\t" : "");
			
			index++;
		}

		return result.toString();
	}
}
