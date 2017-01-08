package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WordSetFeatureTest {

	private WordSetFeature TEST_OBJECT1 = new GoodWordSetFeature();
	private WordSetFeature TEST_OBJECT2 = new BadWordSetFeature();

	@Test
	public void testCheckPosWordOccurrence() {
		Map<String, Integer> posWords = new LinkedHashMap<>();
		posWords.put("was", 1);
		posWords.put("good", 1);
		posWords.put("and", 1);
		posWords.put("a", 1);
		posWords.put("great", 1);
		posWords.put("movie", 1);

		Document posDoc = new Document();
		posDoc.setGoldStandard(Classification.SENTIMENT_POSITIVE);
		posDoc.setContent(posWords);

		double matches = TEST_OBJECT1.calculateOccurrence(posDoc);
		assertEquals(2, matches, 0);
	}

	@Test
	public void testCheckNegWordOccurrence() {
		Map<String, Integer> negWords = new LinkedHashMap<>();
		negWords.put("was", 1);
		negWords.put("terrible", 1);
		negWords.put("and", 1);
		negWords.put("not", 1);
		negWords.put("good", 1);

		Document negDoc = new Document();
		negDoc.setGoldStandard(Classification.SENTIMENT_POSITIVE);
		negDoc.setContent(negWords);

		double matches = TEST_OBJECT2.calculateOccurrence(negDoc);
		assertEquals(2, matches, 0);
	}
}
