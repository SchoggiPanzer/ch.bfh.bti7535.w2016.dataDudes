package ch.bfh.bti7535.w2016.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Object to store all Information of a textfile
 */
public class Document {

	private String filename;
	private Classification goldStandard;
	private Classification testResult;
	private Map<String, Integer> content = new LinkedHashMap<>();

	public Document() {
	}

	public Document(List<String> content, Classification goldStandard) {
		setContent(content);
		this.goldStandard = goldStandard;
	}

	public Document(Map<String, Integer> content, Classification goldStandard) {
		this.content = content;
		this.goldStandard = goldStandard;
	}

	/**
	 * get the filename of the Document
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * set the filename of the Document
	 * @param filename the filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * get the predefined calssification of the document
	 * @return the classification
	 */
	public Classification getGoldStandard() {
		return goldStandard;
	}

	/**
	 * set the classification of the document
	 * @param classifier the classification
	 */
	public void setGoldStandard(Classification classifier) {
		this.goldStandard = classifier;
	}

	/**
	 * set the content of the document in form of a Map with words and their occurrence
	 * @param content Map with words and occurrence
	 */
	public void setContent(Map<String, Integer> content) {
		this.content = content;
	}

	/**
	 * set the content of the document in form of a List with words
	 * @param content List with the words
	 */
	public void setContent(List<String> content) {
		for (String token : content) {
			this.content.put(token, 1);
		}
	}

	/**
	 * get the content in form of a Map with words and their occurrence
	 * @return Map with words and occurrence
	 */
	public Map<String, Integer> getContentWithWordProperties() {
		return content;
	}

	/**
	 * get the content in form of a list with words
	 * @return List with words
	 */
	public List<String> getContent() {
		return new ArrayList<>(content.keySet());
	}

	/**
	 * get the classification of the test
	 * @return classification result
	 */
	public Classification getTestResult() {
		return testResult;
	}

	/**
	 * set the test result classification
	 * @param testResult test result classification
	 */
	public void setTestResult(Classification testResult) {
		this.testResult = testResult;
	}

}
