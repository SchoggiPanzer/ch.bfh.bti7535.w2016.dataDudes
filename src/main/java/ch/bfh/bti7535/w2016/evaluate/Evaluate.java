package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.BaselineAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.NaiveBayesAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

/**
 * This class contains the main function and the function to evaluate the algorithms
 */
public class Evaluate {

	private static Logger log = (Logger) LoggerFactory.getLogger(Evaluate.class);

    /**
     * this function evaluates a algorithm and print out the results
     * @param clazz algorithm name to execute
     * @param kfold with kfold or not
     */
	private static void evaluateByClassname(Class clazz, boolean kfold) {
		try {
			log.info("Using {} for sentiment analysis", clazz.getSimpleName());
			AbstractAlgorithm algo = (AbstractAlgorithm) clazz.newInstance();
			AlgoClassifier classifier = new AlgoClassifier(algo);
			AlgoClassifier.ConfusionMatrix confusionMatrix = classifier.executeAndClassifyAlgorithm(kfold);
			log.info(confusionMatrix.toString());
		} catch (FileNotFoundException | IllegalAccessException | InstantiationException e) {
			log.error("Cannot instantiate {}", clazz.getName());
		}
	}

	/**
	 * Main function. In this function we declare which algorithm we will evaluate
	 * @param args baseline or naivebayes
	 */
	public static void main(String[] args) {
		if (args.length == 1) {
			String arg0 = args[0];
			switch (arg0.toLowerCase()) {
			case "baseline":
				evaluateByClassname(BaselineAlgorithm.class, false);
				break;
			case "naivebayes":
				evaluateByClassname(NaiveBayesAlgorithm.class, true);
				break;
			default:
				log.error("The argument {} does not match any algorithm.");
			}
		} else {
			//evaluateByClassname(BaselineAlgorithm.class, false);
			evaluateByClassname(NaiveBayesAlgorithm.class, true);
		}
	}
}
