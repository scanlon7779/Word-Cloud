package edu.ncsu.csc316.wordcloud.ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.wordcloud.manager.ReportManager;

/**
 * IU class that handles input and output for a user
 * @author colinscanlon
 *
 */
public class WordCloudUI {

	/**
	 * The main method that starts the program
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ReportManager rm = null;
		boolean b = true;
		while(b) {
			System.out.println("Specify the input file that contains the text of the literature.");
			String pathToText = in.next();
			System.out.println("You may also specify the input file that contains the text of filter words, or type na.");
			String pathToFilter = in.next();
			try {
				if ("na".equals(pathToFilter)) {
					rm = new ReportManager(pathToText, pathToFilter);
					b = false;
				} else {
					rm = new ReportManager(pathToText);
					b = false;
				}
			} catch (FileNotFoundException e) {
				System.out.println("The file you entered did not exist.");
			}
		}
		
		boolean quit = true;
		while (quit) {
			System.out.println("Would you like to Get the Frequency of a Word (Frequency), Generate a Report of the Most Frequent Words (Words), " +
		                       "Generate a Word Cloud (Cloud), or quit?");
			String next = in.next();
			if ("Frequency".equals(next)) {
				System.out.print("The word to get the frequency of: ");
				String word = in.next();
				System.out.print("\n" + rm.getFrequencyOfWord(word));
				
			} else if ("Words".equals(next)) {
				System.out.print("The number of words you would like to generate a report for: ");
				int wordNum = in.nextInt();
				System.out.print("\n" + rm.getTopWordsReport(wordNum));
				
			} else if ("Cloud".equals(next)) {
				System.out.print("The output file of the word cloud: ");
				String path = in.next();
				System.out.print("The title of the word cloud: ");
				String title = in.next();
				System.out.print("The number of words to include in the word cloud: ");
				int num = in.nextInt();
				try {
					rm.outputWordCloud(path, title, num);
				} catch (FileNotFoundException e) {
					System.out.println("Invalid file.");
				}
				
			} else if ("quit".equals(next)) {
				quit = false;
			} else {
				System.out.println("Invalid input.");
			}
			
		}
		
		
		
		
		
		
		in.close();
	}
}
