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

	public double getProbability(Classification classification) {
		return probabilities.get(classification);
	}

	public void train(List<Document> documents) {
		double pos = train(documents, Classification.SENTIMENT_POSITIVE);
		double neg = train(documents, Classification.SENTIMENT_NEGATIVE);
		pos = normalize(pos);
		neg = normalize(neg);
		probabilities.put(Classification.SENTIMENT_POSITIVE, pos);
		probabilities.put(Classification.SENTIMENT_NEGATIVE, neg);
	}

	private double normalize(double number) {
		return 1.0 - 1.0 / (1.0 + number);
	}

	protected abstract double train(List<Document> documents, Classification classification);

	public abstract double test(Document document);
}
