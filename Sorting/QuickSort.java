package src;

import java.util.Random;

public class QuickSort {

	public static void main(String[] args) {
		int[] a = new int[20];
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			a[i] = rand.nextInt(30);
		}
		
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");
		quickSort(a, 0, a.length - 1);
		System.out.println();
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");

	}

	public static void quickSort(int[] a, int start, int end) {
		if (start < end) {
			int pIndex = partition(a, start, end);
			quickSort(a, start, pIndex - 1);
			quickSort(a, pIndex + 1, end);
		}
		
	}
	
	private static int partition(int[] a, int start, int end) {
		
		int pivot = a[end];
		int pIndex = start;
		
		for (int i = start; i < end ; i++) {
			if (a[i] <= pivot) {
				int tmp = a[i];
				a[i] = a[pIndex];
				a[pIndex] = tmp;
				pIndex++;
			}
		}
		int temp = a[pIndex];
		a[pIndex] = a[end];
		a[end] = temp;
		
		return pIndex;
	}

}
