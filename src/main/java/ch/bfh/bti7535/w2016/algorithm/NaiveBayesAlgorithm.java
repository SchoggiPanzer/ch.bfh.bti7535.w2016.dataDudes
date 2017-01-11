package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.algorithm.features.AbstractFeature;
import ch.bfh.bti7535.w2016.algorithm.features.BadWordSetFeature;
import ch.bfh.bti7535.w2016.algorithm.features.GoodWordSetFeature;
import ch.bfh.bti7535.w2016.algorithm.features.WordCount;
import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Naivebayes algorithm
 */
public class NaiveBayesAlgorithm extends AbstractAlgorithm {

	private List<AbstractFeature> featurePipeline;

	/**
	 *
	 * @param input
	 * @return
	 */
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

	/**
	 *
	 * @param trainingSet
	 * @param testSet
	 * @return
	 */
	@Override
	public List<Document> execute(List<Document> trainingSet, List<Document> testSet) {
		// 1. Setup the feature set
		featurePipeline = getFeaturePipeline();

		// 2. Train based on training documents. This will calculates the feature probabilities
		train(trainingSet);

		// 3. Classify the test set documents based on the trained feature probabilities
		List<Document> results = new ArrayList<>();
		for (Document d : testSet) {
			Document classified = test(d);
			results.add(classified);
		}
		return results;
	}

	/**
	 *
	 * @param trainingSet
	 */
	private void train(List<Document> trainingSet) {
		for (AbstractFeature feature : featurePipeline) {
			feature.train(trainingSet);
		}
	}

	/**
	 *
	 * @param document
	 * @return
	 */
	private Document test(Document document) {
		double classifiedPositive = 0.0;
		double classifiedNegative = 0.0;

		for (AbstractFeature feature : featurePipeline) {
			classifiedPositive += Math.log(testFeature(document, feature, Classification.SENTIMENT_POSITIVE));
			classifiedNegative += Math.log(testFeature(document, feature, Classification.SENTIMENT_NEGATIVE));
		}

		document.setTestResult(classifiedPositive > classifiedNegative ?
				Classification.SENTIMENT_POSITIVE : Classification.SENTIMENT_NEGATIVE);
		return document;
	}

	private double testFeature(Document doc, AbstractFeature feature, Classification classification) {
		double probability = feature.getProbability(classification);
		double testResult = feature.test(doc);
		return Math.pow(probability, testResult);
	}

	/**
	 *
	 * @return
	 */
	private List<AbstractFeature> getFeaturePipeline() {
		List<AbstractFeature> features = new ArrayList<>();
		//features.add(new QuestionSentenceFeature());
		//features.add(new ExclamationSentenceFeature());
		features.add(new GoodWordSetFeature());
		features.add(new BadWordSetFeature());
		features.add(new WordCount());

		return features;
	}

}
