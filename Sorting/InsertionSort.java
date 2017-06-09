package src;

import java.util.Random;

public class InsertionSort {

	public static void main(String[] args) {
		int[] a = new int[20];
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			a[i] = rand.nextInt(30);
		}
		
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");
		insertionSort(a);
		System.out.println();
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");
	}
	
	public static void insertionSort(int[] a) {
		
		for (int i = 1; i < a.length; i++) {
			int val = a[i];
			int j = i -1;
			
			while ( j >= 0 && a[j] > val) {
				a[j+1] = a[j];
				j--;
			}
			
			a[j+1] = val;
		}
	}

}
