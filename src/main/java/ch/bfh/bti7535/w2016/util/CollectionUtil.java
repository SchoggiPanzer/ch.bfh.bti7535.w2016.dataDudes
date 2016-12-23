package ch.bfh.bti7535.w2016.util;

import java.util.Arrays;
import java.util.List;

public class CollectionUtil {

	public static boolean isStringInList(String input, String[] list) {
		List<String> strings = Arrays.asList(list);
		return strings.contains(input);
	}
}
