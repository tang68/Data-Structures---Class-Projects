package project3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class DividedDifference {
	
	public static void main(String[] args) {
		List<Double> x = new ArrayList<>();
		List<Double> y = new ArrayList<>();
		System.out.println("Enter file name (test1.txt, test2.txt, test3.txt, test4.txt): ");
		Scanner scan = new Scanner(System.in);
		String fileName = scan.next();
		String line;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			Boolean readX = false;
			while ((line = bufferedReader.readLine()) != null) {
				String[] s = line.split(" ");
				if (!readX){
					for (int i = 0; i < s.length; i++) {
						x.add(Double.parseDouble(s[i]));
					}
					readX = true;
				}
				else {
					for (int i = 0; i < s.length; i++) {
						y.add(Double.parseDouble(s[i]));
					}
				}
			}
			bufferedReader.close();
			
		} catch (FileNotFoundException ex) {
			System.out.println("***** Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("***** Error reading file '" + fileName + "'");
		}
		scan.close();
		
		List<Double> c = generateDifferenceTable(x, y);
		simplify(c, x);
		
	}
	
	private static List<Double> generateDifferenceTable(List<Double> x, List<Double> y) {
		
		List<List<Double>> table = new ArrayList<>();
		List<Double> calculatedCol  = new ArrayList<>();;
		List<Double> tmpCol;
		List<Double> c = new ArrayList<>();
		
		for (int i = 0; i < x.size(); i++) {
			tmpCol = new ArrayList<>();
			if (i == 0) {
				calculatedCol = y;
			}
			
			for (int j = 0; j < calculatedCol.size() - 1; j++) {
				Double y2 = calculatedCol.get(j+1);
				Double y1 = calculatedCol.get(j);
				Double x2 = x.get(j + 1 + i);
				Double x1 = x.get(j);
				Double newVal = (y2 - y1)/(x2 - x1);
				tmpCol.add(newVal);
			}
			table.add(calculatedCol);
			calculatedCol = tmpCol;
		}
		
		for (int i = 0; i < table.size(); i++) 
			c.add(table.get(i).get(0));

		
		//Output table
		System.out.println("Note: values in table are obtained by rounding decical numbers from input file");
		System.out.println("      to 2 decimal places, then convert to fraction, then reduce the fraction");
		System.out.println("EX:   1.5 = 150/100 =>> 3/2");
		System.out.println("      1.67 = 167/100 =>> can't reduce\n");
		System.out.print("x\t\t");
		for (int i = 0; i < x.size(); i++) {
			System.out.print("f[");
			for (int j = 0; j < i; j++){
				System.out.print(",");
			}
			System.out.print("]\t\t"); 
		}
		System.out.println();
		
		int count = table.size();
		for (int i = 0; i < x.size(); i++) {
			
			System.out.print(convertToFraction(x.get(i)));
			for (int j = 0; j < count; j++) {
				System.out.print("\t\t" + convertToFraction(table.get(j).get(i)));
			}
			count--;
			System.out.println();
		}
		System.out.println();
		
		//Output polynomial
		System.out.print("Interpolating polynomial is:\n\t");
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i) == 0 )
				continue;
			else {
				System.out.print(" " + convertToFraction(c.get(i)));
				for (int j = 0; j < i; j++) 
					System.out.printf("(x - " + convertToFraction(x.get(j)) + ")");

				if (i != c.size()-1 && c.get(i+1) > 0)
					System.out.print(" +");
			}
			
		}
		return c;
	}
	
	private static void simplify(List<Double> c, List<Double> x) {
		
		System.out.println("\n\nSimplified polynomial is:\t");
		List<List<Double>> expandedList = new ArrayList<>();
		
		for (int i = 0; i < x.size(); i++) {
			int termCount = 0;
			List<Double> tmpList = new ArrayList<>();
			
			while (termCount < i) {
				tmpList.add(x.get(termCount) * -1);
				termCount++;
			}
			
			expandedList.add(expand(c.get(i), tmpList));
			//System.out.println(expandedList.get(i));
		}
		List<Double> coeff = new ArrayList<>();
		for (int i = 0; i < expandedList.size(); i++) {
			double val = 0;
			for (int j = i; j < expandedList.size(); j++) {
				val += expandedList.get(j).get(i);
			}
			coeff.add(val);
		}
		//Collections.reverse(coeff);
		//Output
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.print(" \t");
		for (int i = coeff.size()-1; i >= 0; i--) {
			if (coeff.get(i) == 0)
				continue;
			else {
				System.out.print(df.format(coeff.get(i))  );
				if (i == 0)
					continue;
				if (i == 1)
					System.out.print("x");
				else
					System.out.print("x^" + i);
				if (i != 0)
					System.out.print(" + ");
			}
			
		}
	}
	
	private static List<Double> expand(double c, List<Double> xList) {
		
		List<Double> result = new ArrayList<>();
		if (xList.size() == 0) {
			result.add(c);
			return result;
		}
		
		else if (xList.size() == 1) {
			result.add(c*xList.get(0));
			result.add(c);
			return result;
		}
		
		else if (xList.size() == 2) {
			result.add(c*xList.get(0)*xList.get(1));
			result.add(c*(xList.get(0) + xList.get(1)));
			result.add(c);
			return result;
		}
		
		else {
			double sum = 0;
			double product = 1;
			for (int i = 0; i < xList.size(); i++) {
				sum += xList.get(i);
				product = product * xList.get(i);
			}
			result.add(1.0);
			result.add(sum);
			
			List<Double> pre = new ArrayList<>();
			pre.add((xList.get(0) + xList.get(1)) * xList.get(2) + (xList.get(0) * xList.get(1)));
			pre.add(xList.get(0) * xList.get(1) * xList.get(2));
			int lastptr = 3;
			for (int i = 0; i < xList.size()-3; i++) {
				List<Double> cur = new ArrayList<>();
				double subSum = 0;
				double subProduct = 1;
				for (int j = 0; j < lastptr; j++) {
					subSum += xList.get(j);
					subProduct = subProduct * xList.get(j);
				}
				subProduct = subProduct * xList.get(lastptr);
				cur.add(subSum * xList.get(lastptr) + pre.get(0));
				
				for (int j = 0; j < pre.size()-1; j++) {
					double x = xList.get(lastptr);
					double y = pre.get(j);
					double z = pre.get(j + 1);
					cur.add( x*y + z);
					
				}
				lastptr++;
				pre = cur;
				pre.add(subProduct);
			}
			
			for (int i = 0; i < pre.size(); i++) {
				result.add(pre.get(i));
			}
			List<Double> returnList = new ArrayList<>();
			for (int i = 0; i < result.size(); i++)
				returnList.add(result.get(i) * c);
			Collections.reverse(returnList);
			return returnList;
		}
		
	}
	
	
	private static String convertToFraction(double val) {

		if (val == Math.floor(val))
			return Double.toString(val).substring(0, Double.toString(val).length()-2);
		
		DecimalFormat df = new DecimalFormat("#.##");	
		String top = Double.toString(Double.parseDouble(df.format(val)) * 100);
		String fraction = top.substring(0, top.length()-2) + "/100";

		String result = reduceFraction(fraction);

		if (result.substring(result.length()-2).equals("/1"))
			return result.substring(0, result.length() - 2);
		else
			return result;
	}
	
	
	private static String reduceFraction(String s) {
		int top= 0;
		int bot= 0;
		String returnString = null;

		String[] f = s.split("/");
		top = Integer.parseInt(f[0]);
		bot = Integer.parseInt(f[1]);
					
		int g = GCF(top, bot);		
		top = top/g;
		bot = bot/g;
		returnString = top + "/" + bot;
		

		return returnString;
    }
	
	private static int GCF(int top, int bot) {
		while (top != 0 && bot !=0) {
			int tmp = bot;
			bot = top%bot;
			top = tmp;
		}
		return top + bot;
	}
}









