package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.Document;
import ch.bfh.bti7535.w2016.filehandling.Classification;
import ch.bfh.bti7535.w2016.filehandling.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class AlgoClassifier {

	private double truePos = 0;
	private double trueNeg = 0;
	private double falsePos = 0;
	private double falseNeg = 0;

	private String path = "./src/main/resources/review_polarity/txt_sentoken";
	private static Logger log = (Logger) LoggerFactory.getLogger(AlgoClassifier.class);

	public AlgoClassifier(AbstractAlgorithm algo) {
		//getArrayOfDocs
		//TODO interface algo
		FileReader fileReader = new FileReader();
		try {
			List<Document> inputdocs = fileReader.readAllFiles(path);
			List<Document> docs = algo.execute(inputdocs);
			classify(docs);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void classify(List<Document> docs) {
		for (Document doc : docs) {
			classifySingleDoc(doc);
		}
	}

	private void classifySingleDoc(Document doc) {
		truePos = 0;
		trueNeg = 0;
		falsePos = 0;
		falseNeg = 0;

		Classification gold = doc.getGoldStandard();
		Classification calculated = doc.getTestResult();
		//Does handle  notclassified as sentiment_negative
		if (gold == calculated) {
			//true
			if (gold == Classification.SENTIMENT_POSITIVE) {
				truePos++;
			} else {
				trueNeg++;
			}
		} else {
			//false
			if (calculated == Classification.SENTIMENT_POSITIVE) {
				falsePos++;
			} else {
				falseNeg++;
			}
		}
	}

	public double getPrecision() {
		return truePos / (truePos + falsePos);
	}

	public double getRecall() {
		return truePos / (truePos + falseNeg);
	}

	public double getFmesure() {
		return getFmesure(1.0);
	}

	public double getFmesure(double beta) {
		double precision = this.getPrecision();
		double recall = this.getRecall();
		return getFmesure(beta, precision, recall);
	}

	public double getFmesure(double beta, double precision, double recall) {
		return ((1 + beta * beta) * (precision * recall)) / ((beta * beta * precision) + recall);
	}

}
