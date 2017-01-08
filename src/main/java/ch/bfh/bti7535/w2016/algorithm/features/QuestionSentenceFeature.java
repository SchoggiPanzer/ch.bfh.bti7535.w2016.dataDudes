package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

public class QuestionSentenceFeature extends SentenceFeature {

	@Override
	public double train(List<Document> documents, Classification classification) {
		double sentences = getSentences(documents, classification);

		double questions = 0;
		for (Document doc : documents)
			if (doc.getGoldStandard().equals(classification))
				questions += DocumentUtil.countQuestionSenctences(doc);

		return (sentences > 0) ? questions / sentences : 0;
	}

	@Override
	public double test(Document document) {
		return DocumentUtil.countQuestionSenctences(document);
	}
}
