package ch.bfh.bti7535.w2016.algorithm;

import ch.bfh.bti7535.w2016.filehandling.Classification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Document {

	private String filename;
	private String reviewClass;
	private Classification goldStandard;
	private Classification testResult;
	private Map<String, WordProperty> content = new HashMap<>();
	private ArrayList<String> tokens = new ArrayList<>();

	public Document() {
	}

	public void setFilename(String filename) { this.filename = filename; }

	public String getFilename() { return filename; }

	public void setReviewClass(String reviewClass) { this.reviewClass = reviewClass; }

	public String getReviewClass() { return reviewClass; }

	public void setTokens(ArrayList<String> tokens) {
		this.tokens = tokens;
	}

	public ArrayList<String> getTokens() {
		return tokens;
	}

	public void setContent(Map<String, WordProperty> content) { this.content = content; }

	public Map<String, WordProperty> getContent() { return content; }

	public void setTestResult(Classification testResult) { this.testResult = testResult; }

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
