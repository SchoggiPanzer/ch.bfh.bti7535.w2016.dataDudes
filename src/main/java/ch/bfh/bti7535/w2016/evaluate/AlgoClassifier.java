package ch.bfh.bti7535.w2016.evaluate;
import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.BaselineAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.Document;
import ch.bfh.bti7535.w2016.filehandling.Classification;
import ch.bfh.bti7535.w2016.filehandling.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;



public class AlgoClassifier {

    private double truePos = 0;
    private double trueNeg = 0;
    private double falsePos = 0;
    private double falseNeg = 0;

    private double delta = 0.0001;
    private double verbosity = 10;

    private String path = "./src/main/resources/review_polarity/txt_sentoken";
    private static Logger log = (Logger) LoggerFactory.getLogger(AlgoClassifier.class);


    public AlgoClassifier(AbstractAlgorithm algo) {
        FileReader fileReader = new FileReader();
        try {
            List<Document> inputdocs = fileReader.readAllFiles(path);

            List<Document> docs = algo.execute(inputdocs);
            classify(docs);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
    }

    public AlgoClassifier(AbstractAlgorithm algo, List<Document> inputdocs){
        List<Document> docs = algo.execute(inputdocs);
        classify(docs);

    }


    protected void classify(List<Document> docs) {
        truePos = 0;
        trueNeg = 0;
        falsePos = 0;
        falseNeg = 0;
        for (Document doc : docs) {
            classifySingleDoc(doc);
        }
    }

    private void classifySingleDoc(Document doc) {

        Classification gold = doc.getGoldStandard();
        Classification calculated = doc.getTestResult();
        //Does handle  notclassified as sentiment_negative
        if (gold == calculated) {
            //true
            if (gold == Classification.SENTIMENT_POSITIVE) {
                truePos++;
            } else {
                trueNeg++;
            }
        } else {
            //false
            if (calculated == Classification.SENTIMENT_POSITIVE) {
                falsePos++;
            } else {
                falseNeg++;
            }
        }
    }

    public double getPrecision() {
        double r = 0.0;
        if (truePos + falsePos < delta) {
            r = 0.0;
        } else {
            r = truePos / (truePos + falsePos);
        }

        if (verbosity > 1) {
            System.out.println("");
            System.out.println("AlgoClassifier:");
            System.out.println("precision=" + r);
        }

        if (verbosity > 10) {
            System.out.println("truePos=" + truePos);
            System.out.println("trueNeg=" + trueNeg);
            System.out.println("falsePos=" + falsePos);
            System.out.println("falseNeg=" + falseNeg);
        }
        return r;
    }


    public double getRecall() {
        double r = 0.0;
        if (truePos + falseNeg < delta) {
            r = 0.0;
        } else {
            r = truePos / (truePos + falseNeg);
        }
        return r;
    }

    public double getFmesure() {
        return getFmesure(1.0);
    }

    public double getFmesure(double beta) {
        double precision = this.getPrecision();
        double recall = this.getRecall();
        return getFmesure(beta, precision, recall);
    }

    public double getFmesure(double beta, double precision, double recall) {
        double r;
        double denominator = (beta *beta * precision) + recall;
        //odo test
        if (beta< delta ) {
            if(beta !=0) {
                throw new IllegalArgumentException("beta can not be belwo 0");
            }
        }
        if (Math.abs(denominator) < delta) {
            r = 0;
        } else {
            r = ((1 + beta * beta) * ((precision * recall)) / denominator);
        }
        return r;
    }

    public double getAccuracy() {
        return this.getAccuracy(truePos,trueNeg,falsePos,falseNeg);
    }


    private double getAccuracy(double truePos, double trueNeg, double falsePos, double falseNeg) {
        double r;
        double denominator = truePos + trueNeg + falsePos + falseNeg;
        if (denominator < delta) {
            r = 0;
        } else {
            r = truePos / denominator;
        }
        return r;
    }
}

