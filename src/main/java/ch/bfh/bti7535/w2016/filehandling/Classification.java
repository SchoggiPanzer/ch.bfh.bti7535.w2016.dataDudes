package ch.bfh.bti7535.w2016.filehandling;

public enum Classification {
	SENTIMENT_POSITIVE("pos"),
	SENTIMENT_NEGATIVE("neg"),
	NOT_CLASSIFIED("");

	private String label;

	Classification(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
