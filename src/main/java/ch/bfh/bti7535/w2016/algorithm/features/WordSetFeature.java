package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.CollectionUtil;

import java.util.List;

public abstract class WordSetFeature extends AbstractFeature {

	protected String[] wordSetList;

	// Only check sentences with 4 words
	public static final int CUTOFF_DEPTH = 4;

	protected double checkIfWordSetOccurs(Document doc) {
		int occurrence = 0;
		List<String> content = doc.getContent();
		for (int i = 0; i < content.size(); i++) {
			String word = content.get(i);
			int j = 1;
			while (j <= CUTOFF_DEPTH && i + j > content.size()) {
				word = word + " " + content.get(i + j);
				if (CollectionUtil.isStringInList(word, wordSetList))
					occurrence++;
				j++;
			}
		}
		return occurrence;
	}
}
