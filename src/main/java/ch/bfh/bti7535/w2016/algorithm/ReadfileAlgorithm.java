package ch.bfh.bti7535.w2016.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by mischu on 05.12.16.
 */
public class ReadfileAlgorithm {

    public Document readfile(String filePath) throws FileNotFoundException {
        Document doc = new Document();
        ArrayList<String> tokens = new ArrayList<>();

        File file = new File(filePath);
        Scanner inputFile = new Scanner(file);
        StringTokenizer tokenizer;
        String line;

        while (inputFile.hasNext()) {

            line = inputFile.nextLine();
            tokenizer = new StringTokenizer(line," ");

            while (tokenizer.hasMoreTokens()) {
                tokens.add(tokenizer.nextToken());
            }
        }

        //not nice yet
        int index = filePath.lastIndexOf("/");
        String reviewClass = filePath.substring(index-3, index);

        doc.setFilename(file.getName());
        doc.setReviewClass(reviewClass);
        doc.setTokens(tokens);

        return doc;
    }
}
