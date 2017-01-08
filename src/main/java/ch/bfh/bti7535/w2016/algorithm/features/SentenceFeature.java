package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

public abstract class SentenceFeature extends AbstractFeature {

	double getSentences(List<Document> documents, Classification classification) {
		double sentencesAmount = 0;
		for (Document doc : documents)
			if (doc.getGoldStandard().equals(classification))
				sentencesAmount += DocumentUtil.countSentences(doc);

		return sentencesAmount;
	}
}
