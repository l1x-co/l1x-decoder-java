package co.l1x.decode.timestamp.epoch;

import co.l1x.decode.timestamp.Timestamp;

public class EpochMilliTimestamp extends EpochTimestamp {

	public static final Timestamp Instance = new EpochMilliTimestamp();

	public static final String EPOCH = "epoch";

	public EpochMilliTimestamp() {
		super(EPOCH);
	}
}
