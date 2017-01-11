package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.FileReaderUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Classifies the algorithm on the basis of the results. It looks which results are true positive/negative and which one
 * are false positive/negative.
 */
public class AlgoClassifier {

	private static double DELTA = 0.0001;

	private List<ConfusionMatrix> confusionMatrices = new LinkedList<>();
	private AbstractAlgorithm algorithm;

	class ConfusionMatrix {
		private double truePos = 0;
		private double trueNeg = 0;
		private double falsePos = 0;
		private double falseNeg = 0;

		ConfusionMatrix() {
		}

		ConfusionMatrix(double truePos, double trueNeg, double falsePos, double falseNeg) {
			this.truePos = truePos;
			this.trueNeg = trueNeg;
			this.falsePos = falsePos;
			this.falseNeg = falseNeg;
		}

        /**
         * calculates the precision
         * @return precision
         */
		public double getPrecision() {
			double precision = 0.0;

			if (truePos + falsePos > DELTA)
				precision = truePos / (truePos + falsePos);

			return precision;
		}

        /**
         * calculates the recall
         * @return recall
         */
		public double getRecall() {
			double recall = 0.0;

			if (truePos + falseNeg > DELTA)
				recall = truePos / (truePos + falseNeg);

			return recall;
		}

        /**
         * get the fmesure
         * @return fmesure
         */
		public double getFMeasure() {
			return getFMeasure(1.0);
		}

        /**
         * calculates the fmesure and returns it
         * @param beta
         * @return the fmesure
         */
		public double getFMeasure(double beta) {
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

        /**
         * calculates the accuracy and returns it
         * @return the accuracy
         */
		public double getAccuracy() {
			double accuracy = 0.0;
			double denominator = truePos + trueNeg + falsePos + falseNeg;

			if (denominator > DELTA)
				accuracy = (truePos + trueNeg) / denominator;

			return accuracy;
		}

		@Override
		public String toString() {
			String cm = String.format(
					"\n\nConfusion Matrix:\n\n" +
							"        POS        NEG       \n" +
							"      .---------------------.\n" +
							" TRUE | %-8.0f | %-8.0f |\n" +
							"      -----------------------\n" +
							"FALSE | %-8.0f | %-8.0f |\n" +
							"      `---------------------'\n",
					truePos, trueNeg, falsePos, falseNeg);

			cm += String.format(
					"\n---------------------\n%s\n---------------------\n"
							+ "Accuracy:\t%.03f %%\n"
							+ "Precision:\t%.03f %%\n"
							+ "Recall:\t\t%.03f %%\n"
							+ "f-Measure:\t%.03f %%\n\n",
					algorithm.getClass().getSimpleName(),
					getAccuracy() * 100,
					getPrecision() * 100,
					getRecall() * 100,
					getFMeasure() * 100);
			return cm;
		}
	}

	public AlgoClassifier(AbstractAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	public ConfusionMatrix executeAndClassifyAlgorithm(boolean kFolding)
			throws FileNotFoundException {
		List<Document> inputdocs = FileReaderUtil.readFilesFromPath(FileReaderUtil.FILE_PATH);
		ConfusionMatrix result = null;

		if (kFolding) {
			result = kFold(inputdocs);
		} else {
			inputdocs = algorithm.execute(inputdocs);
			result = classify(inputdocs);
		}

		return result;
	}

	private ConfusionMatrix kFold(List<Document> inputdocs) {
		ConfusionMatrix result = new ConfusionMatrix();
		double denominator = 1 - AbstractAlgorithm.SPLIT_POINT;
		Collections.shuffle(inputdocs);
		int offset = (int) Math.floor(((float) inputdocs.size()) * denominator);
		for (double i = 0; i < 1.0; i += denominator) {
			int start = (int) Math.floor(i * denominator);
			List<Document> trainingSet = inputdocs.subList(start, offset);

			List<Document> testSet = new ArrayList<>(inputdocs);
			testSet.removeAll(trainingSet);

			List<Document> results = algorithm.execute(trainingSet, testSet);

			ConfusionMatrix confusionMatrix = classify(results);
			confusionMatrices.add(confusionMatrix);
		}
		for (ConfusionMatrix cm : confusionMatrices) {
			if (result.getFMeasure() < cm.getFMeasure())
				result = cm;
		}
		return result;
	}

	protected ConfusionMatrix classify(List<Document> docs) {
		double truePos = 0;
		double trueNeg = 0;
		double falsePos = 0;
		double falseNeg = 0;

		for (Document doc : docs) {
			Classification gold = doc.getGoldStandard();
			Classification calculated = doc.getTestResult();

			// Ignores SENTIMENT_NOT_CLASSIFIED
			if (gold == calculated) {
				//true
				if (gold == Classification.SENTIMENT_POSITIVE)
					truePos++;
				else if (gold == Classification.SENTIMENT_NEGATIVE)
					trueNeg++;
			} else {
				//false
				if (calculated == Classification.SENTIMENT_POSITIVE)
					falsePos++;
				else if (calculated == Classification.SENTIMENT_NEGATIVE)
					falseNeg++;
			}
		}
		return new ConfusionMatrix(truePos, trueNeg, falsePos, falseNeg);
	}

}

