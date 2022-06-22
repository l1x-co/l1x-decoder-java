package co.l1x.decode.lexer;

import co.l1x.decode.options.EventEncodeOptions;
import co.l1x.decode.options.TemplateEncodeOptions;
import co.l1x.decode.options.TimestampOptions;
import co.l1x.decode.options.TokenOptions;
import co.l1x.decode.util.StringUtil;
import co.l1x.decode.util.ToString;
import co.l1x.decode.util.chars.CharArraySequence;
import co.l1x.decode.util.chars.CharArraySubSequence;

public abstract class BaseLexer {
	
	private static enum LexerState {
		Initialized,
		Encoding,
		Tokenizing,
		Tokenized;
	}
	
	public static final int SLOT_SIZE = 3;	
	
	public static final int TYPE_SLOT = 0;
	public static final int START_SLOT = 1;
	public static final int END_SLOT = 2;

	private final TokenizeContext tokenizeContext;
	private LexerState lexerState;
	
	protected char[] chars;
	protected int start;
	protected int length;
		
	protected char[] tokens;
	protected int tokenSize;
	
	protected BaseLexer(TokenizeContext tokenizeContext) {
		this.tokenizeContext = tokenizeContext;
		
		this.tokens = new char[SLOT_SIZE * 500];
	}
	
	protected TokenOptions tokenOptions() {
		return tokenizeContext.tokenOptions();
	}
	
	protected TimestampOptions timestampOptions() {
		return tokenizeContext.timestampOptions();
	}
	
	protected TemplateEncodeOptions templateEncodeOptions() {
		return tokenizeContext.templateOptions();
	}
	
	protected EventEncodeOptions eventEncodeOptions() {
		return tokenizeContext.eventOptions();
	}
	
	protected int tokenArraySize() {
		return SLOT_SIZE * this.tokenSizeToEncode();
	}
		
	protected int tokenSizeToEncode() {
		return this.tokenSize;
	}
	
	protected void addToken(TokenType tokenType, int tokenStart, int tokenEnd) {
		
		int slotBase = this.tokenSize * SLOT_SIZE;
		
		if (slotBase == tokens.length) {
			
			char[] newTokens = new char[tokens.length * 2];
			System.arraycopy(this.tokens, 0, newTokens, 0, tokens.length);
			this.tokens = newTokens;	
		}

		this.tokens[slotBase + TYPE_SLOT] =  (char)tokenType.ordinal();
		this.tokens[slotBase + START_SLOT] = (char)(tokenStart - this.start);
		this.tokens[slotBase + END_SLOT] =   (char)(tokenEnd - this.start);			

		this.tokenSize++;
	}
	
	protected int tokenLength(int index) {
		
		int slotBase = index * SLOT_SIZE;
		
		int start = this.tokens[slotBase + START_SLOT];
		int end = this.tokens[slotBase + END_SLOT];
		
		return end - start;
	}
	
	protected int tokenStart(int index) {
		return this.start + this.tokens[(index * SLOT_SIZE) + START_SLOT];
	}
	
	protected TokenType tokenType(int index) {
		return TokenType.values[this.tokens[(index * SLOT_SIZE) + TYPE_SLOT]];
	}
	
	protected boolean tokenize() {
		
		if (this.lexerState == LexerState.Initialized) {
			this.lexerState = LexerState.Tokenizing;
		}
		
		if ((this.lexerState == LexerState.Tokenizing) &&
			(this.tokenSize == 0)) {
			
			processTokens();
		}
	
		return true;
	}
	
	protected abstract void processTokens();
	
	protected boolean initialize(String s) {
		
		this.lexerState = LexerState.Initialized;
		
		this.chars = s.toCharArray();
		this.start = 0;
		this.length = s.length();
		
		this.tokenSize = 0;
		
		return true;
	}
	
	protected boolean shouldInitialize() {
		return (this.lexerState != LexerState.Tokenizing);
	}
	
	private String printTokens() {
		
		StringBuilder result = new StringBuilder().			
			append(System.lineSeparator()).
			append("tokens = ").
			append(tokenSize).
			append(System.lineSeparator());
		
		if (this.tokenSize == 0) {
			
			result.append(String.valueOf((Object)null));
			return result.toString();
		}
		
		for (int i = 0; i < tokenSize; i++) {

			TokenType tokenType = tokenType(i);
			
			int tokenStart = tokenStart(i);
			int tokenLen = tokenLength(i);

			result.
				append(i).
				append(" ").
				append(tokenType).
				append(" {").
				append(tokenStart).
				append(",").
				append(tokenLen).
				append(" = ");
			
			CharArraySequence tokenSequence =
					new CharArraySubSequence(this.chars, tokenStart(i), tokenLength(i));
			
			result.append(tokenSequence.array(), 
				tokenSequence.start(), tokenSequence.length());
			
			result.append("}\n");
		}

		return result.toString();		
	}
	
	@Override
	public String toString() {
		
		String tokens;
		
		try {
			tokens = this.printTokens();
		} catch (Exception e) {
			tokens = e.getMessage();
		}
		
		return ToString.format(
			"chars", "'" + StringUtil.print(chars, start, length) + "'\n",
			"tokens", tokens
		);
	}
}
