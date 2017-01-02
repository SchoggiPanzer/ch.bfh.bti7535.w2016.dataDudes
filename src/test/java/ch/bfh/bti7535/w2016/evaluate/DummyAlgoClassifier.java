/*
* Copyright (c) 2017 Berner Fachhochschule, Switzerland.
*
* Project Smart Reservation System.
*
* Distributable under GPL license. See terms of license at gnu.org.
*/
package ch.bfh.bti7535.w2016.evaluate;

import ch.bfh.bti7535.w2016.algorithm.AbstractAlgorithm;
import ch.bfh.bti7535.w2016.algorithm.Document;

import java.util.ArrayList;
import java.util.List;

public class DummyAlgoClassifier extends AlgoClassifier {

	public DummyAlgoClassifier(AbstractAlgorithm algo) {
		super(algo);
	}

	public void DummyAlgoClassifier(AbstractAlgorithm algo) {
		List<Document> empty = new ArrayList<>();
		List<Document> docs;
		docs = algo.execute(empty);
		super.classify(docs);
	}

}
