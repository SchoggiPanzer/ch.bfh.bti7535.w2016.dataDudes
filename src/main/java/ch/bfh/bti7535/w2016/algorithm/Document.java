package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.filehandling.Classification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {

	private String filename;
	private Classification goldStandard;
	private Classification testResult;
	private Map<String, WordProperty> content = new HashMap<>();

	public Document() {
	}

	public Document(List<String> tokens) {
		setContent(tokens);
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setGoldStandard(Classification classifier) {
		this.goldStandard = classifier;
	}

	public Classification getGoldStandard() {
		return goldStandard;
	}

	public void setContent(Map<String, WordProperty> content) {
		this.content = content;
	}

	public void setContent(List<String> content) {
		for (String token : content) {
			this.content.put(token, null);
		}
	}

	public Map<String, WordProperty> getContentWithWordProperties() {
		return content;
	}

	public List<String> getContent() {
		return (List<String>) content.keySet();
	}

	public void setTestResult(Classification testResult) {
		this.testResult = testResult;
	}

	public Classification getTestResult() {
		return testResult;
	}

	public static class WordProperty {
		private int occurence = 0;
		private Classification meaning;

		public WordProperty(int occurence, Classification meaning) {
			this.occurence = occurence;
			this.meaning = meaning;
		}

		public int getOccurence() {
			return occurence;
		}

		@Override public String toString() {
			return "WordProperty{" +
					"occurence=" + occurence +
					", meaning=" + meaning +
					'}';
		}
	}
}
