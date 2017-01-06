package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

public abstract class SentenceFeature extends AbstractFeature {
	List<Document> documents;
	Classification classification;

	protected void execute(char type) {
		double sentencesAmount = 0;
		for (Document doc : documents) {
			if (doc.getGoldStandard().equals(classification))
				sentencesAmount += DocumentUtil.countSentences(doc);
		}

		double sentenceTypeAmount = 0;
		for (Document doc : documents) {
			if (doc.getGoldStandard().equals(classification)) {
				switch (type) {
				case '?':
					sentenceTypeAmount += DocumentUtil.countQuestionSenctences(doc);
					break;
				case '!':
					sentenceTypeAmount += DocumentUtil.countQuestionSenctences(doc);
					break;
				}
			}
		}

		double result = (sentencesAmount > 0) ? sentenceTypeAmount / sentencesAmount : 0;
		setProbability(classification, result);
	}
}
