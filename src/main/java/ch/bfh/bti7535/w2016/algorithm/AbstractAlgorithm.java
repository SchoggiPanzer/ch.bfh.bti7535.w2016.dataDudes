package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.data.Document;

import java.util.List;

/**
 * abstract class for the algorithm. It's the superclass of the baseline and naivebayes algorithm.
 */
public abstract class AbstractAlgorithm {

	public static final float SPLIT_POINT = 0.8f;

	public abstract List<Document> execute(List<Document> input);

	public abstract List<Document> execute(List<Document> trainingSet, List<Document> testSet);
}
