package ch.bfh.bti7535.w2016.algorithm;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NaiveBayesAlgorithmTest {

	private List<Document> docList;

	@Before
	public void setUp() throws Exception {
		docList = new ArrayList<>();
		String posContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("neg.txt").toURI())));
		List<String> posTokens = Arrays.asList(posContent.split(" "));
		docList.add(new Document(posTokens));

		String negContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("pos.txt").toURI())));
		List<String> negTokens = Arrays.asList(negContent.split(" "));
		docList.add(new Document(negTokens));
	}

	@Test
	public void testExecute() throws Exception {
		NaiveBayesAlgorithm naiveBayesAlgorithm = new NaiveBayesAlgorithm();
		List<Document> execute = naiveBayesAlgorithm.execute(docList);


	}
}
