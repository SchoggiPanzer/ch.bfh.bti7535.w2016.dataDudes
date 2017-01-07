package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.BaselineAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.NaiveBayesAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Evaluate {

	private static Logger log = (Logger) LoggerFactory.getLogger(Evaluate.class);

	private static void evaluateByClassname(Class clazz) {
		try {
			log.info("Using {} for sentiment analysis", clazz.getSimpleName());
			AbstractAlgorithm algo = (AbstractAlgorithm) clazz.newInstance();
			AlgoClassifier classifier = new AlgoClassifier(algo);

			double fmesure = classifier.getFmesure();
			double precision = classifier.getPrecision();
			double recall = classifier.getRecall();

			String baseline = String.format(
					"\n---------------------\n%s\n---------------------\n"
							+ "Precision:\t%.03f %%\n"
							+ "Recall:\t\t%.03f %%\n"
							+ "f-Measure:\t%.03f %%\n\n", algo.getClass().getSimpleName(), precision * 100, recall * 100, fmesure * 100);

			log.info(baseline);
		} catch (IllegalAccessException | InstantiationException e) {
			log.error("Cannot instantiate {}", clazz.getName());
		}
	}

	public static void main(String[] args) {
		if (args.length == 1) {
			String arg0 = args[0];
			switch (arg0.toLowerCase()) {
			case "baseline":
				evaluateByClassname(BaselineAlgorithm.class);
				break;
			case "naivebayes":
				evaluateByClassname(NaiveBayesAlgorithm.class);
				break;
			default:
				log.error("The argument {} does not match any algorithm.");
			}
		} else {
			evaluateByClassname(BaselineAlgorithm.class);
			//evaluateByClassname(NaiveBayesAlgorithm.class);
		}
	}
}
