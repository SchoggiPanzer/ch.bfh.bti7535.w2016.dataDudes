package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

/**
 * This abstract class serves as superclass for the features with sentences.
 */
public abstract class SentenceFeature extends AbstractFeature {

	/**
	 * counts all sentences of a list of Documents
	 * @param documents list of documents
	 * @param classification positive or negative
	 * @return the amount of sentences
	 */
	double getSentences(List<Document> documents, Classification classification) {
		double sentencesAmount = 0;
		for (Document doc : documents)
			if (doc.getGoldStandard().equals(classification))
				sentencesAmount += DocumentUtil.countSentences(doc);

		return sentencesAmount;
	}
}
