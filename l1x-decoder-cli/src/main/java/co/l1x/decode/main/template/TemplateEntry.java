package co.l1x.decode.main.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TemplateEntry {

	private static final Logger logger = LoggerFactory.getLogger(TemplateEntry.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	public final String templateHash;
	public final String template;

	public TemplateEntry() {
		this(null, null);
	}

	public TemplateEntry(String templateHash, String template) {
		this.templateHash = templateHash;
		this.template = template;
	}

	public String toJson() {
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			logger.warn("Failed converting to json - {} - {}", templateHash, template);

			return null;
		}
	}

	public static TemplateEntry fromJson(String s) {
		try {
			return mapper.readValue(s, TemplateEntry.class);
		} catch (JsonProcessingException e) {
			logger.warn("Failed parsing TemplateEntry - {}", s, e);

			return null;
		}
	}
}