package co.l1x.decode.util;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class EpochUtil {

	private static final long JAN_FIRST_2100 = 4102444800000L;
	
	private static boolean isNanoMilli(long epoch) {
		return epoch > JAN_FIRST_2100;
	}
	
	public static Instant fromEpoch(long epoch) {
					
		if (!isNanoMilli(epoch)) {
			return Instant.ofEpochMilli(epoch);
		}
	
		long sec = TimeUnit.NANOSECONDS.toSeconds(epoch);
		long secNanos = TimeUnit.SECONDS.toNanos(sec);
		
		long nanoAdjust = epoch - secNanos; 
		Instant result = Instant.ofEpochSecond(sec, nanoAdjust);
		
		return result;
	}

	public static long combineEpoch(long epochMilli, int nano) {
		
		int times = 1;
		
		while (times <= nano) {
			times *= 10;
		}
		
		return epochMilli * times + nano;
	}
}
