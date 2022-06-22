package com.log10x.l1x.decode.test;

import java.io.IOException;

import co.l1x.decode.SingleEventDecoder;
import co.l1x.decode.lexer.TokenizeContext;
import co.l1x.decode.options.EventEncodeOptions;
import co.l1x.decode.options.TemplateEncodeOptions;
import co.l1x.decode.options.TimestampOptions;
import co.l1x.decode.options.TokenOptions;

public class DecoderTest {

	public static void main(String[] args) throws IOException {
		
		String hash = "$bvKCbEl6WYQ";
		String line = "1441120604000 Paths yxsu ImageNet ILSVRC2012 00000622 JPEG 0 135395 00000669 153355";
		String pattern = "$(yy/MM/dd HH:mm:ss) INFO rdd.BinaryFileRDD: Input split: $://$//dataset//$//$_img_test//$1_test_$.$:$+$,//$7//dataset//$6//$5_img_test//$5_test_$.$4:$3+$";
		
		System.out.println(SingleEventDecoder.decode(context(), hash, line, pattern));
	}

	private static TokenizeContext context() {
		return TokenizeContext.create(
			new TokenOptions(),
			new TimestampOptions(true),
			new TemplateEncodeOptions(),
			new EventEncodeOptions());
	}
}
