package ch.bfh.bti7535.w2016.util;

import ch.bfh.bti7535.w2016.data.Classification;
import ch.bfh.bti7535.w2016.data.Document;
import org.junit.Test;

import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;

public class DocumentUtilTest {

	@Test
	public void testCountWords() throws Exception {
		Document document = FileReaderUtil.readFile(Paths.get("./src/test/resources/pos.txt"), Classification.SENTIMENT_POSITIVE);
		assertNotNull(document);
		assertFalse(document.getContent().isEmpty());

		int totalWords = DocumentUtil.countWords(document);
		int test1 = DocumentUtil.countSingleWord(document, "if");
		int test2 = DocumentUtil.countSingleWord(document, "be");

		assertEquals(761, totalWords);
		assertEquals(2, test1);
		assertEquals(3, test2);
	}
}
