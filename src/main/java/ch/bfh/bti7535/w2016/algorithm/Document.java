package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.filehandling.Classification;

import java.util.HashMap;
import java.util.Map;

public class Document {

	private Classification goldStandard;
	private Classification testResult;
	private Map<String, WordProperty> content = new HashMap<>();

	public Document() {
	}

	public void setContent(Map<String, WordProperty> content) {
		this.content = content;
	}

	public Map<String, WordProperty> getContent() {
		return content;
	}

	public void setTestResult(Classification testResult) {
		this.testResult = testResult;
	}

	static class WordProperty {
		private int occurence = 0;
		private Classification meaning;

		public WordProperty(int occurence, Classification meaning) {
			this.occurence = occurence;
			this.meaning = meaning;
		}

		public int getOccurence() {
			return occurence;
		}
	}
}
