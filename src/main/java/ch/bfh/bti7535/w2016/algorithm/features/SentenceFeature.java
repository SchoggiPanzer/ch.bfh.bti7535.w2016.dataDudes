package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

public abstract class SentenceFeature extends AbstractFeature {

	protected enum Type {
		QUESTION, EXCLAMATION, POINT
	}

	protected void execute(List<Document> documents, Classification classification, Type type) {
		double sentencesAmount = 0;
		for (Document doc : documents) {
			if (doc.getGoldStandard().equals(classification))
				sentencesAmount += DocumentUtil.countSentences(doc);
		}

		double sentenceTypeAmount = 0;
		for (Document doc : documents) {
			if (doc.getGoldStandard().equals(classification)) {
				switch (type) {
				case QUESTION:
					sentenceTypeAmount += DocumentUtil.countQuestionSenctences(doc);
					break;
				case EXCLAMATION:
					sentenceTypeAmount += DocumentUtil.countQuestionSenctences(doc);
					break;
				}
			}
		}

		double result = (sentencesAmount > 0) ? sentenceTypeAmount / sentencesAmount : 0;
		setProbability(classification, result);
	}
}
