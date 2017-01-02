package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.Document;
import ch.bfh.bti7535.w2016.filehandling.Classification;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DummyAlgoTest {
	@Test
	public void lengthTest() {
		int count = 10;
		DummyAlgo algo = new DummyAlgo();
		List<Document> list = new ArrayList<>();
		List<Document> outputDocs = algo.execute(list);
		assertEquals(0, outputDocs.size());

		algo.addTruePos(count);
		algo.addFalsePos(count);
		outputDocs = algo.execute(list);
		assertEquals(2 * count, outputDocs.size());
	}

	@Test
	public void addTruePosTest() {
		int count = 10;
		DummyAlgo algo = new DummyAlgo();
		List<Document> list = new ArrayList<>();
		List<Document> outputDocs = algo.execute(list);
		algo.addTruePos(count);
		outputDocs = algo.execute(list);

		assertEquals(count, outputDocs.size());
		for (Document d : outputDocs) {
			assertEquals(Classification.SENTIMENT_POSITIVE, d.getTestResult());
			assertEquals(Classification.SENTIMENT_POSITIVE, d.getGoldStandard());
		}
	}

	@Test
	public void addTrueNegTest() {
		int count = 10;
		DummyAlgo algo = new DummyAlgo();
		List<Document> list = new ArrayList<>();
		List<Document> outputDocs = algo.execute(list);
		algo.addTrueNeg(count);
		outputDocs = algo.execute(list);

		assertEquals(count, outputDocs.size());
		for (Document d : outputDocs) {
			assertEquals(Classification.SENTIMENT_NEGATIVE, d.getTestResult());
			assertEquals(Classification.SENTIMENT_NEGATIVE, d.getGoldStandard());
		}
	}

	@Test
	public void addFalsePosTest() {
		int count = 10;
		DummyAlgo algo = new DummyAlgo();
		List<Document> list = new ArrayList<>();
		List<Document> outputDocs = algo.execute(list);
		algo.addFalsePos(count);
		outputDocs = algo.execute(list);

		assertEquals(count, outputDocs.size());
		for (Document d : outputDocs) {
			assertEquals(Classification.SENTIMENT_POSITIVE, d.getGoldStandard());
			assertEquals(Classification.SENTIMENT_NEGATIVE, d.getTestResult());
		}
	}

	@Test
	public void addFalseNegTest() {
		int count = 10;
		DummyAlgo algo = new DummyAlgo();
		List<Document> list = new ArrayList<>();
		List<Document> outputDocs = algo.execute(list);
		algo.addFalseNeg(count);
		outputDocs = algo.execute(list);

		assertEquals(count, outputDocs.size());
		for (Document d : outputDocs) {
			assertEquals(Classification.SENTIMENT_NEGATIVE, d.getGoldStandard());
			assertEquals(Classification.SENTIMENT_POSITIVE, d.getTestResult());
		}

	}
}