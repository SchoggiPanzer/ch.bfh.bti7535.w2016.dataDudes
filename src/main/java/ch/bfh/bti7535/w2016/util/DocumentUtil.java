package ch.bfh.bti7535.w2016.util;

import ch.bfh.bti7535.w2016.data.Document;

import java.util.List;
import java.util.Map;

/**
 * contains helper methods for documents
 */
public class DocumentUtil {
	private static String[] sentenceEnds = new String[] { ".", "?", "!" };

	/**
	 * counts the total amount of words in a document
	 * @param doc document to count
	 * @return amount of words
	 */
	public static int countWords(Document doc) {
		Map<String, Integer> content = doc.getContentWithWordProperties();

		int matches = 0;
		for (String token : content.keySet()) {
			matches += content.get(token);
		}
		return matches;
	}

	/**
	 * counts a single word in a document
	 * @param doc document to count
	 * @param word the word to search
	 * @return amount of the word
	 */
	public static int countSingleWord(Document doc, String word) {
		Map<String, Integer> content = doc.getContentWithWordProperties();
		return (content.get(word) != null) ? content.get(word) : 0;
	}

	/**
	 * counts the sentences of a list of documents.
	 * @param documents list of documents
	 * @return total amount of sentences
	 */
	public static int countSentences(List<Document> documents) {
		int sum = 0;
		for (Document doc : documents) {
			sum += countSentences(doc);
		}
		return sum;
	}

	/**
	 * counts the sentences of a document
	 * @param doc document to count
	 * @return amount of sentences
	 */
	public static int countSentences(Document doc) {
		Map<String, Integer> content = doc.getContentWithWordProperties();

		int sentences = 0;
		for (String word : content.keySet()) {
			if (CollectionUtil.isStringInList(word, sentenceEnds))
				sentences += content.get(word);
		}

		return sentences;
	}

	/**
	 * count all point sentences in a document
	 * @param doc document to count
	 * @return amount of point sentences
	 */
	public static int countPointSenctences(Document doc) {
		Map<String, Integer> content = doc.getContentWithWordProperties();

		int sentences = 0;
		for (String word : content.keySet()) {
			if (word.contains(sentenceEnds[0]))
				sentences += content.get(word);
		}

		return sentences;
	}

	/**
	 * count all question sentences in a document
	 * @param doc document to count
	 * @return the amount of question sentences
	 */
	public static int countQuestionSenctences(Document doc) {
		Map<String, Integer> content = doc.getContentWithWordProperties();

		int sentences = 0;
		for (String word : content.keySet()) {
			if (word.contains(sentenceEnds[1]))
				sentences += content.get(word);
		}

		return sentences;
	}

	/**
	 * count all exclamation sentences in a document
	 * @param doc document to count
	 * @return amount of exclamation sentences
	 */
	public static int countExclamationSenctences(Document doc) {
		Map<String, Integer> content = doc.getContentWithWordProperties();

		int sentences = 0;
		for (String word : content.keySet()) {
			if (word.contains(sentenceEnds[2]))
				sentences += content.get(word);
		}

		return sentences;
	}
}
