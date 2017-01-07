package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;

import java.util.List;

public class GoodWordSetFeature extends WordSetFeature {

	public GoodWordSetFeature() {
		super.wordSetList = new String[] {
				"was terrible",
				"was boring",
				"was not good",
				"not good"
		};
	}

	@Override
	public void train(List<Document> documents, Classification classification) {

		double wordSetOccurrence = 0;
		for (Document doc : documents) {
			if (doc.getGoldStandard().equals(classification)) {
				wordSetOccurrence += checkIfWordSetOccurs(doc);
			}
		}

		double result = (wordSetList.length > 0) ? wordSetOccurrence / wordSetList.length : 0;
		setProbability(classification, result);
	}
}