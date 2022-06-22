package co.l1x.decode.main;

import co.l1x.decode.main.picocli.PicocliStreamDecoder;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "l1x-decode", mixinStandardHelpOptions = true, version = "l1x-decode 0.1.0", description = "Decodes L1x encoded files")
public class DecoderMain {

	public static void main(String[] args) {
		int exitCode = new CommandLine(new PicocliStreamDecoder()).execute(args);
		System.exit(exitCode);
	}
}
