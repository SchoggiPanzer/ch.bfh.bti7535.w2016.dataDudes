package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.algorithm.features.AbstractFeature;
<<<<<<< HEAD
import ch.bfh.bti7535.w2016.algorithm.features.ExclamationSentenceFeature;
import ch.bfh.bti7535.w2016.algorithm.features.GoodWordSetFeature;
import ch.bfh.bti7535.w2016.algorithm.features.QuestionSentenceFeature;
=======
import ch.bfh.bti7535.w2016.algorithm.features.GoodWordsFeature;
import ch.bfh.bti7535.w2016.algorithm.features.WordFeature;
>>>>>>> add WordFeature and Junit test
import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NaiveBayesAlgorithm extends AbstractAlgorithm {

	public static final float SPLIT_POINT = 0.8f;
	private List<AbstractFeature> featurePipeline;

	@Override
	public List<Document> execute(List<Document> input) {
		int listSize = input.size();
		Collections.shuffle(input);

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

		// 3. Classify the testset documents based on the trained feature probabilities
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
		double classifiedPositive = 0.0;
		double classifiedNegative = 0.0;

		for (AbstractFeature feature : featurePipeline) {
			classifiedPositive += calcProbabilityTimesOccurrence(feature, Classification.SENTIMENT_POSITIVE);
			classifiedNegative += calcProbabilityTimesOccurrence(feature, Classification.SENTIMENT_NEGATIVE);
		}

		document.setTestResult(classifiedPositive > classifiedNegative ?
				Classification.SENTIMENT_POSITIVE : Classification.SENTIMENT_NEGATIVE);
		return document;
	}

	private double calcProbabilityTimesOccurrence(AbstractFeature feature, Classification classification) {
		double probability = feature.getProbability(classification);
		// double occurrence = feature.getOccurrence(classification);

		// FIXME: Calculate the occurrence of the feature
		double occurrence = 1.0;
		return probability * occurrence;
	}

	private List<AbstractFeature> getFeaturePipeline() {
		List<AbstractFeature> features = new ArrayList<>();
		features.add(new QuestionSentenceFeature());
		features.add(new ExclamationSentenceFeature());
		features.add(new GoodWordSetFeature());
		features.add(new WordFeature("good"));
		features.add(new WordFeature("bad"));
		// Add more features here...

		return features;
	}

}
