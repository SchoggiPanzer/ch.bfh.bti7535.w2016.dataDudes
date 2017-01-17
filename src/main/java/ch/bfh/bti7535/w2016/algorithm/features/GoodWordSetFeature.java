package ch.bfh.bti7535.w2016.algorithm.features;

/**
 * this feature checks the documents of good words
 */
public class GoodWordSetFeature extends WordSetFeature {

	public GoodWordSetFeature() {
		super.wordSetList = new String[] {
				"outstanding",
				"fantastic",
				"awesome",
				"interesting",
				"perfectly",
				"excellent",
				"perfect",
				"memorable",
				"life",
				"best",
				"fascination",
				"fascinating",
				"incredible"
		};
	}
}
