package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BaselineAlgorithmTest {

	private List<Document> posWordList;
	private List<Document> negWordList;

	// Since algorithm should be stateless we can instantiate the object once
	private BaselineAlgorithm TEST_OBJECT = new BaselineAlgorithm();

	@Before
	public void setUp() throws Exception {
		//
		// Positive document
		Map<String, Integer> posWords = new LinkedHashMap<>();
		posWords.put("wonderful", 1);
		posWords.put("awesome", 3);
		posWords.put("terrible", 1);

		Document posDoc = new Document();
		posDoc.setGoldStandard(Classification.SENTIMENT_POSITIVE);
		posDoc.setContent(posWords);

		posWordList = new ArrayList<>();
		posWordList.add(posDoc);

		//
		// Negative document
		Map<String, Integer> negWords = new LinkedHashMap<>();
		negWords.put("awful", 3);
		negWords.put("nice", 1);
		negWords.put("bad", 3);

		Document negDoc = new Document();
		negDoc.setGoldStandard(Classification.SENTIMENT_NEGATIVE);
		negDoc.setContent(negWords);

		negWordList = new ArrayList<>();
		negWordList.add(negDoc);
	}

	@Test
	public void testPositiveCase() throws Exception {
		List<Document> results = TEST_OBJECT.execute(posWordList);
		Assert.assertEquals(1, results.size());

		Document doc = results.get(0);
		Assert.assertEquals(Classification.SENTIMENT_POSITIVE, doc.getGoldStandard());
		Assert.assertEquals(Classification.SENTIMENT_POSITIVE, doc.getTestResult());
	}

	@Test
	public void testNegativeCase() throws Exception {
		List<Document> results = TEST_OBJECT.execute(negWordList);
		Assert.assertEquals(1, results.size());

		Document doc = results.get(0);
		Assert.assertEquals(Classification.SENTIMENT_NEGATIVE, doc.getGoldStandard());
		Assert.assertEquals(Classification.SENTIMENT_NEGATIVE, doc.getTestResult());
	}
}