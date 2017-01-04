package ch.bfh.bti7535.w2016.features;

import ch.bfh.bti7535.w2016.algorithm.Document;
import ch.bfh.bti7535.w2016.filehandling.Classification;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

public class QuestionSentFeature extends AbstractFeature {

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

		float result = (sentencesAmount > 0.0001) ? sentencesQuestionAmount / sentencesAmount : 0;
		setProbability(classification, result);
	}
}
