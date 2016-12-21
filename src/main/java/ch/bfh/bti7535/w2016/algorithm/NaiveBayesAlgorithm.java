package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.features.AbstractFeature;
import ch.bfh.bti7535.w2016.filehandling.Classification;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class NaiveBayesAlgorithm extends AbstractAlgorithm {

	private List<AbstractFeature> featurePipeline;

	@Override
	public List<Document> execute(List<Document> input) {
		throw new NotImplementedException();
	}

	@Override
	public List<Document> execute(List<Document> trainingSet, List<Document> testSet) {
		featurePipeline = getFeaturePipeline();

		train(trainingSet);

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
		int propInPos = 0;

		for (AbstractFeature feature : featurePipeline) {
			int posProp = feature.getPropability(Classification.SENTIMENT_POSITIVE);
			int posOccurence = feature.getOccurence(Classification.SENTIMENT_POSITIVE);
			//TODO: Not sure if we don't have to calculates opposite
			propInPos += posProp * posOccurence;
		}

		document.setTestResult(propInPos > 0.5 ? Classification.SENTIMENT_POSITIVE : Classification.SENTIMENT_NEGATIVE);
		return document;
	}

	private List<AbstractFeature> getFeaturePipeline() {
		List<AbstractFeature> features = new ArrayList<>();

		return features;
	}

}
