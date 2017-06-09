package src;

import java.util.Random;

public class MergeSort {

	public static void main(String[] args) {
		int[] a = new int[20];
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			a[i] = rand.nextInt(30);
		}
		
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");
		mergeSort(a, 0, a.length - 1);
		System.out.println();
		for (int i = 0; i < 20; i++)
			System.out.print(a[i] + " ");

	}

	public static void mergeSort(int[] a, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			mergeSort(a, low, mid);
			mergeSort(a, mid + 1, high);		
			merge(a, low, mid, high );
		}
	}
	
	private static void merge(int[] a, int low, int mid, int high) {
		//size of 2 aux arrays
		int sizeArr1 = mid - low + 1;
		int sizeArr2 = high - mid;
		
		//create aux arrays
		int[] L = new int[sizeArr1];
		int[] R = new int[sizeArr2];
		
		//copy data to aux array
		for (int i = 0; i < sizeArr1; i++) {
			L[i] = a[low + i];
		}
		
		for (int i = 0; i < sizeArr2; i++) {
			R[i] = a[mid + 1 + i];
		}
		
		//merge them back
		int i = 0, j = 0, k = low;
		
		while ( i < sizeArr1 && j < sizeArr2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
			k++;
		}
		
		while ( i < sizeArr1) {
			a[k] = L[i];
			k++;
			i++;
		}
			
		
		while ( j < sizeArr2){
			a[k] = R[j];
			k++;
			j++;
		}
	}

}















