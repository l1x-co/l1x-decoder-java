package co.l1x.decode.timestamp;

import java.io.Writer;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;

import co.l1x.decode.util.EpochUtil;

public class FormatterTimestamp extends Timestamp {
	
	private transient final Calendar calender;

	private transient final ZoneId zoneId;

	private transient DateTimeFormatter formatter;
		
	public FormatterTimestamp(String pattern, Calendar calender, ZoneId zoneId) {
		
		super(pattern);
		
		this.calender = calender;
		this.zoneId = zoneId;	
	}
	
	public DateTimeFormatter formatter() {
		
		if (this.formatter != null) {
			return this.formatter;
		}
		
		synchronized (this) {
			
			if (this.formatter != null) {
				return this.formatter;
			}
			
			DateTimeFormatterBuilder builder =
				new DateTimeFormatterBuilder().appendPattern(pattern());
			
			if (this.calender != null) {
				
				builder.
					parseDefaulting(ChronoField.YEAR_OF_ERA, calender.get(Calendar.YEAR)).
					parseDefaulting(ChronoField.MONTH_OF_YEAR, calender.get(Calendar.MONTH)).
					parseDefaulting(ChronoField.DAY_OF_MONTH, calender.get(Calendar.DAY_OF_MONTH)).
					parseDefaulting(ChronoField.HOUR_OF_DAY, 0).
					parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).
					parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0);
			}
			
			this.formatter = builder.toFormatter().
				withZone(zoneId);
		}
		
		return this.formatter;
	}
	
	@Override
	public String format(long epoch) {
		
		Instant instant = EpochUtil.fromEpoch(epoch);
		String result = formatter().format(instant);
		
		return result;
	}
	
	@Override
	public void format(long epoch, Writer writer) {
		
		Instant instant = EpochUtil.fromEpoch(epoch);
		
		formatter().formatTo(instant, writer);	
	}
}