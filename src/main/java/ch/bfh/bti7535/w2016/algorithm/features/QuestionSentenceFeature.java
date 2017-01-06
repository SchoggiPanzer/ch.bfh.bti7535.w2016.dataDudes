package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;

import java.util.List;

public class QuestionSentenceFeature extends SentenceFeature {

	@Override
	public void train(List<Document> documents, Classification classification) {
		super.documents = documents;
		super.classification = classification;
		execute('?');
	}
}
