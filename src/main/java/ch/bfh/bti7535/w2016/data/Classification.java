package ch.bfh.bti7535.w2016.data;

/**
 * Enums for the classification
 */
public enum Classification {
	SENTIMENT_POSITIVE("pos"),
	SENTIMENT_NEGATIVE("neg"),
	NOT_CLASSIFIED("");

	private String label;

	/**
	 * Set the label
	 * @param label label to set
	 */
	Classification(String label) {
		this.label = label;
	}

	/**
	 * get the label
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
}
