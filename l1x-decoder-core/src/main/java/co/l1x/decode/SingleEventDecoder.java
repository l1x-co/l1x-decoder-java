package co.l1x.decode;

import java.io.IOException;

import co.l1x.decode.event.EventLexer;
import co.l1x.decode.lexer.TokenizeContext;
import co.l1x.decode.template.Template;
import co.l1x.decode.template.TemplateLexer;

public class SingleEventDecoder {

	public static String decode(TokenizeContext context, String hash, String input, String pattern) throws IOException {
		TemplateLexer templateLexer = new TemplateLexer(context, hash);

		Template eventVarTemplate = templateLexer.tokenize(pattern);

		EventLexer eventLexer = new EventLexer(context, eventVarTemplate);

		String res = eventLexer.decode(input);

		return res;
	}
}
