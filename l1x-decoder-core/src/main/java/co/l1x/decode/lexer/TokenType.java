package co.l1x.decode.lexer;

public enum TokenType {
	Symbol,
	Var,
	Delim,
	MinusOne;
	
	public static final TokenType[] values = TokenType.values();
}
