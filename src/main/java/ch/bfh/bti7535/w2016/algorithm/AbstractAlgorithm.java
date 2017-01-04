package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.data.Document;

import java.util.List;

public abstract class AbstractAlgorithm {

	private int fMeasure;

	public abstract List<Document> execute(List<Document> input);

	public abstract List<Document> execute(List<Document> trainingSet, List<Document> testSet);
}
