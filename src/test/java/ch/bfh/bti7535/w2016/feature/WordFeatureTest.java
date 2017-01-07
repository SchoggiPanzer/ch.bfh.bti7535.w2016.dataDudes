package ch.bfh.bti7535.w2016.feature;

import ch.bfh.bti7535.w2016.algorithm.features.AbstractFeature;
import ch.bfh.bti7535.w2016.algorithm.features.WordFeature;
import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.FileReaderUtil;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WordFeatureTest {

    private List<Document> docList;

    @Before
    public void setUp() throws Exception {
        docList = new ArrayList<>();
        docList.add(FileReaderUtil.readFile(Paths.get("./src/test/resources/pos.txt"), Classification.SENTIMENT_POSITIVE));
        docList.add(FileReaderUtil.readFile(Paths.get("./src/test/resources/neg.txt"), Classification.SENTIMENT_NEGATIVE));
    }

    @Test
    public void testWordFeature() {
        AbstractFeature wordGoodFeature = new WordFeature("good");

        wordGoodFeature.train(docList);

        double negGoodResult = wordGoodFeature.getProbability(Classification.SENTIMENT_NEGATIVE);
        double posGoodResult = wordGoodFeature.getProbability(Classification.SENTIMENT_POSITIVE);

        assertEquals(0.0026, posGoodResult, 0.0001);
        assertEquals(0.0025, negGoodResult, 0.0001);

        AbstractFeature wordLikeFeature = new WordFeature("like");
        wordLikeFeature.train(docList);

        double negLikeResult = wordLikeFeature.getProbability(Classification.SENTIMENT_NEGATIVE);
        double posLikeResult = wordLikeFeature.getProbability(Classification.SENTIMENT_POSITIVE);

        assertEquals(0.0052, posLikeResult, 0.0001);
        assertEquals(0.0038, negLikeResult, 0.0001);

    }
}
