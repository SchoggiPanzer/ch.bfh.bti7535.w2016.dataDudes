package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.data.Classification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFeature {
	protected Map<Classification, List<Float>> values;

	public AbstractFeature() {
		values = new HashMap<>();
		List<Float> classValues = new ArrayList<>();
		classValues.add(0f);
		classValues.add(0f);
		values.put(Classification.SENTIMENT_POSITIVE, classValues);
		values.put(Classification.SENTIMENT_NEGATIVE, classValues);
	}

	public float getOccurrence(Classification classification) {
		return values.get(classification).get(0);
	}

	public float getProbability(Classification classification) {
		return values.get(classification).get(1);
	}

	protected void setProbability(Classification classification, float probability) {
		List<Float> classValues = values.get(classification);
		classValues.add(0, probability);
		values.put(classification, classValues);
	}

	protected void setOccurrence(Classification classification, float occurrence) {
		List<Float> classValues = values.get(classification);
		classValues.add(1, occurrence);
		values.put(classification, classValues);
	}

	public abstract void train(List<Document> documents, Classification classification);
}
