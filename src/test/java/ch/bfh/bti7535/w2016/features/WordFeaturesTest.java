package ch.bfh.bti7535.w2016.features;

import ch.bfh.bti7535.w2016.algorithm.features.AbstractFeature;
import ch.bfh.bti7535.w2016.algorithm.features.WordFeature;
import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WordFeaturesTest {

    private List<Document> docList;

    @Before
    public void setUp() throws Exception{
        docList = new ArrayList<>();

        String negContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("pos.txt").toURI())));
        List<String> negTokens = Arrays.asList(negContent.split(" "));
        docList.add(new Document(negTokens, Classification.SENTIMENT_POSITIVE));

        String posContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("neg.txt").toURI())));
        List<String> posTokens = Arrays.asList(posContent.split(" "));
        docList.add(new Document(posTokens, Classification.SENTIMENT_NEGATIVE));
    }

    @Test
    public void testWordFeature() {
        AbstractFeature wordGoodFeature = new WordFeature("good");
        wordGoodFeature.train(docList, Classification.SENTIMENT_NEGATIVE);
        wordGoodFeature.train(docList, Classification.SENTIMENT_POSITIVE);

        double negGoodResult = wordGoodFeature.getProbability(Classification.SENTIMENT_NEGATIVE);
        double posGoodResult = wordGoodFeature.getProbability(Classification.SENTIMENT_POSITIVE);

        assertEquals(0.0027, negGoodResult, 0.0001);
        assertEquals(0.0027, posGoodResult, 0.0001);

    }
}
