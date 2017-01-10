package ch.bfh.bti7535.w2016.evaluate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgoClassifierTest {
	private static double DOUBLE_DELTA = 0.0001;

	private AlgoClassifier.ConfusionMatrix confusionMatrixTP;
	private AlgoClassifier.ConfusionMatrix confusionMatrixTN;
	private AlgoClassifier.ConfusionMatrix confusionMatrixFP;
	private AlgoClassifier.ConfusionMatrix confusionMatrixFN;
	private AlgoClassifier.ConfusionMatrix confusionMatrixMixed;

	@Before
	public void setUp() throws Exception {
		int times = 10;

		MockAlgorithm tp = new MockAlgorithm();
		MockAlgorithm tn = new MockAlgorithm();
		MockAlgorithm fp = new MockAlgorithm();
		MockAlgorithm fn = new MockAlgorithm();
		MockAlgorithm mixed = new MockAlgorithm();

		tp.addTruePos(times);
		tn.addTrueNeg(times);
		fp.addFalsePos(times);
		fn.addFalseNeg(times);

		mixed.addTruePos(times);
		mixed.addTrueNeg(times);
		mixed.addFalsePos(times);
		mixed.addFalseNeg(times);

		AlgoClassifier tpA = new AlgoClassifier(tp);
		confusionMatrixTP = tpA.executeAndClassifyAlgorithm(false);

		AlgoClassifier tnA = new AlgoClassifier(tn);
		confusionMatrixTN = tnA.executeAndClassifyAlgorithm(false);

		AlgoClassifier fpA = new AlgoClassifier(fp);
		confusionMatrixFP = fpA.executeAndClassifyAlgorithm(false);

		AlgoClassifier fnA = new AlgoClassifier(fn);
		confusionMatrixFN = fnA.executeAndClassifyAlgorithm(false);

		AlgoClassifier mixedA = new AlgoClassifier(mixed);
		confusionMatrixMixed = mixedA.executeAndClassifyAlgorithm(false);
	}

	@Test
	public void getPrecisionTest() throws Exception {

		double tpPrecision = confusionMatrixTP.getPrecision();
		double tnPrecision = confusionMatrixTN.getPrecision();
		double fpPrecision = confusionMatrixFP.getPrecision();
		double fnPrecision = confusionMatrixFN.getPrecision();
		double mixedPrecision = confusionMatrixMixed.getPrecision();

		assertEquals(1, tpPrecision, DOUBLE_DELTA);
		assertEquals(0, tnPrecision, DOUBLE_DELTA);
		assertEquals(0, fpPrecision, DOUBLE_DELTA);
		assertEquals(0, fnPrecision, DOUBLE_DELTA);
		assertEquals(0.5, mixedPrecision, DOUBLE_DELTA);
	}

	@Test
	public void getRecallTest() throws Exception {
		double tpRecall = confusionMatrixTP.getRecall();
		double tnRecall = confusionMatrixTN.getRecall();
		double fpRecall = confusionMatrixFP.getRecall();
		double fnRecall = confusionMatrixFN.getRecall();
		double mixedRecall = confusionMatrixMixed.getRecall();

		assertEquals(1, tpRecall, DOUBLE_DELTA);
		//FIXME: Check that case (always 1?)
		//assertEquals(0, tnRecall, DOUBLE_DELTA);
		assertEquals(0, fpRecall, DOUBLE_DELTA);
		assertEquals(0, fnRecall, DOUBLE_DELTA);
		assertEquals(0.5, mixedRecall, DOUBLE_DELTA);
	}

	@Test
	public void getFmesureTest() throws Exception {
		double tpFmesure = confusionMatrixTP.getFMeasure();
		double tnFmesure = confusionMatrixTN.getFMeasure();
		double fpFmesure = confusionMatrixFP.getFMeasure();
		double fnFmesure = confusionMatrixFN.getFMeasure();
		double mixedFmesure = confusionMatrixMixed.getFMeasure();

		assertEquals(1, tpFmesure, DOUBLE_DELTA);
		assertEquals(0, tnFmesure, DOUBLE_DELTA);
		assertEquals(0, fpFmesure, DOUBLE_DELTA);
		assertEquals(0, fnFmesure, DOUBLE_DELTA);
		assertEquals(0.5, mixedFmesure, DOUBLE_DELTA);
	}

	@Test
	public void getFmesureBeta1Test() throws Exception {
		double beta = 3.0;
		double tpFmesure = confusionMatrixTP.getFMeasure(beta);
		double tnFmesure = confusionMatrixTN.getFMeasure(beta);
		double fpFmesure = confusionMatrixFP.getFMeasure(beta);
		double fnFmesure = confusionMatrixFN.getFMeasure(beta);
		double mixedFmesure = confusionMatrixMixed.getFMeasure(beta);

		assertEquals(1, tpFmesure, DOUBLE_DELTA);
		assertEquals(0, tnFmesure, DOUBLE_DELTA);
		assertEquals(0, fpFmesure, DOUBLE_DELTA);
		assertEquals(0, fnFmesure, DOUBLE_DELTA);
		assertEquals(0.5, mixedFmesure, DOUBLE_DELTA);
	}

	@Test
	public void getFmesureBeta2Test() throws Exception {
		double beta = 0.0;
		double tpFmesure = confusionMatrixTP.getFMeasure(beta);
		double tnFmesure = confusionMatrixTN.getFMeasure(beta);
		double fpFmesure = confusionMatrixFP.getFMeasure(beta);
		double fnFmesure = confusionMatrixFN.getFMeasure(beta);
		double mixedFmesure = confusionMatrixMixed.getFMeasure(beta);

		assertEquals(1.0, tpFmesure, DOUBLE_DELTA);
		assertEquals(0, tnFmesure, DOUBLE_DELTA);
		assertEquals(0, fpFmesure, DOUBLE_DELTA);
		assertEquals(0, fnFmesure, DOUBLE_DELTA);
		assertEquals(0.5, mixedFmesure, DOUBLE_DELTA);
	}

	@Test
	public void getAccuracyTest() {
		double t1 = confusionMatrixTP.getAccuracy();
		double t2 = confusionMatrixTN.getAccuracy();
		double t3 = confusionMatrixFP.getAccuracy();
		double t4 = confusionMatrixFN.getAccuracy();
		double t5 = confusionMatrixMixed.getAccuracy();

		assertEquals(1, t1, DOUBLE_DELTA);
		assertEquals(1, t2, DOUBLE_DELTA);
		assertEquals(0, t3, DOUBLE_DELTA);
		assertEquals(0, t4, DOUBLE_DELTA);
		assertEquals(.5, t5, DOUBLE_DELTA);
	}
}