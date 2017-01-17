package ch.bfh.bti7535.w2016.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class to handle sentiment words
 */
public class SentimentWordsUtil {
	private static Logger log = LoggerFactory.getLogger(SentimentWordsUtil.class);

	private static String POS_WORDS_FILE = "./src/main/resources/wordsentiments/positive-words.txt";
	private static String NEG_WORDS_FILE = "./src/main/resources/wordsentiments/negative-words.txt";

	private List<String> posWords;
	private List<String> negWords;

	public SentimentWordsUtil() {
		posWords = readWords(POS_WORDS_FILE);
		negWords = readWords(NEG_WORDS_FILE);
		// TODO: Find out if this is really necessary
		//normalize();
	}

	/**
	 * read all words from a target file and save them in a list
	 * @param file file with the sentiment words
	 * @return List with the sentiment words
	 */
	private List<String> readWords(String file) {
		List<String> words = new ArrayList<>();
		Path filePath = Paths.get(file);
		try (Stream<String> lines = Files.lines(filePath, Charset.defaultCharset())) {
			lines.forEach(words::add);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return words;
	}

	private void normalize() {
		Collections.shuffle(posWords);
		Collections.shuffle(negWords);

		int smallestSize = CollectionUtil.getSmallestSize(posWords, negWords);

		posWords = posWords.subList(0, smallestSize);
		negWords = negWords.subList(0, smallestSize);
	}

	/**
	 * checks if a word is in positive wordlist
	 * @param word word to search
	 * @return true or false
	 */
	public boolean isPositiveWord(String word) {
		return posWords.contains(word);
	}

	/**
	 * checks if a word is in negative wordlist
	 * @param word word to search
	 * @return true or false
	 */
	public boolean isNegativeWord(String word) {
		return negWords.contains(word);
	}
}
