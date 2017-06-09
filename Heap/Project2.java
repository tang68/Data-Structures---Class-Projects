package maxHeap;
import java.util.*;

public class Project2 {

	public static void main(String[] args) {
	 	
	 	System.out.println("Please select how to test the program:");
	 	System.out.println("(1) 20 sets of 100 randomly generated integers");
	 	System.out.println("(2) Fixed integer values 1-100");
	 	System.out.print("Enter choice: ");
	 	
	 	Scanner scan = new Scanner(System.in);
	 	int choice = scan.nextInt();
	 	while (choice != 1 && choice != 2) {
	 		System.out.println("No such choice mate! Enter again: ");
	 		choice = scan.nextInt();
	 	}
	 	System.out.println();
	 	
	 	if (choice == 2) {
	 		MaxHeap<Integer> seriesHeap = new MaxHeap<>(100);
	 		for(int i = 1; i <= 100; i++) {
	 			seriesHeap.addSequential(i);
	 		}
	 		
	 		System.out.print("Heap built  using series of insertions: ");
	 		seriesHeap.firstTen();
	 		System.out.println("Number of Swaps: " + seriesHeap.countSwapSequential());
	 		for (int i = 0; i < 10; i++) {
	 			seriesHeap.removeMax();
	 		}
	 		
	 		System.out.print("Heap after 10 removal: ");
	 		seriesHeap.firstTen();
	 		System.out.println();
	 	
	 		//optimal method
	 		Integer[] arr = new Integer[100];
	 		for (int i = 0; i < 100; i++) {
	 			arr[i] = i+1;
	 		}
	 			
	 		MaxHeap<Integer> optimalHeap = new MaxHeap<>(100);
	 		optimalHeap.addOptimal(arr);
	 		System.out.print("Heap built using optimal method: ");
	 		optimalHeap.firstTen();
	 		System.out.println("Number of swaps: " + optimalHeap.countSwapOptimal());
	
	 		for (int i = 0; i < 10; i++) {
	 			optimalHeap.removeMax();
	 		}
	 		System.out.print("Heap after 10 removal: ");
	 		optimalHeap.firstTen();
	 		System.out.println();	
	 	}
	 	
	 	else {
	 		int swapsOfSeries = 0;
	 		int swapOfOptimal = 0;
	 		int setsOfIntegers = 20;
	 		for (int i = 0; i < setsOfIntegers; i++) {
	 			swapsOfSeries += seriesOfInsertions();
	 		}
	 		
	 		System.out.println("Average swaps for series of insertions: " 
	 				+ swapsOfSeries/setsOfIntegers);
	 		
	 		for (int i = 0; i < setsOfIntegers; i++) {
	 			swapOfOptimal += optimalInsertions();
	 		}
	 		
	 		System.out.println("Average swaps for optimal method: " 
	 				+ swapOfOptimal/setsOfIntegers);
	 			
	 		
	 	}

	}
	
	public static ArrayList<Integer> getArray(){
		Random rand = new Random();
 		int valueToAdd;
 		ArrayList<Integer> arr = new ArrayList<>();
 		while (arr.size() < 100) {
 			valueToAdd = rand.nextInt(120) + 1;
 			if (arr.contains(valueToAdd))
 				continue;
 			else
 				arr.add(valueToAdd);
 		}
 		return arr;
	}
	
	public static int seriesOfInsertions(){
		MaxHeap<Integer> series = new MaxHeap<>(100);
		for (int i = 0; i < getArray().size(); i++){
			series.addSequential(getArray().get(i));
		}
		return series.countSwapSequential();
	}
	
	public static int optimalInsertions() {
		MaxHeap<Integer> optimal = new MaxHeap<>(100);
		Integer[] arr = new Integer[getArray().size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = getArray().get(i);
		}		
		optimal.addOptimal(arr);
		return optimal.countSwapOptimal();
	}

}





