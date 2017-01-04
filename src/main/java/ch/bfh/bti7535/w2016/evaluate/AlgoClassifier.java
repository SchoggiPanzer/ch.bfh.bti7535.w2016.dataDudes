package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.util.FileReaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class AlgoClassifier {

	private static Logger log = (Logger) LoggerFactory.getLogger(AlgoClassifier.class);
	private static double DELTA = 0.0001;

	private double truePos = 0;
	private double trueNeg = 0;
	private double falsePos = 0;
	private double falseNeg = 0;

	public AlgoClassifier(AbstractAlgorithm algo) {
		try {
			List<Document> inputdocs = FileReaderUtil.readFilesFromPath(FileReaderUtil.FILE_PATH);
			List<Document> results = algo.execute(inputdocs);
			classify(results);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		}
	}

	protected void classify(List<Document> docs) {
		for (Document doc : docs) {
			classifySingleDoc(doc);
		}
	}

	private void classifySingleDoc(Document doc) {
		Classification gold = doc.getGoldStandard();
		Classification calculated = doc.getTestResult();

		// Does handle NOT_CLASSIFIED as SENTIMENT_NEGATIVE
		if (gold == calculated) {
			//true
			if (gold == Classification.SENTIMENT_POSITIVE)
				truePos++;
			else
				trueNeg++;
		} else {
			//false
			if (calculated == Classification.SENTIMENT_POSITIVE)
				falsePos++;
			else
				falseNeg++;
		}
	}

	public double getPrecision() {
		double precision = 0.0;

		if (truePos + falsePos > DELTA)
			precision = truePos / (truePos + falsePos);

		return precision;
	}

	public double getRecall() {
		double recall = 0.0;

		if (truePos + falseNeg > DELTA)
			recall = truePos / (truePos + falseNeg);

		return recall;
	}

	public double getFmesure() {
		return getFmesure(1.0);
	}

	public double getFmesure(double beta) {
		double precision = this.getPrecision();
		double recall = this.getRecall();

		double fMeasure = 0.0;
		double denominator = beta * beta * precision + recall;

		if (beta < DELTA && beta != 0)
			throw new IllegalArgumentException("Beta can not be below 0");

		if (Math.abs(denominator) > DELTA)
			fMeasure = (1 + beta * beta) * precision * recall / denominator;

		return fMeasure;
	}

	public double getAccuracy() {
		double accuracy = 0.0;
		double denominator = truePos + trueNeg + falsePos + falseNeg;

		if (denominator > DELTA)
			accuracy = truePos / denominator;

		return accuracy;
	}
}

