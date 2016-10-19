package ch.bfh.bti7535.w2016.ex1;

import ch.bfh.bti7535.w2016.util.CollectionHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TermFrequencyDistribution {

	public static void main(String[] args) {
		String inName = "hamlet.txt";
		String outName = "/tmp/hamlet.out";
		getTFD(inName, outName);
	}

	private static void getTFD(String inName, String outName) {
		try {
			InputStream inputStream = TermFrequencyDistribution.class.getClassLoader().getResourceAsStream(inName);
			String hamlet = org.apache.commons.io.IOUtils.toString(inputStream, "UTF-8");
			// Do processing...

			Map<String, Integer> wordCount = new HashMap<>();
			for (String word : hamlet.split("\\W+")) {
				if (wordCount.containsKey(word))
					wordCount.put(word, wordCount.get(word) + 1);
				else
					wordCount.put(word, 1);
			}

			Map<String, Integer> orderedByValue = CollectionHelper.sortMapByValue(wordCount, false);

			StringWriter sw = new StringWriter();
			orderedByValue.forEach((k, v) -> {
				sw.append(String.format("%s %s \n", k, v));
			});
			Files.write(Paths.get(outName), sw.toString().getBytes());

			//XXX: Not working correctly, much easier in bash ;-)
			//GnuplotHelper.runPlot(outName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
