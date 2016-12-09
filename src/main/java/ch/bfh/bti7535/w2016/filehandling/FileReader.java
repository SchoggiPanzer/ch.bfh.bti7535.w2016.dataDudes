package ch.bfh.bti7535.w2016.filehandling;

import ch.bfh.bti7535.w2016.algorithm.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileReader {

	public Document readFile(File file, String filePath)
			throws FileNotFoundException {
		Document doc = new Document();
		Map<String, Document.WordProperty> tokens = new HashMap<>();

		Scanner inputFile = new Scanner(file);
		StringTokenizer tokenizer;
		String line;

		while (inputFile.hasNext()) {

			line = inputFile.nextLine();
			tokenizer = new StringTokenizer(line, " ");

			while (tokenizer.hasMoreTokens()) {
				tokens.put(tokenizer.nextToken(), null);
			}
		}

		//TODO: not nice yet
		int index = filePath.lastIndexOf("/");
		String reviewClass = filePath.substring(index + 1);

		if (reviewClass.equals(Classification.SENTIMENT_POSITIVE.getLabel())) {
			doc.setGoldStandard(Classification.SENTIMENT_POSITIVE);
		} else if (reviewClass.equals(Classification.SENTIMENT_NEGATIVE.getLabel())) {
			doc.setGoldStandard(Classification.SENTIMENT_NEGATIVE);
		}

		doc.setFilename(file.getName());
		doc.setContent(tokens);

		return doc;
	}

	public ArrayList<Document> readAllFiles(String pathName)
			throws FileNotFoundException {
		ArrayList<Document> docList = new ArrayList<>();

		File root = new File(pathName);
		File[] subFolders = root.listFiles();

		//TODO: Ugly nesting. Could be improved
		if (subFolders != null) {
			for (File file : subFolders) {
				if (file.isDirectory()) {
					File[] fileList;
					fileList = file.listFiles();

					if (fileList != null) {
						for (File txtFile : fileList) {
							Document doc;
							doc = readFile(txtFile, file.getName());
							docList.add(doc);
						}
					}
				}
			}
		}

		return docList;
	}
}
