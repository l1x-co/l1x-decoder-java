package co.l1x.decode.main.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.l1x.decode.lexer.TokenizeContext;
import co.l1x.decode.template.Template;
import co.l1x.decode.template.TemplateLexer;
import co.l1x.decode.util.StringUtil;

public class TemplateProvider {
	private static final Logger logger = LoggerFactory.getLogger(TemplateProvider.class);

	private final TokenizeContext context;
	private final Map<String, String> rawTemplates;
	private final Map<String, Template> templates;

	public TemplateProvider(TokenizeContext context, Map<String, String> rawTemplates) {
		this.context = context;
		this.rawTemplates = rawTemplates;
		this.templates = new ConcurrentHashMap<>();
	}

	public boolean addRawTemplate(String hash, String rawTemplate) {

		String existingRawTemplate = this.rawTemplates.get(hash);

		if (existingRawTemplate != null) {
			if (!existingRawTemplate.equals(rawTemplate)) {
				logger.warn("Attempted to replace template {} from '{}' to '{}'", hash, existingRawTemplate,
						rawTemplate);
			}

			return false;
		}

		synchronized (this.rawTemplates) {
			existingRawTemplate = this.rawTemplates.get(hash);

			if (existingRawTemplate != null) {
				if (!existingRawTemplate.equals(rawTemplate)) {
					logger.warn("Race for template {} from '{}' to '{}'", hash, existingRawTemplate, rawTemplate);
				}

				return false;
			}

			this.rawTemplates.put(hash, rawTemplate);

			return true;
		}
	}

	public Template getTemplate(String hash) {
		Template result = this.templates.get(hash);

		if (result != null) {
			return result;
		}

		return addTemplate(hash);
	}

	private Template addTemplate(String hash) {
		Template result;

		synchronized (this.templates) {

			result = templates.get(hash);

			if (result == null) {
				result = createTemplate(hash);

				if (result != null) {
					this.templates.put(hash, result);
				}
			}
		}

		return result;
	}

	private Template createTemplate(String hash) {
		String rawTemplate = rawTemplates.get(hash);

		if (StringUtil.isNullOrEmpty(rawTemplate)) {
			logger.warn("No raw template found for {}.", hash);

			return null;
		}

		try {
			TemplateLexer lexer = new TemplateLexer(context, hash);
			return lexer.tokenize(rawTemplate);
		} catch (Exception e) {
			logger.error("Failed to build template {} - {}", hash, rawTemplate, e);

			return null;
		}
	}

	public static TemplateProvider fromFile(TokenizeContext context, String filename) {
		return fromFile(context, filename, true);
	}

	public static TemplateProvider fromFile(TokenizeContext context, String filename, boolean warnDuplicates) {
		return new TemplateProvider(context, getEntriesMap(filename, warnDuplicates));
	}

	private static Map<String, String> getEntriesMap(String filename, boolean warnDuplicates) {
		Map<String, String> result = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for (String line; (line = br.readLine()) != null;) {
				TemplateEntry entry;

				try {
					entry = TemplateEntry.fromJson(line);
				} catch (Exception e) {
					logger.warn("Non matching template entry line - {}.", line, e);
					continue;
				}

				if ((warnDuplicates) && (result.containsKey(entry.templateHash))) {
					logger.warn("Encountered duplicate template hash {} in file {}.", entry.templateHash, filename);
					continue;
				}

				result.put(entry.templateHash, entry.template);
			}
		} catch (Exception e) {
			logger.warn("Error while reading template entries from file {}.", filename, e);
		}

		return result;
	}
}
