package co.l1x.decode.main.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.l1x.decode.event.EventLexer;
import co.l1x.decode.lexer.TokenizeContext;
import co.l1x.decode.main.template.TemplateProvider;
import co.l1x.decode.template.Template;

public class StreamDecoder {

	private static final Logger logger = LoggerFactory.getLogger(StreamDecoder.class);

	public static boolean decode(TokenizeContext context, TemplateProvider templateProvider, InputStream in,
			OutputStream out) {

		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, Charset.defaultCharset()));
				BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

			for (String line; (line = br.readLine()) != null;) {

				int index = line.indexOf(' ');

				String hash;
				String encodedLine;

				if (index > -1) {
					hash = line.substring(0, index);
					encodedLine = line.substring(index + 1);
				} else {
					hash = line;
					encodedLine = "";
				}

				String decodedLine = decodeLine(context, templateProvider, hash, encodedLine);

				if (decodedLine != null) {
					writer.write(decodedLine);
				} else {
					writer.write(line);
				}

				writer.newLine();
			}

			return true;

		} catch (Exception e) {
			logger.error("Error while decoding.", e);
			return false;
		}
	}

	private static String decodeLine(TokenizeContext context, TemplateProvider templateProvider, String hash,
			String encodedLine) {

		Template template = templateProvider.getTemplate(hash);
		
		if (template == null) {
			logger.warn("Missing template for {}.", hash);
			return null;
		}
		
		try {
			EventLexer lexer = new EventLexer(context, template);
			return lexer.decode(encodedLine);
			
		} catch (Exception e) {
			logger.error("Failed decoding line {}", encodedLine, e);
			return null;
		}
	}
}
