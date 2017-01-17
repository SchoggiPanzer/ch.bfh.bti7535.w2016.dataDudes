package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

/**
 * this feature checks the documents of question sentences
 */
public class QuestionSentenceFeature extends SentenceFeature {

	/**
	 * count all question sentences and calculate the probability
	 * @param documents the list of documents
	 * @param classification positive or negative
	 * @return
	 */
	@Override
	public double train(List<Document> documents, Classification classification) {
		double sentences = getSentences(documents, classification);

		double questions = 0;
		for (Document doc : documents)
			if (doc.getGoldStandard().equals(classification))
				questions += DocumentUtil.countQuestionSenctences(doc);

		return (sentences > 0) ? questions / sentences : 0;
	}

	/**
	 * count all question sentences of a document
	 * @param document document to test
	 * @return the amount of sentences
	 */
	@Override
	public double test(Document document) {
		return DocumentUtil.countQuestionSenctences(document);
	}
}
