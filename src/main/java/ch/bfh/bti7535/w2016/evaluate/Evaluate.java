package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.NaiveBayesAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Evaluate {

	private static Logger log = (Logger) LoggerFactory.getLogger(Evaluate.class);

	private static void evaluateByClassname(Class clazz) {
		try {
			AbstractAlgorithm algo = (AbstractAlgorithm) clazz.newInstance();
			AlgoClassifier classifier = new AlgoClassifier(algo);

			double fmesure = classifier.getFmesure();
			double precision = classifier.getPrecision();
			double recall = classifier.getRecall();

			String baseline = String.format(
					"\n---------------------\nBaseline Algorithm\n---------------------\n"
							+ "Precision:\t%f\n"
							+ "Recall:\t\t%f\n"
							+ "f-Measure:\t%f\n\n", precision, recall, fmesure);

			log.info(baseline);
		} catch (IllegalAccessException | InstantiationException e) {
			log.error("Cannot instantiate {}", clazz.getName());
		}
	}

	public static void main(String[] args) {
		//evaluateByClassname(BaselineAlgorithm.class);
		evaluateByClassname(NaiveBayesAlgorithm.class);
	}
}
