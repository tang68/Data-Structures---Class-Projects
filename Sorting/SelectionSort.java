package src;
import java.util.*;
public class SelectionSort {

	public static void main(String[] args) {
		int[] a = new int[20];
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			a[i] = rand.nextInt(30);
		}
		
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");
		selectionSort(a);
		System.out.println();
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");

	}

	public static void selectionSort(int[] a) {
		int size = a.length;
		for (int i = 0; i < size - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < size; j++){
				if (a[j] < a[minIndex])
					minIndex = j;
			}
			
			if (minIndex != i) {
				int tmp = a[minIndex];
				a[minIndex] = a[i];
				a[i] = tmp;
			}
		}
	}
}




