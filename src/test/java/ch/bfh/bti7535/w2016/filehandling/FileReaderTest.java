package ch.bfh.bti7535.w2016.filehandling;

import ch.bfh.bti7535.w2016.algorithm.Document;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

public class FileReaderTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	@Ignore
	public void testReadFiles() throws Exception {
		ArrayList<Document> docList;

		String filepath = "./src/main/resources/review_polarity/txt_sentoken";
		FileReader rfa = new FileReader();

		docList = rfa.readAllFiles(filepath);

		//FIXME: Printing out the stuff is not really a test
		for (Document doc : docList) {
			System.out.println(doc.getFilename() + " " + doc.getGoldStandard());
		}
	}
}
