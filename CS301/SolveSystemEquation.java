package solveSystemEquation;
/*
 * Author: Phat Tang
 * Cal Poly Pomona- CS 301.01 Fall 2017
 * Project 1
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SolveSystemEquation {

	private static double[][] coeff;
	private static int numEquations;
	private static double[] originalBottom;
	private static ArrayList<Integer> processedRows;
	
	public static void main(String[] args) {
		long startTime = 0;
		
		System.out.println("How many number of linear equations to solve? ");
		Scanner scan = new Scanner(System.in);
		numEquations = scan.nextInt();
		int inputType;
		do {
			System.out.println("How to enter the coefficients?\n\t(1) Provide a file name "
					+ "\n\t(2) Enter the coefficients of each equation");
			inputType = scan.nextInt();
		} while (inputType != 1 && inputType != 2);

		coeff = new double[numEquations][numEquations + 1];

		if (inputType == 1) {
			startTime = System.currentTimeMillis();
			coeff = getCoeffWithFile(coeff);
		} else {
			coeff = getCoeffInConsole(coeff);
		}

		solveEquations(coeff);
		scan.close();
		long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");

	}

	private static double[][] getCoeffWithFile(double[][] matrix) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the file's name (include file extension): ");
		String fileName = scan.next();
		String line;
		
		System.out.println();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tmp = line.split(" ");
				
				for (int col = 0; col < tmp.length; col++) {
					matrix[row][col] = Integer.parseInt(tmp[col]);
				}
				row++;
			}
			bufferedReader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("***** Error reading file '" + fileName + "'");
		}
		scan.close();
	
		return matrix;
	}

	private static double[][] getCoeffInConsole(double[][] matrix) {
		System.out.println("Enter a " + numEquations + " x " + (numEquations + 1) + " matrix:");
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j <= matrix.length; j++) {
				matrix[i][j] = scan.nextDouble();
			}
		}
		scan.close();
		return matrix;
	}

	private static void solveEquations(double[][] matrix) {

		getOriginalBottom(matrix);
		processedRows = new ArrayList<>();
		
		for (int k = 0; k < matrix.length - 1; k++) {
			double[] ratio = getRatios(matrix, k);
			int pivotRow = 0;
			
			//this loop is to find the max ratio
			double maxRatio = ratio[0];
			int ratioCounter;
			for (ratioCounter = 1; ratioCounter < ratio.length; ratioCounter++) {
				if (ratio[ratioCounter] > maxRatio){
					maxRatio = ratio[ratioCounter];
					pivotRow = ratioCounter;
				}	
			}
			
			//elimination process
			processedRows.add(pivotRow);
			double[] pivotedCoeff = matrix[pivotRow];
			for (int i = 0; i < matrix.length; i++) {
				if (i == pivotRow || processedRows.contains(i))
					continue;		
				else {
					if (!processedRows.contains(i)) {
						double multiple = getMultiple(pivotedCoeff[k], matrix[i][k]);
						for (int j = 0; j <= matrix.length; j++) {	
							matrix[i][j] = pivotedCoeff[j] * multiple + matrix[i][j];
							if (Math.abs(matrix[i][j]) < 0.001){
								matrix[i][j] = 0;
							}
							
						}
					}
				}
			}
		}
		double[] answers = subBack(matrix);
		for (int i = answers.length - 1; i >= 0; i--)
			System.out.println("X" + (answers.length - i) + " = " + answers[i]);
	}
	
	//back substitution process
	private static double[] subBack(double[][] matrix){
		double[] answers = new double[matrix.length];
		int[] subOrders = new int[matrix.length];
	
		//this loop is to get the order of the rows that we should substitute back
		for (int i = 0; i < matrix.length; i++) {
			int zeroCounter = 0;
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] == 0) {
					zeroCounter++;
				}
				else {
					break;
				}
			}
			
			subOrders[matrix.length - zeroCounter - 1] = i;
		}
		
		//this part is to calculate solution based on the order above
		int row = subOrders[0];
		answers[0] = matrix[row][matrix.length]/matrix[row][matrix.length-1];
		
		int counter = matrix.length - 2;
		for (int i = 1; i < subOrders.length; i++){
			row = subOrders[i];
			double total = 0;
			for (int j = matrix.length - 1; j > counter; j--) {
				total += matrix[row][j] * answers[matrix.length - j-1];
			}
			

			answers[i] = (matrix[row][matrix.length] - total)/matrix[row][matrix.length -i -1];
			counter--;
			if (counter == -1)
				break;
		
		}
		
		return answers;
	}
	
	//the pivot row will be multiplied by this value then added to the other rows
	private static double getMultiple(double pivoted, double changingRow){
		return -1 * (changingRow/pivoted);
	}

	//get the ratios for each time we eliminate a row
	private static double[] getRatios(double[][] matrix, int counter) {
		double[] ratio = new double[matrix.length];
	
		for (int i = 0; i < matrix.length; i++){
			if (!processedRows.contains(i))
				ratio[i] = Math.abs(matrix[i][counter] / originalBottom[i]);	
			else
				ratio[i] = 0;
		}
		
		return ratio;
	}
	
	//get and keep the original denominators of the ratios, since we always divide by this value
	private static void getOriginalBottom(double[][] matrix) {
		originalBottom = new double[matrix.length];
		
		for (int i = 0; i < matrix.length; i++){
			originalBottom[i] = Math.abs(matrix[i][0]);
			for (int j = 1; j < matrix.length; j++){
				if (Math.abs(matrix[i][j]) > originalBottom[i]){
					originalBottom[i] = Math.abs(matrix[i][j]);
				}
			}
		}
	}
	
}















