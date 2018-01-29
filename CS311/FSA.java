package project1FSA;

import java.util.*;
import java.io.*;

public class FSA {

	private static int states = 0;
	private static int FSACount = 1;
	private static Boolean[] finalStates = null;
	private static Map<String, Integer> alphabet = null;
	private static ArrayList<ArrayList<String>> transitions = null;
	private static String fileName = "FSAInput.txt";
	private static String line = null;
	
	public static void main(String[] args) {		
		try {
			Boolean newStateFlag = true;
    		Boolean newFinalStatesFlag = true;
    		Boolean newAlphabetFlag = true;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
           
            while(line != null) { 	
            	//-------------------------------------
        		// FSA definition
            	//-------------------------------------
        		if (newStateFlag) {
        			System.out.println("Finite State Automaton " + FSACount);    
        			states = Integer.parseInt(line);
        			newStateFlag = false;
        			System.out.println("(1) Number of states: " + states);
        			continue;
        		}
        		
        		if (newFinalStatesFlag) {
        			//get final states into array
        			line = bufferedReader.readLine();
        			finalStates = new Boolean[states];
        			String[] tempFinalStates = line.split(" ");
        			String output = "";
        			
        			for (int i = 0; i < states; i++) {      				
        				for (int j = 0; j < tempFinalStates.length; j++) {
        					int tmp = Integer.parseInt(tempFinalStates[j]);
        					if (tmp == i) {
            					finalStates[i] = true;
            					output += Integer.toString(i) + ", ";
            					break;
            				} else {finalStates[i] = false;}
        				}		
        			}
        			output = output.substring(0, (output.length() - 2));
        			System.out.println("(2) Final States: " + output);
        			newFinalStatesFlag = false;
        			continue;
        		}
        		
        		if (newAlphabetFlag) {
        			//get alphabet list
        			line = bufferedReader.readLine();
        			String[] tempAlphabet = line.split(" ");
        			alphabet = new HashMap<>();
        			System.out.print("(3) Alphabet: ");
        			for (int i = 0; i < tempAlphabet.length; i++) {
        				alphabet.put(tempAlphabet[i], i);
        				System.out.print(tempAlphabet[i] + " ");
        			}
        			newAlphabetFlag = false;
        			System.out.println();
        			continue;
        		}
        		
        		transitions = new ArrayList<>();
        		while ((line = bufferedReader.readLine()).startsWith("(")) {
        			//read transitions        			
        			//line = bufferedReader.readLine();
        			line = line.substring(1, line.length() - 1);
        			String[] lineBreak = line.split(" ");
        			ArrayList<String> tmpArray = new ArrayList<>();
        			tmpArray.add(lineBreak[0]);
        			tmpArray.add(lineBreak[1]);
        			tmpArray.add(lineBreak[2]);
        			
        			transitions.add(tmpArray);
        		}
        		System.out.println("(4) Transitions: ");
        		for (int i = 0; i < transitions.size(); i++) 
        			System.out.println("\t" + transitions.get(i));
        		
        		//-------------------------------------
        		// End FSA definition
        		//-------------------------------------
        		
        		//Start check strings with current FSA, 
        		//outer loop to process all strings, 
        		//inner loop to process all symbols in 1 string
        		
        		System.out.println("(5) Strings: ");
        		while (line != null) {
        			//read strings        			
        			String currentString = line;
        			Boolean stop = false;
        			int symbolCounter = 0;    
        			String currentState = "0";
        			Boolean result = false;
        			currentString = currentString + "\n";

        			while (symbolCounter < currentString.length() && !stop) {      				
        				String symbol = String.valueOf(currentString.charAt(symbolCounter));           			
            			if (alphabet.containsKey(symbol)) {
            				currentState = getNextState(currentState, symbol);
            				if (currentState == null){
            					stop = true;
            					result = false;
            				}
            			}            			
            			else {
            				stop = true;
            				if (!symbol.equals("\n")) 
            					result = false;
            				else if (finalStates[Integer.parseInt(currentState)] == true)
            					result = true;
            				else
            					result = false;
            			}
            			symbolCounter++;
        			}
        			currentString = currentString.substring(0, currentString.length()- 1);
        			if (result == true)
        				System.out.println("\t" + currentString + "\tAccept");
        			else
        				System.out.println("\t" + currentString + "\tReject");

        			line = bufferedReader.readLine();
        			if (line != null && line.length() > 0 && line.charAt(0) == '/'){
        					break;
        			}
        		}
        		//End reading all strings for current FSA
        		
        		//-------------------------------
        		//reset flags, moves to next FSA
        		//-------------------------------
        		
        		System.out.println();
        		newStateFlag = true;
        		newFinalStatesFlag = true;
        		newAlphabetFlag = true;
        		FSACount++;
        		line = bufferedReader.readLine();
        		
            }   

            bufferedReader.close();   		
		}	
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
		catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
        }
	}
	
	public static String getNextSymbol(int transCounter) {	
			return transitions.get(transCounter).get(1);
	}
	
	public static String getNextState(String state, String symbol) {		
		for (int i = 0; i < transitions.size(); i++) {
			if (transitions.get(i).get(0).equals(state) && transitions.get(i).get(1).equals(symbol)){
				return transitions.get(i).get(2);
			}
		}
		return null;
	}

}





















