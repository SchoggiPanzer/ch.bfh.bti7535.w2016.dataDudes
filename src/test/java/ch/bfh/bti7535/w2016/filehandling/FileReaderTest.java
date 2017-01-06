package ch.bfh.bti7535.w2016.filehandling;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import ch.bfh.bti7535.w2016.util.FileReaderUtil;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class FileReaderTest {

	@Test
	public void testReadFilesFromPath() throws Exception {
		ArrayList<Document> documents = FileReaderUtil.readFilesFromPath(FileReaderUtil.FILE_PATH);

		assertNotNull(documents);
		assertFalse(documents.isEmpty());

		Document document = documents.get(0);
		assertNotNull(document);
		Classification goldStandard = document.getGoldStandard();
		assertNotNull(goldStandard);
		List<String> content = document.getContent();
		assertNotNull(content);
	}

	@Test
	public void testReadFile() throws Exception {
		Document document = FileReaderUtil.readFile(Paths.get("./src/test/resources/pos.txt"), Classification.SENTIMENT_POSITIVE);
		assertNotNull(document);
		List<String> content = document.getContent();
		assertFalse(content.isEmpty());
		assertEquals(424, content.size());
	}
}
