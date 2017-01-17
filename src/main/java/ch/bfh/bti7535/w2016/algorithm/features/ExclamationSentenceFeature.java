package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

/**
 * this feature checks the documents of exclamation sentences
 */
public class ExclamationSentenceFeature extends SentenceFeature {

	/**
	 * count all exclamation sentences and calculate the probability
	 * @param documents the list of documents
	 * @param classification positive or negative
	 * @return the probability
	 */
	@Override
	public double train(List<Document> documents, Classification classification) {
		double sentences = getSentences(documents, classification);

		double exclamations = 0;
		for (Document doc : documents)
			if (doc.getGoldStandard().equals(classification))
				exclamations += DocumentUtil.countExclamationSenctences(doc);

		return (sentences > 0) ? exclamations / sentences : 0;
	}

	/**
	 * count all exclamation sentences of a document
	 * @param document document to test
	 * @return the amount of sentences
	 */
	@Override public double test(Document document) {
		return DocumentUtil.countExclamationSenctences(document);
	}
}
