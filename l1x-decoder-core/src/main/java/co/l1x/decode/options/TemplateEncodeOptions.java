package co.l1x.decode.options;

import co.l1x.decode.util.ToString;

public class TemplateEncodeOptions {
		
	private static final String TIMESTAMP_PREFIX = "$(";
	private static final String TIMESTAMP_POSTFIX = ")";

	public static final char VAR_ESCAPE = '/';
	private static final char VAR_PREFIX = '$';
	
	private static final String SINGLE_ESCAPE_STR = String.valueOf(VAR_ESCAPE);
	private static final String DOUBLE_ESCAPE_STR = SINGLE_ESCAPE_STR + SINGLE_ESCAPE_STR;

	private final char varPlaceholder;
	
	private final String timestampPrefix;
	
	private final String timestampPostfix;
	
	private transient String varPlaceholderStr;
	private transient String varPlaceholderEsc;

	public TemplateEncodeOptions() {
		this(VAR_PREFIX, TIMESTAMP_PREFIX, TIMESTAMP_POSTFIX);
	}
	
	private TemplateEncodeOptions(char varPlaceholder, String timestampPrefix, String timestampPostfix) {
		
		this.varPlaceholder = varPlaceholder;

		this.timestampPrefix = timestampPrefix;
		this.timestampPostfix = timestampPostfix;
	}
	
	public char varPlaceholder() {
		return this.varPlaceholder;
	}

	public String timestampPrefix() {
		return this.timestampPrefix;
	}

	public String timestampPostfix() {
		return this.timestampPostfix;
	}
	
	private String varPlaceholderStr() {
		
		if (this.varPlaceholderStr == null) {
			this.varPlaceholderStr = String.valueOf(varPlaceholder);
		}
		
		return this.varPlaceholderStr;	
	}
	
	private String varPlaceholderEsc() {
		
		if (this.varPlaceholderEsc == null) {
			this.varPlaceholderEsc = VAR_ESCAPE + String.valueOf(varPlaceholder);
		}
		
		return this.varPlaceholderEsc;
	}
	
	public String unescape(String pattern) {
		
		return pattern.
			replace(varPlaceholderEsc(), varPlaceholderStr()).
			replace(DOUBLE_ESCAPE_STR, SINGLE_ESCAPE_STR);
	}

	@Override
	public String toString() {
		return ToString.formatPrefix(super.toString(),
			
			"varPlaceholder", varPlaceholder,	
			
			"timestampPrefix", timestampPrefix,	
			"timestampPostfix", timestampPostfix
		);
	}
}
