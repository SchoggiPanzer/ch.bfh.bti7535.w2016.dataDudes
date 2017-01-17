package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

/**
 * This feature will count all word from a document
 */
public class WordCount extends AbstractFeature {

    /**
     * counts all words from a document
     * @param document document to test
     * @return amount of words
     */
    @Override
    public double test(Document document) {
        return DocumentUtil.countWords(document);
    }

    /**
     * trains the algorithm with a list of documents
     * @param documents the list of documents
     * @param classification positive or negative
     * @return amount of words
     */
    @Override
    public double train(List<Document> documents, Classification classification){
        double WordCount= 0;
        for (Document doc : documents) {
            if (doc.getGoldStandard().equals(classification)) {
                WordCount += DocumentUtil.countWords(doc);
            }
        }

        return WordCount;
    }
}
