package ch.bfh.bti7535.w2016.util;

import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.data.Classification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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

	private static Document readFile(Path file, Classification classification) {
		Document doc = new Document();
		try {
			String fileContent = new String(Files.readAllBytes(file));
			String[] tokenized = fileContent.split(" ");

			Map<String, Document.WordProperty> tokens = new HashMap<>();
			for (String token : tokenized)
				tokens.put(token, new Document.WordProperty(0, Classification.NOT_CLASSIFIED));

			doc = new Document(tokens, classification);
			doc.setFilename(file.getFileName().toString());
		} catch (IOException e) {
			log.error("Cannot read and tokenize file {}", file.toAbsolutePath());
		}
		return doc;
	}
}
