package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;

public class WordFeature extends AbstractFeature {

    private String searchWord;

    public WordFeature(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public void train(List<Document> documents, Classification classification) {
        List<String> content;

        int wordAmount = 0;
        int searchWordAmount = 0;

        for (Document doc :documents) {
            if(doc.getGoldStandard().equals(classification)) {
                wordAmount += DocumentUtil.countWords(doc);
                searchWordAmount += DocumentUtil.countSpecificWord(doc, searchWord);
            }
        }

        System.out.println("SearchWordAmount: " + searchWordAmount);
        System.out.println("Total Words: " + wordAmount);
        double result = (wordAmount > 0.0001) ? (double) searchWordAmount / (double) wordAmount : 0;
        setProbability(classification, result);
    }
}