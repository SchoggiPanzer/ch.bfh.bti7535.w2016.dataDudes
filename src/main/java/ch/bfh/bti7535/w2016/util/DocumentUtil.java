package ch.bfh.bti7535.w2016.util;

import ch.bfh.bti7535.w2016.data.Document;

import java.util.List;

public class DocumentUtil {
	private static String[] sentenceEnds = new String[] { ".", "?", "!" };

	public static int countSentences(List<Document> documents) {
		int sum = 0;
		for (Document doc : documents) {
			sum += countSentences(doc);
		}
		return sum;
	}

	public static int countSentences(Document doc) {
		List<String> content = doc.getContent();

		int sentences = 0;
		for (String word : content) {
			if (CollectionUtil.isStringInList(word, sentenceEnds))
				sentences++;
		}

		return sentences;
	}

	public static int countPointSenctences(Document doc) {
		List<String> content = doc.getContent();

		int sentences = 0;
		for (String word : content) {
			if (word.contains(sentenceEnds[0]))
				sentences++;
		}

		return sentences;
	}

	public static int countQuestionSenctences(Document doc) {
		List<String> content = doc.getContent();

		int sentences = 0;
		for (String word : content) {
			if (word.contains(sentenceEnds[1]))
				sentences++;
		}

		return sentences;
	}

	public static int countExclamationSenctences(Document doc) {
		List<String> content = doc.getContent();

		int sentences = 0;
		for (String word : content) {
			if (word.contains(sentenceEnds[2]))
				sentences++;
		}

		return sentences;
	}

	public static int countSpecificWord(Document doc, String searchWord) {
		List<String> content = doc.getContent();

		int searchWordAmount = 0;
		for (String word : content) {
			if (word.equals(searchWord))
				searchWordAmount++;
		}

		return searchWordAmount;
	}
}
