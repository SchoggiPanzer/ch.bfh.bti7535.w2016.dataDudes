package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.BaselineAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Evaluate {

	private static Logger log = (Logger) LoggerFactory.getLogger(Evaluate.class);

	public static void main(String[] args) {
		AbstractAlgorithm algo = new BaselineAlgorithm();
		AlgoClassifier classifier = new AlgoClassifier(algo);

		double fmesure = classifier.getFmesure();
		double precision = classifier.getPrecision();
		double recall = classifier.getRecall();

		String baseline = String.format(
				"Baseline Algorithm\n---------------------\n"
						+ "Precision:\t%f\n"
						+ "Recall:\t%f\n"
						+ "f-Measure:\t%f\n", precision, recall, fmesure);

		log.info(baseline);
	}
}
