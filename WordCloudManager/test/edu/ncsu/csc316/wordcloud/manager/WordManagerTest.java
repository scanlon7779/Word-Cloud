package edu.ncsu.csc316.wordcloud.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the WordManager and ReadManager classes
 * @author colinscanlon
 *
 */
public class WordManagerTest {

	/** The local ReportManager for testing */
	private ReportManager rm;
	
	/** The address for the input file for testing */
	private String inputText = "input/sample.txt";
	
	/** The address of the filter file */
	private String inputFilter = "input/sample-filter.txt";
	
	/**
	 * Resets the map for the next test
	 */
	@Before
	public void setUp() {
		try {
			rm = new ReportManager(inputText, inputFilter);
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	
	/**
	 * Tests the getFrequencyOfWord method
	 */
	@Test
	public void getFrequencyOfWordTest() {
		assertEquals("The word (quick) is contained in the text 1 times.", rm.getFrequencyOfWord("quick"));
		assertEquals("The word (green) is contained in the text 0 times.", rm.getFrequencyOfWord("green"));
		assertEquals("The word (the) is contained in the text 0 times.", rm.getFrequencyOfWord("the"));
        
	}
	
	/**
	 * Tests the getTopWordsReport method
	 */
	@Test
	public void getTopWordsReportTest() {
		assertEquals("MostFrequentWords[\n" + "   brown - 1\n" + "   dog - 1\n" + "   fox - 1\n" + "]\n", rm.getTopWordsReport(3));
		assertEquals("MostFrequentWords[\n" + 
				"   brown - 1\n" + 
				"   dog - 1\n" + 
				"   fox - 1\n" + 
				"   jumps - 1\n" + 
				"   lazy - 1\n" + 
				"   over - 1\n" + 
				"   quick - 1\n" + 
				"   red - 1\n" + 
				"]\n", rm.getTopWordsReport(100));
		try {
			rm = new ReportManager("input/sample2.txt");
		} catch (FileNotFoundException e) {
			fail();
		}
		
		assertEquals("MostFrequentWords[\n" + 
				"   do - 18\n" + 
				"   baby - 4\n" + 
				"]\n", rm.getTopWordsReport(2));
		assertEquals("MostFrequentWords[\n" + 
				"   Number of words must be greater than 0.\n" + 
				"]\n", rm.getTopWordsReport(-5));
	}
	
	/**
	 * Tests the outputWordCloud method
	 */
	@Test
	public void outputWordCloudTest() {
		try {
			rm.outputWordCloud("output/output.html", "Test", 5);
		} catch (FileNotFoundException e) {
			fail();
		}
		
		try {
			rm = new ReportManager("input/sample2.txt");
		} catch (FileNotFoundException e) {
			fail();
		}
		
		try {
			rm.outputWordCloud("output/output2.html", "Test", 3);
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	
}
