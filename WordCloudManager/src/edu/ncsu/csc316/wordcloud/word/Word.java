package edu.ncsu.csc316.wordcloud.word;

/**
 * The word objects that holds the words name and frequency as they were in the map
 * @author colinscanlon
 *
 */
public class Word implements Comparable<Word> {
	/** The key value of the word */
	private String key;
	/** the value of the word */
	private int value;
	
	/**
	 * Constructor for a word with a key and value
	 * @param key The key of the word
	 * @param value The value of the word
	 */
	public Word(String key, int value) {
		this.key = key;
		this.value = value;
	}
	
	

	/**
	 * Gets the key of the word
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets the value of the word
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	@Override
	public int compareTo(Word o) {
		if (value < o.getValue()) {
			return 1;
		} else if (value > o.getValue()) {
			return -1;
		} 
		return key.compareTo(o.getKey());
	}

}
