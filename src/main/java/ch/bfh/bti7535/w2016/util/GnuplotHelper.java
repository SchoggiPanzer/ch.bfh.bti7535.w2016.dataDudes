package ch.bfh.bti7535.w2016.util;

import org.apache.log4j.Logger;

import java.io.IOException;

public class GnuplotHelper {
	private static Logger log = Logger.getLogger(GnuplotHelper.class);

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
