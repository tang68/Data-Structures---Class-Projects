/*
 * Author: Phat Tang
 * CS 311- Instructor: Dr.Sang
 * Project 2: Dynamic FSA
 * To run: Give the name of the file containing the keywords
 * 			to the initializeSymbolAndNext() method
 * 		   Give the name of the file containing the java program
 * 			to the readProgram() method
 */

package dynamicFSA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DynamicFSA {

	private static int[] switches;
	private static char[] symbol;
	private static int[] next;
	private static int symbolIndex = 0;
	private static int wordCounter = 0;
	private static boolean finishReadingKeyWords = false;
	private static Map<String, Boolean> keyWords = new HashMap<>();
	
	public static void main(String[] args) {

		initializeSwitches();
		initializeSymbolAndNext();
		readProgram();
		
		//To print the switches, symbol and next arrays
		int switchesIndex = 0;
		System.out.print("\t");
		for (int i = 0; i < 13; i++)
			System.out.printf("%5s", Character.toString((char) (i+65)));
		System.out.print("\nswitch\t");
		for (int i = 0; i < 13; i++, switchesIndex++)
			System.out.printf("%5d", switches[switchesIndex]);
		System.out.println("\n");
		
		System.out.print("\t");
		for (int i = 0; i < 13; i++)
			System.out.printf("%5s", Character.toString((char) (i+65+13)));
		System.out.print("\nswitch\t");
		for (int i = 0; i < 13; i++, switchesIndex++)
			System.out.printf("%5d", switches[switchesIndex]);
		System.out.println("\n");
		
		System.out.print("\t");
		for (int i = 0; i < 13; i++)
			System.out.printf("%5s", Character.toString((char) (i+97)));
		System.out.print("\nswitch\t");
		for (int i = 0; i < 13; i++, switchesIndex++)
			System.out.printf("%5d", switches[switchesIndex]);
		System.out.println("\n");
		
		System.out.print("\t");
		for (int i = 0; i < 13; i++)
			System.out.printf("%5s", Character.toString((char) (i+97+13)));
		System.out.print("    _    $");
		System.out.print("\nswitch\t");
		for (int i = 0; i < 13; i++, switchesIndex++)
			System.out.printf("%5d", switches[switchesIndex]);
		System.out.print("   "+ switches[switches.length-2] + "   " + switches[switches.length-1]);
		System.out.println("\n");

		
		for (int j = 0; j < symbolIndex/10 + 1; j++) {
			//start symbol and next
			
			System.out.print("\t");
			for (int i = j *10; i < j *10 + 10; i++) {
				System.out.printf("%5d", i);
			}
			System.out.println();
			System.out.print("Symbol\t");
			for (int i = j *10; i < j *10 + 10; i++) {
				System.out.printf("%5c", symbol[i]);
			}
			System.out.println();
			System.out.print("Next\t");
			for (int i = j *10; i < j *10 + 10; i++) {
				System.out.printf("%5d", next[i]);
			}
			System.out.println("\n");
		}
		

	}

	/*
	 * Read and process the java program by removing unwanted characters 
	 * and pass each word to the transition()
	 */
	private static void readProgram() {

		Scanner scan = new Scanner(System.in);
		String fileName = "java.txt";
		String line;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				line = removeUnwantedChar(line);
				String[] itemsInline = line.split(" ");
				for (int i = 0; i < itemsInline.length; i++) {
					if (itemsInline[i].equals(""))
						continue;
					else {
						System.out.print(itemsInline[i]);
						transition(itemsInline[i]);
					}
				}
				System.out.println();
			}
			bufferedReader.close();
			finishReadingKeyWords = true;

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("***** Error reading file '" + fileName + "'");
		}
		scan.close();
	}

	/*
	 * Remove unwanted characters such as ; , . { ] .....
	 */
	private static String removeUnwantedChar(String line) {
		
		line = line.replaceAll("(;|,|\\+|\\[|\\.|\\{|\\}|\\(|\\)|\\\"|\\<|\\>|\\=|"
				+ "\\:|\\-|\\]|\\!|\\*|\\/|'|&|\\@|0|1|2|3|4|5|6|7|8|9)", " ");	
		return line;
	}
	
	/*
	 * init the swticthes array to all -1
	 */
	private static void initializeSwitches() {
		switches = new int[54];
		for (int i = 0; i < switches.length; i++)
			switches[i] = -1;
	}

	/*
	 * Load the keyword file into symbol and next array by
	 * reading each word in the file, then pass each of them into the transition
	 */
	private static void initializeSymbolAndNext() {
		symbol = new char[1200];
		next = new int[1200];

		Scanner scan = new Scanner(System.in);
		String fileName = "keyword.txt";
		String line;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {

				String[] itemsInline = line.split(" ");
				for (int i = 0; i < itemsInline.length; i++) {
					transition(itemsInline[i]);
					keyWords.put(itemsInline[i], true);
				}
			}
			bufferedReader.close();
			finishReadingKeyWords = true;

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("***** Error reading file '" + fileName + "'");
		}
		scan.close();
	}

	/*
	 * Create new item in next array or create new switch symbol
	 */
	private static void create(String word, boolean writeNext, int nextPointer, int charConsumed) {

		if (writeNext) {
			next[nextPointer] = symbolIndex;
			writeToNext(word, charConsumed);

		} else {
			switches[getIndexOfStartingSym(word.charAt(0))] = symbolIndex;
			writeToNext(word, charConsumed);
		}
	}

	/*
	 * Helper method for create()
	 */
	private static void writeToNext(String word, int charConsumed) {

		for (int i = charConsumed; i < word.length(); i++) {
			symbol[symbolIndex] = word.charAt(i);
			symbolIndex++;
		}
		if (!finishReadingKeyWords) {
			symbol[symbolIndex] = '*';
			symbolIndex++;
		} else if (finishReadingKeyWords) {
			symbol[symbolIndex] = '@';
			symbolIndex++;
		}
	}

	/*
	 * Transition algorithm
	 */
	private static void transition(String word) {

		char sym = getNextSymbol(word);
		int charConsumed = 1;
		int ptr = switches[getIndexOfStartingSym(sym)];

		if (ptr == -1) {
			create(word, false, 0, 1);
			if (finishReadingKeyWords)
				System.out.print('?' + " ");
		}

		else {
			sym = getNextSymbol(word);
			boolean exit = false;
			while (!exit) {
				if (symbol[ptr] == sym) {
					if (sym != '@' && sym != '*') {
						ptr = ptr + 1;
						charConsumed++;
						sym = getNextSymbol(word);
					} else {
						System.out.print(symbol[ptr] + " ");
						exit = true;
					}
				} else {
					if (next[ptr] != 0)
						ptr = next[ptr];
					else {
						create(word, true, ptr, charConsumed);
						if (finishReadingKeyWords)
							System.out.print('?' + " ");
						exit = true;
					}
				}
			}
		}
		wordCounter = 0;
	}

	private static char getNextSymbol(String word) {
		char result = ' ';
		if (wordCounter >= word.length()) {
			if (keyWords.containsKey(word))
				return '*';
			else 
				return '@';
		}
			
		else {
			result = word.charAt(wordCounter);
			wordCounter++;
			return result;
		}
	}

	private static int getIndexOfStartingSym(char sym) {
		if (sym == '_')
			return 52;

		if (sym == '$')
			return 53;

		if ((int) sym >= 65 && (int) sym <= 90)
			return (int) sym - 65;

		else
			return (int) sym - 71;
	}

}












