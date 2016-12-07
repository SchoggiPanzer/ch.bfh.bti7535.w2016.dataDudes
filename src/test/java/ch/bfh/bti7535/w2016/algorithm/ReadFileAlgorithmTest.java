package ch.bfh.bti7535.w2016.algorithm;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ReadFileAlgorithmTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testExecute() throws Exception {
		ArrayList<Document> docList;

		String filepath = "./src/main/resources/review_polarity/txt_sentoken";
		ReadfileAlgorithm rfa = new ReadfileAlgorithm();

		docList = rfa.readAllFiles(filepath);

		for (Document doc : docList) {
			System.out.println(doc.getFilename() + " " + doc.getGoldStandard());
		}
	}
}
