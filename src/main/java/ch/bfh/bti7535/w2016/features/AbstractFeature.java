package ch.bfh.bti7535.w2016.features;

import ch.bfh.bti7535.w2016.algorithm.Document;
import ch.bfh.bti7535.w2016.filehandling.Classification;

import java.util.List;

public abstract class AbstractFeature {

	public abstract void train(List<Document> documents);

	public abstract int getOccurrence(Classification classification);

	public abstract int getProbability(Classification classification);
}
