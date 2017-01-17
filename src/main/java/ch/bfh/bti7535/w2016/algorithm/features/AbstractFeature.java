package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The AbstractFeature class serves as superclass of the Sentence- and WordSetFeature class.
 */
public abstract class AbstractFeature {
	protected Map<Classification, Double> probabilities;

	public AbstractFeature() {
		probabilities = new HashMap<>();
		probabilities.put(Classification.SENTIMENT_POSITIVE, 0.0);
		probabilities.put(Classification.SENTIMENT_NEGATIVE, 0.0);
	}

    /**
     * Returns the probability of the feature related of the classification
     * @param classification positive or negative
     * @return the probability
     */
	public double getProbability(Classification classification) {
		return probabilities.get(classification);
	}

    /**
     * Trains the feature with a list of documents
     * @param documents the list of documents
     */
	public void train(List<Document> documents) {
		double pos = train(documents, Classification.SENTIMENT_POSITIVE);
		double neg = train(documents, Classification.SENTIMENT_NEGATIVE);
		pos = normalize(pos);
		neg = normalize(neg);
		probabilities.put(Classification.SENTIMENT_POSITIVE, pos);
		probabilities.put(Classification.SENTIMENT_NEGATIVE, neg);
	}

    /**
     * normalizes the number
     * @param number number to normalizes
     * @return the normalized number
     */
	private double normalize(double number) {
		return 1.0 - 1.0 / (1.0 + number);
	}

    /**
     * Abstract method to train the feature with a list of documents related of the classification
     * @param documents the list of documents
     * @param classification positive or negative
     * @return the probability
     */
	protected abstract double train(List<Document> documents, Classification classification);

    /**
     * Abstract method to test a document
     * @param document document to test
     * @return the result
     */
	public abstract double test(Document document);
}
