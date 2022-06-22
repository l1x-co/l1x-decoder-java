package co.l1x.decode.options;

public class TokenConsts {

	public static final char LT_DELIM = '<';
	public static final char GT_DELIM = '>';
	public static final char PIPE_DELIM = '|';
	public static final char DOT_DELIM = '.';
	public static final char SPACE_DELIM = ' ';
	public static final char CLOSE_RECT_BRACKET_DELIM = ']';
	public static final char OPEN_RECT_BRACKET_DELIM = '[';
	public static final char COLON_DELIM = ':';
	public static final char DASH_DELIM = '-';
	public static final char PLUS_DELIM = '+';
	public static final char SLASH_DELIM = '/';
	public static final char ASTERISK_DELIM = '*';
	public static final char EQUAL_DELIM = '=';
	public static final char BACKSLASH_DELIM = '\\';
	public static final char COMMA_DELIM = ',';
	public static final char CURL_BRACKET_OPEN_DELIM = '{';
	public static final char CURL_BRACKET_CLOSE_DELIM = '}';
	public static final char UNDERSCORE_DELIM = '_';
	public static final char BRACKET_OPEN_DELIM = '(';
	public static final char BRACKET_CLOSE_DELIM = ')';
	public static final char SEMICOLON_DELIM = ';';
	public static final char SINGLE_QUOTE_DELIM = '\'';
	public static final char DOUBLE_QUOTE_DELIM = '"';
	public static final char DOLLAR_SIGN_DELIM = '$';

	public static final char TEMPLATE_VAR = '$';
	public static final String TEMPLATE_VAR_STR = String.valueOf(TEMPLATE_VAR);

	public static final String MINUS_ONE = "-1";

	public static final char TAB = '\t';
	public static final char BREAK = '\n';

	public static final String TOKEN_DELIMS = new String(new char[] {
		LT_DELIM,
		GT_DELIM,
		PIPE_DELIM,
		DOT_DELIM,
		SPACE_DELIM,
		CLOSE_RECT_BRACKET_DELIM,
		OPEN_RECT_BRACKET_DELIM,
		COLON_DELIM,
		DASH_DELIM,
		PLUS_DELIM,
		SLASH_DELIM,
		ASTERISK_DELIM,
		EQUAL_DELIM,
		BACKSLASH_DELIM,
		COMMA_DELIM,
		CURL_BRACKET_OPEN_DELIM,
		CURL_BRACKET_CLOSE_DELIM,
		UNDERSCORE_DELIM,
		BRACKET_OPEN_DELIM,
		BRACKET_CLOSE_DELIM,
		SEMICOLON_DELIM,
		SINGLE_QUOTE_DELIM,
		DOUBLE_QUOTE_DELIM,
		DOLLAR_SIGN_DELIM,
		TAB,
		BREAK
	});

	public final static String STRING_FORMAT_DELIMS = "%{";

	public static final char STAR_DATE_DELIM = '*';

	public static boolean isMinusOne(char[] chars, int start, int length) {

		return ((start < length - 1) &&
				(chars[start] == '-') &&
				(chars[start + 1] == '1'));
	}
}
