package co.l1x.decode.options;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import co.l1x.decode.timestamp.FormatterTimestamp;
import co.l1x.decode.timestamp.Timestamp;
import co.l1x.decode.timestamp.epoch.EpochMilliTimestamp;
import co.l1x.decode.timestamp.epoch.EpochNanoTimestamp;
import co.l1x.decode.util.ToString;
import co.l1x.decode.util.chars.CharArraySequence;

public class TimestampOptions {

	private final Set<String> patterns;
	private final String zone;
	private final Map<CharArraySequence, FormatterTimestamp> patternsMap;

	private transient Calendar contextCalender;
	private transient ZoneId zoneId;

	public TimestampOptions(boolean defaultTimestamps) {

		this(defaultTimestamps ? TimestampConsts.DEFAULT_TIMESTAMP_PATTERNS : new LinkedHashSet<>(), null);
	}

	protected TimestampOptions(Set<String> patterns, String zone) {

		this.patterns = patterns;
		this.zone = zone;

		this.patternsMap = new HashMap<>(patterns.size());

		initializePatterns();
	}

	private void initializePatterns() {

		for (String pattern : patterns()) {

			addPattern(pattern);
		}
	}

	public Set<String> patterns() {

		return (this.patterns != null) ? this.patterns : Collections.emptySet();
	}

	public ZoneId zoneId() {

		if (this.zoneId != null) {
			return this.zoneId;
		}

		this.zoneId = (this.zone != null) ? ZoneId.of(this.zone) : ZoneId.systemDefault();

		return this.zoneId;
	}

	public Calendar contextCalender() {
		return this.contextCalender;
	}

	public Timestamp getPattern(CharArraySequence pattern) {

		if (pattern.contentEquals(EpochNanoTimestamp.EPOCH_NANO)) {
			return EpochNanoTimestamp.Instance;
		}

		if (pattern.contentEquals(EpochMilliTimestamp.EPOCH)) {
			return EpochMilliTimestamp.Instance;
		}

		FormatterTimestamp existing = patternsMap.get(pattern);

		if (existing != null) {
			return existing;
		}

		return addPattern(pattern.asString());
	}

	private FormatterTimestamp addPattern(String pattern) {

		FormatterTimestamp result = new FormatterTimestamp(pattern, contextCalender(), zoneId());

		patternsMap.put(new CharArraySequence(pattern), result);

		return result;
	}

	@Override
	public String toString() {
		return ToString.format("lexerPatterns", patterns.size());
	}
}