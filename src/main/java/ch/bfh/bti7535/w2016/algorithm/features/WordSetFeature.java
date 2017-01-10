package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.CollectionUtil;

import java.util.List;

/**
 * This abstract class serves as superclass for the features with words.
 */
public abstract class WordSetFeature extends AbstractFeature {

	protected String[] wordSetList;

	// Only check sentences with 4 words
	public static final int CUTOFF_DEPTH = 4;

	/**
	 * calculates the occurrence of the words in a document who also are in the word list of the feature
	 * @param doc document to calculate
	 * @return the occurrence
	 */
	protected double calculateOccurrence(Document doc) {
		int occurrence = 0;
		List<String> content = doc.getContent();
		for (int i = 0; i < content.size(); i++) {
			String word = content.get(i);
			occurrence = checkWordOccurrence(occurrence, word);
			for (int j = 1; j <= CUTOFF_DEPTH && i + j < content.size(); j++) {
				word = word + " " + content.get(i + j);
				occurrence = checkWordOccurrence(occurrence, word);
			}
		}
		return occurrence;
	}

	/**
	 * checks if a word of the document is in the set of words from the feature. If yes, then adds the word occurrence
     * to the total word occurrence
	 * @param occurrence total word occurrence
	 * @param word the word to search
	 * @return total word occurrence
	 */
	private int checkWordOccurrence(int occurrence, String word) {
		if (CollectionUtil.isStringInList(word, wordSetList))
			occurrence++;
		return occurrence;
	}

    /**
     *
     * @param documents the list of documents
     * @param classification positive or negative
     * @return
     */
	@Override
	public double train(List<Document> documents, Classification classification) {
		double wordSetOccurrence = 0;
		for (Document doc : documents)
			if (doc.getGoldStandard().equals(classification))
				wordSetOccurrence += calculateOccurrence(doc);

		return wordSetOccurrence;
	}

    /**
     *
     * @param document document to test
     * @return
     */
	@Override
	public double test(Document document) {
		return calculateOccurrence(document);
	}
}
