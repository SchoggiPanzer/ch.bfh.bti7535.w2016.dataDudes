package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.features.AbstractFeature;
import ch.bfh.bti7535.w2016.filehandling.Classification;

import java.util.ArrayList;
import java.util.List;

public class NaiveBayesAlgorithm extends AbstractAlgorithm {

	private List<AbstractFeature> featurePipeline;
	private List<Document> input;

	@Override
	public List<Document> execute(List<Document> input) {
		this.input = input;
		featurePipeline = getFeaturePipeline();
		train();

		List<Document> results = new ArrayList<>();
		for (Document d : input) {
			Document classified = calculate(d);
			results.add(classified);
		}
		return results;
	}

	private void train() {
		for (AbstractFeature feature : featurePipeline) {
			feature.train(input);
		}
	}

	private Document calculate(Document d) {
		int propInPos = 0;

		for (AbstractFeature feature : featurePipeline) {
			int posProp = feature.getPropability(Classification.SENTIMENT_POSITIVE);
			int posOccurence = feature.getOccurence(Classification.SENTIMENT_POSITIVE);
			//TODO: Not sure if we don't have to calculates opposite
			propInPos += posProp * posOccurence;
		}

		d.setTestResult(propInPos > 0.5 ? Classification.SENTIMENT_POSITIVE : Classification.SENTIMENT_NEGATIVE);
		return d;
	}

	private List<AbstractFeature> getFeaturePipeline() {
		List<AbstractFeature> features = new ArrayList<>();

		return features;
	}

}
