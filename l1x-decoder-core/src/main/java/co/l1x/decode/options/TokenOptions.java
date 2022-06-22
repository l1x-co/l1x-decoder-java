package co.l1x.decode.options;

import co.l1x.decode.util.ToString;

public class TokenOptions {
		
	public static final TokenOptions defaultOptions = new TokenOptions();

	public final String tokenDelims;
	
	public final String stringFormatDelims;
	
	private transient boolean[] tokenDelimsKeys;
	
	private transient String[] tokenDelimsValues;

	public TokenOptions() {
		
		this(TokenConsts.TOKEN_DELIMS, 
			TokenConsts.STRING_FORMAT_DELIMS);
	}
	
	public TokenOptions(String tokenDelims, String stringFormatDelims) {
		
		this.tokenDelims = tokenDelims;
		this.stringFormatDelims = stringFormatDelims;
	}
	
	private void initialize() {
		
		if (this.tokenDelimsKeys != null) {
			return;
		}
		
		synchronized (this) {
			
			if (this.tokenDelimsKeys != null) {
				return;
			}
			
			this.tokenDelimsKeys = new boolean[Byte.MAX_VALUE];
			this.tokenDelimsValues = new String[tokenDelims.length()];
					
			for (int i = 0; i < tokenDelims.length(); i++) {
	
				char delim = tokenDelims.charAt(i);	
				
				if (delim > Byte.MAX_VALUE) {
					throw new IllegalArgumentException("delim: " + delim + " cannot exceed " + Byte.MAX_VALUE);
				}
				
				tokenDelimsKeys[(byte)delim] = true;
				tokenDelimsValues[i] = String.valueOf(delim);
			}
		}
	}
	
	public String tokenDelims() {
		return this.tokenDelims;
	}

	public String stringFormatDelims() {
		return this.stringFormatDelims;
	}
	
	public boolean isTokenDelim(char delim) {		
		
		if (delim >= Byte.MAX_VALUE) {
			return false;
		}
		
		initialize();
		
		return tokenDelimsKeys[(byte)delim];
	}
	
	public boolean isDelimitersOnly(String text) {
		
		for (int i = 0; i < text.length(); i++) {
			
			char c = text.charAt(i);
			
			if (!isTokenDelim(c)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String toString() {
		
		return ToString.format(
			"tokenDelims", tokenDelims.
								replace(System.lineSeparator(), "\\n").
								replace("\t", "\\t")
		);
	}
}
