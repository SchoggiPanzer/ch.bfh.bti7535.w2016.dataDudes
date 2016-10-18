package ch.bfh.bti7535.w2016.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GnuplotHelper {
	private static Logger log = LoggerFactory.getLogger(GnuplotHelper.class);

	public static void runPlot(String filename) {
		try {
			Runtime commandPrompt = Runtime.getRuntime();
			commandPrompt.exec(String.format("gnuplot %s", filename));
			//commandPrompt.waitFor();
		} catch (IOException e) {
			log.error("Cannot run gnuplot");
		}

	}
}
