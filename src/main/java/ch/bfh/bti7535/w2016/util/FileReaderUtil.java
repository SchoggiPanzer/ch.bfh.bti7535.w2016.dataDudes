package ch.bfh.bti7535.w2016.util;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FileReaderUtil {
	public static String FILE_PATH = "./src/main/resources/review_polarity/txt_sentoken/";
	private static Logger log = LoggerFactory.getLogger(FileReaderUtil.class);

	public static ArrayList<Document> readFilesFromPath(String pathName)
			throws FileNotFoundException {
		ArrayList<Document> docList = new ArrayList<>();

		String filepathPos = pathName + "pos";
		String filepathNeg = pathName + "neg";

		try (Stream<Path> pathsPos = Files.walk(Paths.get(filepathPos));
				Stream<Path> pathsNeg = Files.walk(Paths.get(filepathNeg))) {
			pathsPos.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					Document document = readFile(filePath, Classification.SENTIMENT_POSITIVE);
					docList.add(document);
				}
			});

			pathsNeg.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					Document document = readFile(filePath, Classification.SENTIMENT_NEGATIVE);
					docList.add(document);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return docList;
	}

	public static Document readFile(Path file, Classification classification) {
		Document doc = new Document();
		try {
			String fileContent = new String(Files.readAllBytes(file));
			String[] tokenized = fileContent.split("[^a-zA-Z0-9',?!.]+");

			Map<String, Integer> tokens = new LinkedHashMap<>();
			for (String token : tokenized) {
				int occurrence = 0;
				if (!tokens.containsKey(token)) {
					occurrence = 1;
				} else {
					occurrence = tokens.get(token);
					occurrence++;
				}
				tokens.put(token, occurrence);
			}

			doc = new Document(tokens, classification);
			doc.setFilename(file.getFileName().toString());
		} catch (IOException e) {
			log.error("Cannot read and tokenize file {}", file.toAbsolutePath());
		}
		return doc;
	}
}
