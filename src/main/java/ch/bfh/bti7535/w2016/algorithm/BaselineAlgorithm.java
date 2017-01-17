package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.SentimentWordsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Baseline algorithm.
 */
public class BaselineAlgorithm extends AbstractAlgorithm {
	private static Logger log = LoggerFactory.getLogger(BaselineAlgorithm.class);

	private SentimentWordsUtil sentimentWordsUtil = new SentimentWordsUtil();

	/**
	 * this method execute the baseline algorithm
	 * @param input list of documents to test
	 * @return list with processed documents
	 */
	@Override
	public List<Document> execute(List<Document> input) {
		List<Document> result = new ArrayList<>();
		for (Document doc : input) {
			result.add(processDocument(doc));
		}
		return result;
	}

	/**
	 * execute the algorithm with trainingsset and testset
	 * @param trainingSet set to train
	 * @param testSet set to test
	 * @return list of documents
	 */
	@Override
	public List<Document> execute(List<Document> trainingSet, List<Document> testSet) {
		trainingSet.addAll(testSet);
		return execute(trainingSet);
	}

	/**
	 * Process a document. This function count the positive and negatives words of the documents. On the base of the
	 * number of positive and negative word, the classification of the document will set as positiv or negative.
	 * @param doc document to process
	 * @return processed document
	 */
	private Document processDocument(Document doc) {
		double posWords = 0.0;
		double negWords = 0.0;

		Map<String, Integer> content = doc.getContentWithWordProperties();
		for (String word : content.keySet()) {
			int occurrence = content.get(word);

			if (sentimentWordsUtil.isPositiveWord(word))
				posWords += occurrence;
			else if (sentimentWordsUtil.isNegativeWord(word))
				negWords += occurrence;
		}

		double total = posWords + negWords;
		if (total > 0) {
			Classification c = (posWords / total > 0.5) ? Classification.SENTIMENT_POSITIVE : Classification.SENTIMENT_NEGATIVE;
			doc.setTestResult(c);
		} else {
			doc.setTestResult(Classification.NOT_CLASSIFIED);
			log.warn("No positive or negative words detected.");
		}
		return doc;
	}

}
