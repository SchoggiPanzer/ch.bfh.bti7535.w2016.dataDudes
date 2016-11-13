package ch.bfh.bti7535.w2016.ex3;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderEvaluator;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.eval.FMeasure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;

public class NameEntityRecognition {
	private static Logger log = LoggerFactory.getLogger(NameEntityRecognition.class);
	TokenNameFinderModel model;

	public NameEntityRecognition() {
	}

	public void trainNames(String goldStandard) {
		try (
				InputStream modelIn = getClass().getClassLoader().getResourceAsStream(goldStandard);
				PlainTextByLineStream plainTextByLineStream = new PlainTextByLineStream(modelIn, Charset.forName("UTF-8"));
				NameSampleDataStream sampleStream = new NameSampleDataStream(plainTextByLineStream)
		) {
			model = NameFinderME.train("en", "PERS", sampleStream, Collections.emptyMap());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void findNames(String filename) {
		try (
				FileInputStream sampleDataIn = new FileInputStream(filename);
				PlainTextByLineStream plainTextByLineStream = new PlainTextByLineStream(sampleDataIn.getChannel(), "UTF-8");
				ObjectStream<NameSample> sampleStream = new NameSampleDataStream(plainTextByLineStream)
		) {
			TokenNameFinderEvaluator evaluator = new TokenNameFinderEvaluator(new NameFinderME(model));
			evaluator.evaluate(sampleStream);
			FMeasure result = evaluator.getFMeasure();
			System.out.println(result.toString());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		NameEntityRecognition ner = new NameEntityRecognition();
		ner.trainNames("ex3/jane-austen-emma-ch2.tsv");
		ner.findNames("ex3/jane-austen-emma-ch2.txt");
	}
}
