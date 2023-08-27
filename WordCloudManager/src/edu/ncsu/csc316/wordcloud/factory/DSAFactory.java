package edu.ncsu.csc316.wordcloud.factory;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.SkipListMap;
import edu.ncsu.csc316.dsa.sorter.QuickSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * Factory for creating new data structure and algorithm instances
 * 
 * @author Dr. King
 *
 */
public class DSAFactory {

	/**
	 * Returns a data structure that implements a map
	 * 
	 * @param <K>
	 *            - the key type
	 * @param <V>
	 *            - the value type
	 * @return a data structure that implements a map
	 */
	public static <K extends Comparable<K>, V> Map<K, V> getMap() {
		return getSkipListMap();
	}


	/**
	 * Returns a comparison based sorter
	 * 
	 * @param <E>
	 *            - the element type
	 * @return a comparison based sorter
	 */
	public static <E extends Comparable<E>> Sorter<E> getComparisonSorter() {
		return getQuickSorter();
	}


	/**
	 * Returns a skip list map
	 * @param <K> type of keys
	 * @param <V> type of values
	 * 
	 * @return a skip list map
	 */
	private static <K extends Comparable<K>, V> SkipListMap<K, V> getSkipListMap() {
		return new SkipListMap<K, V>();
	}

	/**
	 * Returns a quicksorter
	 * @param <E> The generic type of the sorter
	 * 
	 * @return a quicksorter
	 */
	private static <E extends Comparable<E>> Sorter<E> getQuickSorter() {
		return new QuickSorter<E>();
	}

}