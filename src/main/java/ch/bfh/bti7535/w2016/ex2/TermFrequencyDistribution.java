package ch.bfh.bti7535.w2016.ex2;

import ch.bfh.bti7535.w2016.util.GnuplotHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TermFrequencyDistribution {

	public static void main(String[] args) {
		String inName = "hamlet.txt";
		String outName = "/tmp/hamlet.out";

		try {
			InputStream inputStream = TermFrequencyDistribution.class.getClassLoader().getResourceAsStream(inName);
			String hamlet = org.apache.commons.io.IOUtils.toString(inputStream, "UTF-8");
			// Do processing...

			String[] splitStr = hamlet.split("\\W+");
			Map<String, Integer> wordCount = new HashMap<>();

			for (String word : splitStr) {
				if (wordCount.containsKey(word))
					wordCount.put(word, wordCount.get(word) + 1);
				else
					wordCount.put(word, 1);
			}

			String out = "";
			for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
				out += entry.getKey() + " " + entry.getValue() + "\n";
			}
			Files.write(Paths.get(outName), out.getBytes());

			//TODO: Not working correctly, much easier in bash ;-)
			GnuplotHelper.runPlot(outName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
