package src;
import java.util.*;
public class BubbleSort {

	public static void main(String[] args) {
		
		int[] a = new int[20];
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			a[i] = rand.nextInt(30);
		}
		
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");
		bubbleSort(a);
		System.out.println();
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");
	}
	
	public static void bubbleSort(int[] a) {
		int size = a.length;
		boolean swapped;
		do {
			swapped = false;
			for (int i = 0; i < size -1 ; i++) {
				if (a[i] > a[i+1]){
					int tmp = a[i];
					a[i] = a[i+1];
					a[i+1] = tmp;
					swapped = true;
				}
			}
			size--;
			
		} while(swapped);
	}

}











