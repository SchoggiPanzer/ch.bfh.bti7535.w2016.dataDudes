package ch.bfh.bti7535.w2016.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CollectionHelper {

	public static TreeMap<String, Integer> sortMapByValue(Map<String, Integer> map, boolean asc) {
		Comparator<String> comparator = new ValueComparator(map, asc);
		TreeMap<String, Integer> result = new TreeMap<>(comparator);
		result.putAll(map);
		return result;
	}

	private static class ValueComparator implements Comparator<String> {

		Map<String, Integer> map = new HashMap<>();
		private boolean asc;

		ValueComparator(Map<String, Integer> map, boolean asc) {
			this.map.putAll(map);
			this.asc = asc;
		}

		@Override
		public int compare(String s1, String s2) {
			if (!asc)
				return (map.get(s1) >= map.get(s2)) ? -1 : 1;
			else
				return (map.get(s1) <= map.get(s2)) ? -1 : 1;
		}
	}
}
