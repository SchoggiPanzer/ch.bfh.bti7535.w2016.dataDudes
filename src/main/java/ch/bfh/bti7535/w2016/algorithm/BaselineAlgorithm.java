package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.filehandling.Classification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaselineAlgorithm extends AbstractAlgorithm {
	private static Logger log = LoggerFactory.getLogger(BaselineAlgorithm.class);

	@Override
	public List<Document> execute(List<Document> input) {
		List<Document> result = new ArrayList<>();
		for (Document doc : input) {
			result.add(processDocument(doc));
		}

		return result;
	}

	private Document processDocument(Document doc) {
		int posWords = 0;
		int negWords = 0;

		Map<String, Document.WordProperty> content = doc.getContent();
		for (String word : content.keySet()) {
			int occurence = content.get(word).getOccurence();

			if (isPositiveWord(word))
				posWords += occurence;
			else
				negWords += occurence;
		}

		int total = posWords + negWords;
		if (total > 0) {
			Classification c = (posWords / total > 0.5) ? Classification.POSITIVE : Classification.NEGATIVE;
			doc.setTestResult(c);
		} else {
			log.warn("No positive or negative words detected.");
		}
		return doc;
	}

	private boolean isPositiveWord(String word) {
		// TODO: Use Library which detects if word is pos or neg
		return true;
	}
}
