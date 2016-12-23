package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.filehandling.Classification;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaselineAlgorithm extends AbstractAlgorithm {
	private static Logger log = LoggerFactory.getLogger(BaselineAlgorithm.class);
	private static String SENTIMENT_API_URL = "http://text-processing.com/api/sentiment/";

	@Override
	public List<Document> execute(List<Document> input) {
		List<Document> result = new ArrayList<>();
		for (Document doc : input) {
			result.add(processDocument(doc));
		}

		return result;
	}

	@Override
	public List<Document> execute(List<Document> trainingSet, List<Document> testSet) {
		throw new NotImplementedException();
	}

	private Document processDocument(Document doc) {
		float posWords = 0;
		float negWords = 0;

		Map<String, Document.WordProperty> content = doc.getContentWithWordProperties();
		for (String word : content.keySet()) {
			int occurence = content.get(word).getOccurence();

			if (isPositiveWord(word))
				posWords += occurence;
			else
				negWords += occurence;
		}

		float total = posWords + negWords;
		if (total > 0) {
			Classification c = (posWords / total > 0.5) ? Classification.SENTIMENT_POSITIVE : Classification.SENTIMENT_NEGATIVE;
			doc.setTestResult(c);
		} else {
			doc.setTestResult(Classification.NOT_CLASSIFIED);
			log.warn("No positive or negative words detected.");
		}
		return doc;
	}

	private boolean isPositiveWord(String word) {
		boolean result = false;
		try {
			HttpClient httpClient = new HttpClient();
			PostMethod postMethod = new PostMethod(SENTIMENT_API_URL);
			postMethod.addParameter("text", word);
			httpClient.executeMethod(postMethod);

			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				String resp = postMethod.getResponseBodyAsString();
				String label = (String) new JSONObject(resp).get("label");
				result = (label.equals(Classification.SENTIMENT_POSITIVE.getLabel()));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
}
