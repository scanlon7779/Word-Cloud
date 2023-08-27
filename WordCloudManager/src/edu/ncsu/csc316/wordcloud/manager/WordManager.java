package edu.ncsu.csc316.wordcloud.manager;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.sorter.Sorter;
import edu.ncsu.csc316.wordcloud.factory.DSAFactory;
import edu.ncsu.csc316.wordcloud.word.Word;

/**
 * The WordManager processes a list of input text and computes frequencies of
 * the words that appear in the input list. Optionally, the program accepts an
 * input list that contains filter words that should be excluded from the
 * frequency analysis.
 * 
 * @author Colin Scanlon
 *
 */
public class WordManager {
	/** The list of title string words */
	private List<String> inputText;
	
	/** The list of filter words */
	private List<String> filterText;
	
	/** The map of words */
	private Map<String, Integer> wordsInput;
	
	//private Map<String, Integer> wordsSorted;
	
	/** The map of filter words */
	private Map<String, Integer> filter;
	
	/** The array of sorted word objects */
	private Word[] wordArray;
	

	/**
	 * Constructs a new word manager with the given input list of text and input filter
	 * word list (See UC1)
	 * 
	 * @param inputText the list of input text to analyze
	 * @param filterText the list of words to omit from frequency analysis
	 */
	public WordManager(List<String> inputText, List<String> filterText) {
		this.inputText = inputText;
		this.filterText = filterText;
		this.filter = loadFilter();
		this.wordsInput = getFrequencies();
		
	}

	/**
	 * Constructs a new word manager with the given input list of words. No words are
	 * filtered. (See UC1)
	 * 
	 * @param inputText the list of words to analyze
	 */
	public WordManager(List<String> inputText) {
		this.inputText = inputText;
		this.filterText = null;
		this.filter = DSAFactory.getMap();
		this.wordsInput = getFrequencies();
	}

	/**
	 * Returns the number of times the given word (converted to lowercase) appears
	 * in the input text. (See UC2)
	 * 
	 * @param word the word for which to return the frequency
	 * @return the number of times the word was contained in the input file
	 */
	public int getFrequencyOfWord(String word) {
		Integer n = wordsInput.get(word.toLowerCase());
		if ( n == null) {
			return 0;
		}
		return n.intValue();
	}

	/**
	 * Returns a mapping of words (lowercase) to frequencies (See UC3)
	 * 
	 * @return a map of word frequencies
	 */
	public Map<String, Integer> getFrequencies() {
		wordsInput = DSAFactory.getMap();
		int i = 0; 
		
		while (i < inputText.size()) {
			String word = inputText.removeFirst().toLowerCase();
			if (filter.get(word) == null) {
				Integer frequency = wordsInput.get(word);
				if (frequency == null) {
					wordsInput.put(word, 1);
				} else {
					wordsInput.put(word, frequency + 1);
				}
			}
			inputText.addLast(word);
			i++;
		}
		makeArray();
        Sorter<Word> sorter = DSAFactory.getComparisonSorter();
		sorter.sort(wordArray);
		//sortMap();
		
		return wordsInput;
	}
	
	
	//private void sortMap() {
		//wordsSorted = DSAFactory.getMap();
		//for (int i = wordsInput.size() - 1; i >=0; i--) {
			//wordsSorted.put(wordArray[i].getKey(), wordArray[i].getValue());
		//}
		
	//}

	/**
	 * Loads the filter with map entries
	 * @return the map of filter entries
	 */
	private Map<String, Integer> loadFilter() {
		Map<String, Integer> m = DSAFactory.getMap();
		while (!filterText.isEmpty()) {
			m.put(filterText.removeFirst().toLowerCase(), 1);
		}
		return m;
	}
	
	/**
	 * Makes an array with all the map entries
	 */
	private void makeArray() {
		Iterator<Map.Entry<String, Integer>> it = wordsInput.entrySet().iterator();
		wordArray = new Word[wordsInput.size()];
		int i = 0;
		while(it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			wordArray[i] = new Word(entry.getKey(), entry.getValue());
			i++;
		}
		
	}
	
	/**
	 * Returns the sorted array of word objects
	 * @return The local sorted array of words
	 */
	public Word[] getArray() {
		return wordArray;
	}
	
	
}

