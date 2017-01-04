package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

public class GoodWordsFeature extends AbstractFeature {

	@Override
	public void train(List<Document> documents, Classification classification) {
		int sentencesAmount = 0;
		for (Document doc : documents) {
			if (doc.getGoldStandard().equals(classification))
				sentencesAmount += DocumentUtil.countSentences(doc);
		}

		int sentencesQuestionAmount = 0;
		for (Document doc : documents) {
			if (doc.getGoldStandard().equals(classification))
				sentencesQuestionAmount += DocumentUtil.countQuestionSenctences(doc);
		}

		double result = (sentencesAmount > 0.0001) ? (double) sentencesQuestionAmount / (double) sentencesAmount : 0;
		setProbability(classification, result);
	}
}
