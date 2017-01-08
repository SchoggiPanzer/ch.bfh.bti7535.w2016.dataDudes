package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

public class ExclamationSentenceFeature extends SentenceFeature {

	@Override
	public double train(List<Document> documents, Classification classification) {
		double sentences = getSentences(documents, classification);

		double exclamations = 0;
		for (Document doc : documents)
			if (doc.getGoldStandard().equals(classification))
				exclamations += DocumentUtil.countExclamationSenctences(doc);

		return (sentences > 0) ? exclamations / sentences : 0;
	}

	@Override public double test(Document document) {
		return DocumentUtil.countExclamationSenctences(document);
	}
}
