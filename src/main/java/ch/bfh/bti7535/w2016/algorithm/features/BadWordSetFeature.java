package ch.bfh.bti7535.w2016.algorithm.features;

/**
 * this feature checks the documents of bad words
 */
public class BadWordSetFeature extends WordSetFeature {

	public BadWordSetFeature() {
		super.wordSetList = new String[] {
				"bad",
				"boring",
				"worst",
				"stupid",
				"ridiculous",
				"waste",
				"awful",
				"lame",
				"mess",
				"redundant",
				"confusing",
				"confused",
				"tired"
		};
	}
}
