package ch.bfh.bti7535.w2016.util;

import java.util.Arrays;
import java.util.List;

public class CollectionUtil {

	public static boolean isStringInList(String input, String[] list) {
		List<String> strings = Arrays.asList(list);
		return strings.contains(input);
	}

	public static int getSmallestSize(List l1, List l2) {
		return (l1.size() > l2.size()) ? l2.size() : l1.size();
	}

	public static int getBiggestSize(List l1, List l2) {
		return (l1.size() < l2.size()) ? l2.size() : l1.size();
	}
}
