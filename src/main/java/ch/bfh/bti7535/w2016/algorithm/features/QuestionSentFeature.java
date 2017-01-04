package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

public class QuestionSentFeature extends AbstractFeature {

	@Override
	public void train(List<Document> documents, Classification classification) {
		int sentencesAmount = 0;
		int sentencesQuestionAmount = 0;

		for (Document doc : documents) {
			if (doc.getGoldStandard().equals(classification)) {
				sentencesAmount += DocumentUtil.countSentences(doc);
				sentencesAmount += DocumentUtil.countExclamationSenctences(doc);
				sentencesAmount += DocumentUtil.countQuestionSenctences(doc);
				sentencesQuestionAmount += DocumentUtil.countQuestionSenctences(doc);
			}
		}

		System.out.println("Point Sentence: " + sentencesAmount);
		float result = (sentencesAmount > 0.0001) ?
				(sentencesQuestionAmount + 1.0f) / (float) (sentencesAmount) : 0;
		setProbability(classification, result);
	}
}
