package ch.bfh.bti7535.w2016.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {

	private String filename;
	private Classification goldStandard;
	private Classification testResult;
	private Map<String, Integer> content = new HashMap<>();

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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Classification getGoldStandard() {
		return goldStandard;
	}

	public void setGoldStandard(Classification classifier) {
		this.goldStandard = classifier;
	}

	public void setContent(Map<String, Integer> content) {
		this.content = content;
	}

	public Map<String, Integer> getContentWithWordProperties() {
		return content;
	}

	public List<String> getContent() {
		return new ArrayList<>(content.keySet());
	}

	public void setContent(List<String> content) {
		for (String token : content) {
			this.content.put(token, 1);
		}
	}

	public Classification getTestResult() {
		return testResult;
	}

	public void setTestResult(Classification testResult) {
		this.testResult = testResult;
	}

}
