package ch.bfh.bti7535.w2016.util;

import java.util.Arrays;
import java.util.List;

/**
 * contains helper methods for collections
 */
public class CollectionUtil {

	/**
	 * checks if a word is in a list of words
	 * @param input word to search
	 * @param list list of words
	 * @return true when word is in list or false when not
	 */
	public static boolean isStringInList(String input, String[] list) {
		List<String> strings = Arrays.asList(list);
		return strings.contains(input);
	}

	/**
	 * checks which list is the smaller one
	 * @param l1 list 1
	 * @param l2 list 2
	 * @return the size of the smaller list
	 */
	public static int getSmallestSize(List l1, List l2) {
		return (l1.size() > l2.size()) ? l2.size() : l1.size();
	}

	/**
	 * checks which list is the bigger one
	 * @param l1 list 1
	 * @param l2 list 2
	 * @return the size of the bigger list
	 */
	public static int getBiggestSize(List l1, List l2) {
		return (l1.size() < l2.size()) ? l2.size() : l1.size();
	}
}
