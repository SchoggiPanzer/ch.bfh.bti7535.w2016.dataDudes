package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.features.AbstractFeature;
import ch.bfh.bti7535.w2016.filehandling.Classification;

import java.util.ArrayList;
import java.util.List;

public class NaiveBayesAlgorithm extends AbstractAlgorithm {

	public static final float SPLIT_POINT = 0.8f;
	private List<AbstractFeature> featurePipeline;

	@Override
	public List<Document> execute(List<Document> input) {
		int listSize = input.size();

		// Very good example to show up how painful Java can be...
		int split = (int) Math.floor(((float) listSize) * SPLIT_POINT);

		List<Document> trainingSet = input.subList(0, split);
		List<Document> testSet = input.subList(split, listSize);
		return execute(trainingSet, testSet);
	}

	@Override
	public List<Document> execute(List<Document> trainingSet, List<Document> testSet) {
		// TODO: Implement dynamic feature set to get best f-measure
		// 1. Setup the feature set
		featurePipeline = getFeaturePipeline();

		// 2. Train based on training documents. This will calculates the feature probabilities
		train(trainingSet);

		// 3. Classify the testset documents based on the trained feature probabilitie
		List<Document> results = new ArrayList<>();
		for (Document d : testSet) {
			Document classified = test(d);
			results.add(classified);
		}
		return results;
	}

	private void train(List<Document> trainingSet) {
		for (AbstractFeature feature : featurePipeline) {
			feature.train(trainingSet);
		}
	}

	private Document test(Document document) {
		int classifiedPositive = 0;
		int classifiedNegative = 0;

		for (AbstractFeature feature : featurePipeline) {
			int posProb = feature.getPropability(Classification.SENTIMENT_POSITIVE);
			int posOccurence = feature.getOccurence(Classification.SENTIMENT_POSITIVE);
			classifiedPositive += posProb * posOccurence;

			int negProb = feature.getPropability(Classification.SENTIMENT_POSITIVE);
			int negOccurence = feature.getOccurence(Classification.SENTIMENT_POSITIVE);
			classifiedNegative += negProb * negOccurence;
		}

		document.setTestResult(classifiedPositive > classifiedNegative ?
				Classification.SENTIMENT_POSITIVE : Classification.SENTIMENT_NEGATIVE);
		return document;
	}

	private List<AbstractFeature> getFeaturePipeline() {
		List<AbstractFeature> features = new ArrayList<>();

		return features;
	}

}
