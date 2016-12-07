package ch.bfh.bti7535.w2016.algorithm;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by mischu on 07.12.16.
 */
public class ReadFileAlgorithmTest {

    @Before
    public void setUp () throws Exception {

    }

    @Test
    public void testExecute () throws Exception {

        String filepath = "./src/main/resources/review_polarity/txt_sentoken/neg/cv000_29416.txt";
        Document testDoc = new Document();
        ReadfileAlgorithm rfa = new ReadfileAlgorithm();

        testDoc = rfa.readfile(filepath);

        System.out.println("Review Class: " + testDoc.getReviewClass());
        System.out.println("Filename: " + testDoc.getFilename());
        System.out.println("Tokens:" + testDoc.getTokens());
    }
}
