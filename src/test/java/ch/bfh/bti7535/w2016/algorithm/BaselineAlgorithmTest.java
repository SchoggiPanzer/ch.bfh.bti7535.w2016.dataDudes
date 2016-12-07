package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.filehandling.Classification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaselineAlgorithmTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testExecute() throws Exception {
		Map<String, Document.WordProperty> dummyWords = new HashMap<>();
		dummyWords.put("wonderful", new Document.WordProperty(1, Classification.SENTIMENT_POSITIVE));
		dummyWords.put("awesome", new Document.WordProperty(10, Classification.SENTIMENT_POSITIVE));
		dummyWords.put("terrible", new Document.WordProperty(3, Classification.SENTIMENT_NEGATIVE));

		Document doc = new Document();
		doc.setContent(dummyWords);
		List docList = new ArrayList();
		docList.add(doc);

		BaselineAlgorithm ba = new BaselineAlgorithm();
		List<Document> results = ba.execute(docList);
		Assert.assertEquals(1, results.size());

		doc = results.get(0);
		Assert.assertEquals(Classification.SENTIMENT_POSITIVE, doc.getTestResult());
	}
}