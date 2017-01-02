package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AlgoClassifierTest {
	private double delta = 0.0001;
	private int times = 10;
	private List<Document> l = new ArrayList();

	private DummyAlgo tp = new DummyAlgo();
	private DummyAlgo tn = new DummyAlgo();
	private DummyAlgo fp = new DummyAlgo();
	private DummyAlgo fn = new DummyAlgo();
	private DummyAlgo mixed = new DummyAlgo();

	private AlgoClassifier tpA;
	private AlgoClassifier tnA;
	private AlgoClassifier fpA;
	private AlgoClassifier fnA;
	//private AlgoClassifier mixedA;
	private DummyAlgoClassifier mixedA;
	/*
	private List<Document> tpL  = new ArrayList();
    private List<Document> tnL = new ArrayList();
    private List<Document> fpL  = new ArrayList();
    private List<Document> fnL= new ArrayList();
    private List<Document> mixedL= new ArrayList();
    */

	private ArrayList<Document> results = new ArrayList();
	private List<Document> r = new ArrayList<>();

	@Before
	public void initialize() {
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
		mixedA = new DummyAlgoClassifier(mixed);

        /*
         tpL = tp.execute(l);
         tnL = tn.execute(l);
         fpL = fp.execute(l);
         fnL = fn.execute(l);
         mixedL = mixed.execute(l);
         */
	}

	private double getPrecision(AlgoClassifier algoClassifier) {
		double r = algoClassifier.getPrecision();
		return r;
	}

	@Test
	public void getPrecisionTest() throws Exception {

		double tpPrecision = this.getPrecision(tpA);
		double tnPrecision = this.getPrecision(tnA);
		double fpPrecision = this.getPrecision(fpA);
		double fnPrecision = this.getPrecision(fnA);
		double mixedPrecision = this.getPrecision(mixedA);

		assertEquals(1, tpPrecision, delta);
		assertEquals(0, tnPrecision, delta);
		assertEquals(0, fpPrecision, delta);
		assertEquals(0, fnPrecision, delta);
		assertEquals(0.5, mixedPrecision, delta);

	}

	private double getRecall(AlgoClassifier algoClassifier) {
		double r = algoClassifier.getRecall();
		return r;

	}

	@Test
	public void getRecallTest() throws Exception {
		double tpRecall = this.getRecall(tpA);
		double tnRecall = this.getRecall(tnA);
		double fpRecall = this.getRecall(fpA);
		double fnRecall = this.getRecall(fnA);
		double mixedRecall = this.getRecall(mixedA);

		assertEquals(1, tpRecall, delta);
		assertEquals(0, tnRecall, delta);
		assertEquals(0, fpRecall, delta);
		assertEquals(0, fnRecall, delta);
		assertEquals(0.5, mixedRecall, delta);

	}

	private double getFmesure(AlgoClassifier algoClassifier) {
		double r = algoClassifier.getFmesure();
		return r;
	}

	private double getFmesureBeta(AlgoClassifier algoClassifier, double beta) {
		double r = algoClassifier.getFmesure(beta);
		return r;
	}

	@Test
	public void getFmesureTest() throws Exception {
		double tpFmesure = this.getFmesure(tpA);
		double tnFmesure = this.getFmesure(tnA);
		double fpFmesure = this.getFmesure(fpA);
		double fnFmesure = this.getFmesure(fnA);
		double mixedFmesure = this.getFmesure(mixedA);

		assertEquals(1, tpFmesure, delta);
		assertEquals(0, tnFmesure, delta);
		assertEquals(0, fpFmesure, delta);
		assertEquals(0, fnFmesure, delta);
		assertEquals(0.5, mixedFmesure, delta);

	}

	@Test
	public void getFmesure1Test() throws Exception {
		double beta = 3.0;
		double tpFmesure = this.getFmesureBeta(tpA, beta);
		double tnFmesure = this.getFmesureBeta(tnA, beta);
		double fpFmesure = this.getFmesureBeta(fpA, beta);
		double fnFmesure = this.getFmesureBeta(fnA, beta);
		double mixedFmesure = this.getFmesureBeta(mixedA, beta);

		assertEquals(1, tpFmesure, delta);
		assertEquals(0, tnFmesure, delta);
		assertEquals(0, fpFmesure, delta);
		assertEquals(0, fnFmesure, delta);
		assertEquals(0.5, mixedFmesure, delta);

        /*
        beta = 4.0;
        tpFmesure= this.getFmesureBeta(tpA,beta);
        tnFmesure= this.getFmesureBeta(tnA,beta);
        fpFmesure= this.getFmesureBeta(fpA,beta);
        fnFmesure= this.getFmesureBeta(fnA,beta);
        mixedFmesure= this.getFmesureBeta(mixedA,beta);

        assertEquals(1,tpFmesure,delta);
        assertEquals(0,tnFmesure,delta);
        assertEquals(0,fpFmesure,delta);
        assertEquals(0,fnFmesure,delta);
        assertEquals(0.5,mixedFmesure,delta);

        */
		beta = 0.0;
		tpFmesure = this.getFmesureBeta(tpA, beta);
		tnFmesure = this.getFmesureBeta(tnA, beta);
		fpFmesure = this.getFmesureBeta(fpA, beta);
		fnFmesure = this.getFmesureBeta(fnA, beta);
		mixedFmesure = this.getFmesureBeta(mixedA, beta);

		assertEquals(1.0, tpFmesure, delta);
		assertEquals(0, tnFmesure, delta);
		assertEquals(0, fpFmesure, delta);
		assertEquals(0, fnFmesure, delta);
		assertEquals(0.5, mixedFmesure, delta);

        /*
        double tpFmesure= this.getFmesure(tpA);
        double tnFmesure= this.getFmesure(tnA);
        double fpFmesure= this.getFmesure(fpA);
        double tpFmesure= this.getFmesure(tpA);
        double tnFmesure= this.getFmesure(tnA);
        double fpFmesure= this.getFmesure(fpA);
        double fnFmesure= this.getFmesure(fnA);
        double mixedFmesure= this.getFmesure(mixedA);

        assertEquals(1,tpFmesure,delta);
        assertEquals(0,tnFmesure,delta);
        assertEquals(0,fpFmesure,delta);
        assertEquals(0,fnFmesure,delta);
        assertEquals(0.5,mixedFmesure,delta);
        double fnFmesure= this.getFmesure(fnA);
        double mixedFmesure= this.getFmesure(mixedA);

        assertEquals(1,tpFmesure,delta);
        assertEquals(0,tnFmesure,delta);
        assertEquals(0,fpFmesure,delta);
        assertEquals(0,fnFmesure,delta);
        assertEquals(0.5,mixedFmesure,delta);
        assertEquals(0,fnFmesure,delta);
        assertEquals(0.5,mixedFmesure,delta);

        beta = 0.5;
        tpFmesure= this.getFmesureBeta(tpA,beta);
        tnFmesure= this.getFmesureBeta(tnA,beta);
        fpFmesure= this.getFmesureBeta(fpA,beta);
        fnFmesure= this.getFmesureBeta(fnA,beta);
        mixedFmesure= this.getFmesureBeta(mixedA,beta);

        assertEquals(0.83333333333,tpFmesure,delta);
        assertEquals(0,tnFmesure,delta);
        assertEquals(0,fpFmesure,delta);
        assertEquals(0,fnFmesure,delta);
        assertEquals(0.41666666,mixedFmesure,delta);

        beta = -2.0;
        tpFmesure= this.getFmesureBeta(tpA,beta);
        tnFmesure= this.getFmesureBeta(tnA,beta);
        fpFmesure= this.getFmesureBeta(fpA,beta);
        fnFmesure= this.getFmesureBeta(fnA,beta);
        mixedFmesure= this.getFmesureBeta(mixedA,beta);

        assertEquals(-5,tpFmesure,delta);
        assertEquals(0,tnFmesure,delta);
        assertEquals(0,fpFmesure,delta);
        assertEquals(0,fnFmesure,delta);
        assertEquals(-2.5,mixedFmesure,delta);

*/
	}

	private double getAccuracy(AlgoClassifier algoClassifier) {
		double r = algoClassifier.getAccuracy();
		return r;
	}

	@Test
	public void getAccuracyTest() {
		double t1 = tpA.getAccuracy();
		double t2 = tnA.getAccuracy();
		double t3 = fpA.getAccuracy();
		double t4 = fnA.getAccuracy();
		double t5 = mixedA.getAccuracy();

		assertEquals(1, t1, delta);
		assertEquals(0, t2, delta);
		assertEquals(0, t3, delta);
		assertEquals(0, t4, delta);
		assertEquals(.25, t5, delta);
	}
}