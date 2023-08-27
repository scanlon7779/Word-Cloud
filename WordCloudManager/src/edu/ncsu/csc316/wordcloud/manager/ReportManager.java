package edu.ncsu.csc316.wordcloud.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.wordcloud.factory.DSAFactory;
import edu.ncsu.csc316.wordcloud.io.InputFileReader;
import edu.ncsu.csc316.wordcloud.io.OutputFileWriter;
import edu.ncsu.csc316.wordcloud.io.WordCloudGenerator;
import edu.ncsu.csc316.wordcloud.word.Word;

/**
 * Creates textual reports of Word Cloud analysis, including reports of the
 * number of times a word is found within an input text file and a report of the
 * words most frequently used in an input file.
 * 
 * @author Colin Scanlon
 *
 */
public class ReportManager {
	
	/** Local word manager */
	private WordManager wordMan;

	/**
	 * Constructs a new word cloud report manager with the given input txt file and
	 * input filter file (See UC1)
	 * 
	 * @param pathToText   the path to the input text file
	 * @param pathToFilter the path to the input filter file
	 * @throws FileNotFoundException if either input file does not exist
	 */
	public ReportManager(String pathToText, String pathToFilter) throws FileNotFoundException {
		this.wordMan = new WordManager(InputFileReader.readFile(pathToText), InputFileReader.readFile(pathToFilter));
	}

	/**
	 * Constructs a new word cloud report manager with the given input txt file. No
	 * filter file is used. (See UC1)
	 * 
	 * @param pathToText the path to the input text file
	 * @throws FileNotFoundException if the input file does not exist
	 */
	public ReportManager(String pathToText) throws FileNotFoundException {
		List<String> inputText = InputFileReader.readFile(pathToText);
		this.wordMan = new WordManager(inputText);
	}

	/**
	 * Returns a frequency report for a given word. (See UC2)
	 * 
	 * @param word the word for which to return the frequency
	 * @return the frequency report
	 */
	public String getFrequencyOfWord(String word) {
		return "The word (" + word + ") is contained in the text " + wordMan.getFrequencyOfWord(word) + " times.";
	}

	/**
	 * Returns a report of the top X most frequent words (See UC3)
	 * 
	 * @param numberOfWords the number of words to include in the report
	 * @return the report of the top X most frequent words in the input text
	 */
	public String getTopWordsReport(int numberOfWords) {
		Word[] words = wordMan.getArray();
		StringBuilder sb = new StringBuilder("MostFrequentWords[\n");
		if ( numberOfWords <= 0 ) {
			return sb.append("   Number of words must be greater than 0.\n]\n").toString();
		}
		if (numberOfWords <= words.length) {
			for ( int i = 0; i < numberOfWords; i++) {
				sb.append( "   " + words[i].getKey() + " - " + words[i].getValue() + "\n");
			}
		} else {
			for ( int i = 0; i < words.length; i++) {
				sb.append( "   " + words[i].getKey() + " - " + words[i].getValue() + "\n");
			}
		}
		sb.append("]\n");
		return sb.toString();
	}

	/**
	 * Creates an output HTML file that contains the Word Cloud
	 * 
	 * @param path          the desired path to the output file
	 * @param title         the desired Word Cloud title
	 * @param numberOfWords the number of words to include in the Word Cloud
	 * @throws FileNotFoundException if the file cannot be created in the desired
	 *                               output path/location
	 */
	public void outputWordCloud(String path, String title, int numberOfWords) throws FileNotFoundException {
		if ( numberOfWords <= 0 ) {
			System.out.println("   Number of words must be greater than 0.");
		}
		Word[] words = wordMan.getArray();
		Map<String, Integer> m = DSAFactory.getMap();
		if (numberOfWords <= words.length) {
			for (int i = 0; i < numberOfWords; i++) {
				m.put(words[i].getKey(), words[i].getValue());
			}	
		} else {
			for (int i = 0; i < words.length; i++) {
				m.put(words[i].getKey(), words[i].getValue());
			}
		}
		if (numberOfWords <= m.size()) {
			OutputFileWriter.outputFile(path, WordCloudGenerator.getWordCloudHTML(title, m));
		} else {
			OutputFileWriter.outputFile(path, WordCloudGenerator.getWordCloudHTML(title, m));
		}
	}
}