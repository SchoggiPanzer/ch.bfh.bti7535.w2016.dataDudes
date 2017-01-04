package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.data.Classification;

import java.util.ArrayList;
import java.util.List;

public class MockAlgorithm extends AbstractAlgorithm {
	private List<Document> docs = new ArrayList<>();

	@Override
	public List<Document> execute(List<Document> input) {
		return docs;
	}

	@Override
	public List<Document> execute(List<Document> trainingSet, List<Document> testSet) {
		return null;
	}

	public void addTruePos(int x) {
		for (int i = 0; i < x; i++) {
			this.addTruePos();
		}
	}

	public void addTruePos() {
		this.docs.add(makeTruePos());
	}

	public void addTrueNeg(int x) {
		for (int i = 0; i < x; i++) {
			this.addTrueNeg();
		}
	}

	public void addTrueNeg() {
		this.docs.add(makeTrueNeg());
	}

	public void addFalseNeg(int x) {
		for (int i = 0; i < x; i++) {
			this.addFalseNeg();
		}
	}

	public void addFalseNeg() {
		this.docs.add(makeFalseNeg());
	}

	public void addFalsePos(int x) {
		for (int i = 0; i < x; i++) {
			this.addFalsePos();
		}
	}

	public void addFalsePos() {
		this.docs.add(makeFalsePos());
	}

	public Document makeTruePos() {
		Document doc = new Document();
		doc.setTestResult(Classification.SENTIMENT_POSITIVE);
		doc.setGoldStandard(Classification.SENTIMENT_POSITIVE);
		return doc;
	}

	public Document makeTrueNeg() {
		Document doc = new Document();
		doc.setTestResult(Classification.SENTIMENT_NEGATIVE);
		doc.setGoldStandard(Classification.SENTIMENT_NEGATIVE);
		return doc;
	}

	public Document makeFalsePos() {
		Document doc = new Document();
		doc.setTestResult(Classification.SENTIMENT_POSITIVE);
		doc.setGoldStandard(Classification.SENTIMENT_NEGATIVE);
		return doc;
	}

	public Document makeFalseNeg() {
		Document doc = new Document();
		doc.setTestResult(Classification.SENTIMENT_NEGATIVE);
		doc.setGoldStandard(Classification.SENTIMENT_POSITIVE);
		return doc;
	}
}
