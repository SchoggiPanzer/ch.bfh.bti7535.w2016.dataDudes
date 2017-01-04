package ch.bfh.bti7535.w2016.filehandling;

import ch.bfh.bti7535.w2016.algorithm.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;

public class FileReaderTest {

	@Test
	public void testReadFilesFromPath() throws Exception {
		ArrayList<Document> documents = FileReader.readFilesFromPath(FileReader.FILE_PATH);

		assertNotNull(documents);
		assertFalse(documents.isEmpty());

		Document document = documents.get(0);
		assertNotNull(document);
		Classification goldStandard = document.getGoldStandard();
		assertNotNull(goldStandard);
		List<String> content = document.getContent();
		assertNotNull(content);
	}
}
