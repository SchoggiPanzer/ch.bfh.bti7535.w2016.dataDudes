package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFeature {
	protected Map<Classification, Double> probabilities;

	public AbstractFeature() {
		probabilities = new HashMap<>();
		probabilities.put(Classification.SENTIMENT_POSITIVE, 0.0);
		probabilities.put(Classification.SENTIMENT_NEGATIVE, 0.0);
	}

	protected void setProbability(Classification classification, double probability) {
		probabilities.put(classification, probability);
	}

	public double getProbability(Classification classification) {
		return probabilities.get(classification);
	}

	public void train(List<Document> documents) {
		train(documents, Classification.SENTIMENT_POSITIVE);
		train(documents, Classification.SENTIMENT_NEGATIVE);
	}

	protected abstract void train(List<Document> documents, Classification classification);
}
