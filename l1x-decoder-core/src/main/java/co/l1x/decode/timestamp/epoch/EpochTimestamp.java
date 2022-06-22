package co.l1x.decode.timestamp.epoch;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;

import co.l1x.decode.timestamp.Timestamp;

public abstract class EpochTimestamp extends Timestamp {

	EpochTimestamp(String pattern) {
		super(pattern);
	}

	@Override
	public void format(long epoch, Writer writer) {
		try {
			writer.write(format(epoch));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	@Override
	public String format(long epoch) {
		return Long.toString(epoch);
	}
}
