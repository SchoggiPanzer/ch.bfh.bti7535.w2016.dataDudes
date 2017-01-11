package ch.bfh.bti7535.w2016.algorithm.features;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.DocumentUtil;

import java.util.List;


public class WordCount extends AbstractFeature {


    @Override
    public double test(Document document) {
        return DocumentUtil.countWords(document);
    }

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
