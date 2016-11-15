package ch.bfh.bti7535.w2016.ex3;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.model.BaseModel;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NamedEntityRecognition {
	private static Logger log = Logger.getLogger(NamedEntityRecognition.class);

	public BaseModel trainNames(String goldStandard) {
		BaseModel model = null;

		try (
				InputStream dataIn = getClass().getClassLoader().getResourceAsStream(goldStandard);
				ObjectStream<String> lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
				ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream)) {

			model = DocumentCategorizerME.train("en", sampleStream);
			// TODO: I think this should be done instead of perform categorization training...
//			model = NameFinderME.train("en", "person", sampleStream,
//					Collections.<String, Object>emptyMap(), 100, 5);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

		return model;
	}

	public String findNames(DoccatModel model, String filename) {
		String results = "";
		try {
			URI uri = getClass().getClassLoader().getResource(filename).toURI();
			String content = new String(Files.readAllBytes(Paths.get(uri)));
			DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);
			double[] outcomes = myCategorizer.categorize(content);
			results = myCategorizer.getAllResults(outcomes);
		} catch (URISyntaxException | IOException e) {
			log.error(e.getMessage(), e);
		}
		return results;
	}

	public static void main(String[] args) {
		NamedEntityRecognition ner = new NamedEntityRecognition();
		BaseModel model = ner.trainNames("ex3/jane-austen-emma-ch2.tsv");
		String results = ner.findNames((DoccatModel) model, "ex3/jane-austen-emma-ch2.txt");
		log.info(results);
	}
}
