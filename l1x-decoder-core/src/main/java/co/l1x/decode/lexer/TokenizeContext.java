package co.l1x.decode.lexer;

import co.l1x.decode.options.EventEncodeOptions;
import co.l1x.decode.options.TemplateEncodeOptions;
import co.l1x.decode.options.TimestampOptions;
import co.l1x.decode.options.TokenOptions;

public class TokenizeContext {

	private final TokenOptions tokenOptions;

	private final TimestampOptions timestampOptions;

	private final TemplateEncodeOptions templateOptions;

	private final EventEncodeOptions eventOptions;
	
	private TokenizeContext(TokenOptions tokenOptions, TimestampOptions timestampOptions,
			TemplateEncodeOptions templateOptions, EventEncodeOptions eventOptions) {

		this.tokenOptions = tokenOptions;
		this.timestampOptions = timestampOptions;
		this.templateOptions = templateOptions;
		this.eventOptions = eventOptions;
	}

	public TokenOptions tokenOptions() {
		return this.tokenOptions;
	}

	public TimestampOptions timestampOptions() {
		return this.timestampOptions;
	}

	public TemplateEncodeOptions templateOptions() {
		return this.templateOptions;
	}

	public EventEncodeOptions eventOptions() {
		return this.eventOptions;
	}
	
	public static TokenizeContext create(TokenOptions tokenOptions, TimestampOptions timestampOptions,
			TemplateEncodeOptions templateOptions, EventEncodeOptions eventOptions) {

		return new TokenizeContext(tokenOptions, timestampOptions, templateOptions, eventOptions);
	}
}
