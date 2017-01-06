package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NaiveBayesAlgorithmTest {

	private List<Document> docList;

	@Before
	public void setUp() throws Exception {
		docList = new ArrayList<>();

		String negContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("pos.txt").toURI())));
		List<String> negTokens = Arrays.asList(negContent.split(" "));
		//docList.add(new Document(negTokens, Classification.SENTIMENT_NEGATIVE));

		String posContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("neg.txt").toURI())));
		List<String> posTokens = Arrays.asList(posContent.split(" "));
		//docList.add(new Document(posTokens, Classification.SENTIMENT_POSITIVE));

		//docList.add(new Document(negTokens, Classification.SENTIMENT_NEGATIVE));
	}

	@Test
    @Ignore
	public void testAutoSplitExecute() throws Exception {
		NaiveBayesAlgorithm naiveBayesAlgorithm = new NaiveBayesAlgorithm();
		List<Document> results = naiveBayesAlgorithm.execute(docList);

		assertEquals(1, results.size());
		Document negativeDoc = results.get(0);

		assertEquals(Classification.SENTIMENT_POSITIVE, negativeDoc.getTestResult());
	}

	@Test
	@Ignore
	public void testManualSplitExecute() throws Exception {
		NaiveBayesAlgorithm naiveBayesAlgorithm = new NaiveBayesAlgorithm();
		List<Document> results = naiveBayesAlgorithm.execute(docList.subList(0, 2), docList.subList(1, 3));

		assertEquals(2, results.size());
		Document positiveDoc = results.get(0);
		Document negativeDoc = results.get(1);

		assertEquals(Classification.SENTIMENT_POSITIVE, positiveDoc.getTestResult());
		assertEquals(Classification.SENTIMENT_NEGATIVE, negativeDoc.getTestResult());
	}
}
