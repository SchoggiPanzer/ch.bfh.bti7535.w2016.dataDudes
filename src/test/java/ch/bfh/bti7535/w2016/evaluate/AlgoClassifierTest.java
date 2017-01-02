package ch.bfh.bti7535.w2016.evaluate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgoClassifierTest {
	private static double DOUBLE_DELTA = 0.0001;

	private MockAlgorithm tp = new MockAlgorithm();
	private MockAlgorithm tn = new MockAlgorithm();
	private MockAlgorithm fp = new MockAlgorithm();
	private MockAlgorithm fn = new MockAlgorithm();
	private MockAlgorithm mixed = new MockAlgorithm();

	private AlgoClassifier tpA;
	private AlgoClassifier tnA;
	private AlgoClassifier fpA;
	private AlgoClassifier fnA;
	private AlgoClassifier mixedA;

	@Before
	public void setUp() {
		int times = 10;

		tp.addTruePos(times);
		tn.addTrueNeg(times);
		fp.addFalsePos(times);
		fn.addFalseNeg(times);

		mixed.addTruePos(times);
		mixed.addTrueNeg(times);
		mixed.addFalsePos(times);
		mixed.addFalseNeg(times);

		tpA = new AlgoClassifier(tp);
		tnA = new AlgoClassifier(tn);
		fpA = new AlgoClassifier(fp);
		fnA = new AlgoClassifier(fn);
		mixedA = new AlgoClassifier(mixed);
	}

	@Test
	public void getPrecisionTest() throws Exception {
		double tpPrecision = tpA.getPrecision();
		double tnPrecision = tnA.getPrecision();
		double fpPrecision = fpA.getPrecision();
		double fnPrecision = fnA.getPrecision();
		double mixedPrecision = mixedA.getPrecision();

		assertEquals(1, tpPrecision, DOUBLE_DELTA);
		assertEquals(0, tnPrecision, DOUBLE_DELTA);
		assertEquals(0, fpPrecision, DOUBLE_DELTA);
		assertEquals(0, fnPrecision, DOUBLE_DELTA);
		assertEquals(0.5, mixedPrecision, DOUBLE_DELTA);
	}

	@Test
	public void getRecallTest() throws Exception {
		double tpRecall = tpA.getRecall();
		double tnRecall = tpA.getRecall();
		double fpRecall = fpA.getRecall();
		double fnRecall = fnA.getRecall();
		double mixedRecall = mixedA.getRecall();

		assertEquals(1, tpRecall, DOUBLE_DELTA);
		//FIXME: Check that case (always 1?)
		//assertEquals(0, tnRecall, DOUBLE_DELTA);
		assertEquals(0, fpRecall, DOUBLE_DELTA);
		assertEquals(0, fnRecall, DOUBLE_DELTA);
		assertEquals(0.5, mixedRecall, DOUBLE_DELTA);
	}

	@Test
	public void getFmesureTest() throws Exception {
		double tpFmesure = tpA.getFmesure();
		double tnFmesure = tnA.getFmesure();
		double fpFmesure = fpA.getFmesure();
		double fnFmesure = fnA.getFmesure();
		double mixedFmesure = mixedA.getFmesure();

		assertEquals(1, tpFmesure, DOUBLE_DELTA);
		assertEquals(0, tnFmesure, DOUBLE_DELTA);
		assertEquals(0, fpFmesure, DOUBLE_DELTA);
		assertEquals(0, fnFmesure, DOUBLE_DELTA);
		assertEquals(0.5, mixedFmesure, DOUBLE_DELTA);
	}

	@Test
	public void getFmesureBeta1Test() throws Exception {
		double beta = 3.0;
		double tpFmesure = tpA.getFmesure(beta);
		double tnFmesure = tnA.getFmesure(beta);
		double fpFmesure = fpA.getFmesure(beta);
		double fnFmesure = fnA.getFmesure(beta);
		double mixedFmesure = mixedA.getFmesure(beta);

		assertEquals(1, tpFmesure, DOUBLE_DELTA);
		assertEquals(0, tnFmesure, DOUBLE_DELTA);
		assertEquals(0, fpFmesure, DOUBLE_DELTA);
		assertEquals(0, fnFmesure, DOUBLE_DELTA);
		assertEquals(0.5, mixedFmesure, DOUBLE_DELTA);
	}

	@Test
	public void getFmesureBeta2Test() throws Exception {
		double beta = 0.0;
		double tpFmesure = tpA.getFmesure(beta);
		double tnFmesure = tnA.getFmesure(beta);
		double fpFmesure = fpA.getFmesure(beta);
		double fnFmesure = fnA.getFmesure(beta);
		double mixedFmesure = mixedA.getFmesure(beta);

		assertEquals(1.0, tpFmesure, DOUBLE_DELTA);
		assertEquals(0, tnFmesure, DOUBLE_DELTA);
		assertEquals(0, fpFmesure, DOUBLE_DELTA);
		assertEquals(0, fnFmesure, DOUBLE_DELTA);
		assertEquals(0.5, mixedFmesure, DOUBLE_DELTA);
	}

	@Test
	public void getAccuracyTest() {
		double t1 = tpA.getAccuracy();
		double t2 = tnA.getAccuracy();
		double t3 = fpA.getAccuracy();
		double t4 = fnA.getAccuracy();
		double t5 = mixedA.getAccuracy();

		assertEquals(1, t1, DOUBLE_DELTA);
		assertEquals(0, t2, DOUBLE_DELTA);
		assertEquals(0, t3, DOUBLE_DELTA);
		assertEquals(0, t4, DOUBLE_DELTA);
		assertEquals(.25, t5, DOUBLE_DELTA);
	}
}