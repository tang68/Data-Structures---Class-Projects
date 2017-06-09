package maxHeap;

public class MaxHeap<T extends Comparable<T>> {
		private static int DEFAULT_CAPACITY = 20;
		private T[] data;
		private int lastIndex;
		private int sequentialSwap;
		private int optimalSwap;
		
		public MaxHeap(){
			this(DEFAULT_CAPACITY);
		}
		
		
		@SuppressWarnings("unchecked")
		public MaxHeap(int capacity) {
			T[] tmp =  (T[]) new Comparable[capacity + 1];	
			data = tmp;
			lastIndex = 0;
			sequentialSwap = 0;
			optimalSwap = 0;
		}
		
		public void addSequential (T newEntry) {
			
			if (lastIndex == data.length - 1)
				increaseCapacity();
			lastIndex++;
			data[lastIndex] = newEntry;
			int childIndex = lastIndex;
			
			while(childIndex > 1)
			{
				int parentIndex = childIndex/2;
				if (data[childIndex].compareTo(data[parentIndex]) > 0) {
					T temp = data[childIndex];
					data[childIndex] = data[parentIndex];
					data[parentIndex] = temp;
					sequentialSwap++;
				}
				childIndex = childIndex/2;
			}
			
		}
		
		public void addOptimal(T[] entries) {
			for (int i = 0; i < entries.length; i++) {
				data[i + 1] = entries[i];
			}
			lastIndex = entries.length ;
			for (int rootIndex = lastIndex/2; rootIndex > 0; rootIndex--) {
				reheap(rootIndex);
			}	
			
		}
		

		public int countSwapSequential() {
			return sequentialSwap;
		}
		
		public int countSwapOptimal() {
			return optimalSwap;
		}
		
		private void increaseCapacity(){
			@SuppressWarnings("unchecked")
			T[] data2 =  (T[])new Comparable[data.length * 2];
			for (int i = 0; i < data.length; i++){
				data2[i] = data[i];
			}
			data = data2;
		}
		
		private void reheap2(int rootIndex) {			
			int parentIndex = rootIndex;
			int childIndexLeft = parentIndex * 2;
			int childIndexRight = parentIndex * 2 + 1;
			while (childIndexRight <= lastIndex && childIndexLeft <= lastIndex) {
				if (data[childIndexLeft].compareTo(data[childIndexRight]) > 0){
					T tmp = data[childIndexLeft];
					data[childIndexLeft] = data[parentIndex];
					data[parentIndex] = tmp;
					parentIndex = childIndexLeft;
					optimalSwap++;
				} else {
					T tmp = data[childIndexRight];
					data[childIndexRight] = data[parentIndex];
					data[parentIndex] = tmp;
					parentIndex = childIndexRight;
					optimalSwap++;
				}
				childIndexLeft = parentIndex * 2;
				childIndexRight = parentIndex * 2 + 1;	
			}
		
		}
		
		private void reheap(int rootIndex) {
			boolean done = false;
			T value = data[rootIndex];
			int leftIndex = 2 * rootIndex;
			while (!done && (leftIndex <= lastIndex)) {
				int largerChildIndex = leftIndex;
				int right = leftIndex +1;
				if ((right <= lastIndex) && (data[right].compareTo(data[largerChildIndex]) > 0)) {
					largerChildIndex = right;
				}
				
				if (value.compareTo(data[largerChildIndex]) < 0) {
					data[rootIndex] = data[largerChildIndex];
					rootIndex = largerChildIndex;
					leftIndex = 2 * rootIndex;			
					optimalSwap++;
				}
				else
					done = true;
			}
		
			data[rootIndex] = value;
		}
		
		public T removeMax() {
			if (isEmpty()) {
				throw new RuntimeException("No Entries in the heap");
			}
			T root = data[1];
			data[1] = data[lastIndex];
			lastIndex--;
			reheap2(1);	
			return root;
		}
		
		public T getMax() {
			if (lastIndex == 0)
				throw new RuntimeException("No max. Empty Heap");
			return data[1];
		}
		
		public boolean isEmpty() {
			return lastIndex < 0;
		}

		public int getSize() {
			return lastIndex;
		}

		public void clear() {
			lastIndex = 0;
		}
		public String toString(){
			String res = "";
			for (int i = 1; i <= lastIndex; i++)
			{
				res += data[i] + " ";
			}
			
			return res;
		}
		
		public String firstTen() {
			String res = "";
			for (int i = 1; i <= 10; i++)
			{
				System.out.print(data[i] + " ");
			}
			System.out.println("....");
			return res;
		}

}



















