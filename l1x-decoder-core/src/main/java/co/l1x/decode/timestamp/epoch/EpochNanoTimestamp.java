package co.l1x.decode.timestamp.epoch;

import co.l1x.decode.timestamp.Timestamp;

public class EpochNanoTimestamp extends EpochTimestamp {

	public static final Timestamp Instance = new EpochNanoTimestamp();

	public static final String EPOCH_NANO = "epochNano";
	public static final char EPOCH_DIGIT = '.';

	public EpochNanoTimestamp() {
		super(EPOCH_NANO);
	}
}
