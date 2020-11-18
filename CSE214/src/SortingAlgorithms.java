
public class SortingAlgorithms {
	
	public static void main (String[] args) {
		int[] sel1 = {4, 2, 9, 8, 5, 3, 10, 1, 7, 6};
		int[] sel2 = sel1.clone();
		int[] ins = sel1.clone();
		int[] bub = sel1.clone();
		
		System.out.println("First Selection Sort:");
		System.out.println("Original: ");
		printArray(sel1);
		System.out.println();
		selectionSort(sel1);
		
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Second Selection Sort: ");
		System.out.println("Original: ");
		printArray(sel2);
		System.out.println();
		selectionSort2(sel2);
		
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Insertion Sort: ");
		System.out.println("Original: ");
		printArray(ins);
		System.out.println();
		insertionSort(ins);
		
		System.out.println("\n\n\n\n\n\n");
		System.out.println("Bubble Sort: ");
		System.out.println("Original: ");
		printArray(bub);
		System.out.println();
		bubbleSort(bub);
	}
	
	/**
	 * Selection Sort:
	 * Run time analysis
	 * Worst Case = Average Cast = Best Case = O(n^2)
	 * 
	 * finds the minimum element from the rest of the array
	 *  and puts it in its corresponding index (minLocation)
	 *  via swapping
	 *  
	 * minLocation gradually progresses through the array
	 * 
	 * @param data - 1D int array
	 */
	public static void selectionSort(int[] data) {
		int i;
		int j;
		int minLocation;
		int n = data.length;
		
		for (i = 0; i <= n - 2; i++) {
			
			// minLocation is 0, 1, 2...second to last element
			// set to current base position (i) with each pass of the loop
			minLocation = i;
			
			
			// j will always start at the elements following the 
			// already sorted ones so they remain unaffected 
			// after getting sorted properly
			
			// traverse through remaining portion of array after base position
			for (j = i + 1; j <= n - 1; j++) {
				
				System.out.println("Comparing " + data[i] + " and " + data[j]);
				
				// if element at current position is less than 
				// minLocation, change minLocation to current position
				//
				// goes through the rest of the array until the 
				// absolute minimum element is found along with its position
				if (data[j] < data[minLocation]) {
					
					
					System.out.println(data[j] + " is less than " + 
							data[minLocation] + " so minLocation "
									+ "is now set to index " + j);
					
					minLocation = j;
				}
				// at this point minLocation holds the index of the 
				// minimum element of the remaining portion of the array
			}
			
			// swap the element at minLocation with the current base element
			swap(data, minLocation, i);
			
			printArray(data);
//			System.out.println();
		}
	}
	
	/**
	 * takes the first element in the array and compares it
	 *  with all the remaining elements in the array -
	 *  swaps the elements if the element found is smaller
	 * 
	 * continues to perform comparisons and swaps until 
	 *  the end of the array is reached
	 *  
	 * repeat with second element, third, etc. 
	 * 
	 * @param data - 1D int array
	 */
	public static void selectionSort2(int[] data) {
		int i;
		int j;
		int n = data.length;
		
		for (i = 0; i <= n - 2; i++) {
			
			// j will always start at the elements following the 
			// already sorted ones so they remain unaffected 
			// after getting sorted properly
			
			// traverse through remaining portion of array after base position
			for (j = i + 1; j <= n - 1; j++) {
				
				System.out.println("Comparing " + data[i] + " and " + data[j]);
				
				// if element at current position is less than the current
				// base element (@ pos. i), swap the two elements
				if (data[j] < data[i]) {
					swap(data, i, j);
					
					
					printArray(data);
//					System.out.println();
					
				}
				
				
			}
//			printArray(data);
//			System.out.println();
		}
	}
	
	/**
	 * Insertion Sort:
	 * Run Time Analysis:
	 * Worst Case = Average Case = O(n^2)
	 * Best Case = O(n) - if the array is already sorted
	 * 
	 * Starts at second element of array 
	 * Checks if less than element before it
	 * If less - continuously shift up elements until correct spot is found
	 *  and place the element there
	 *  
	 * Continue for rest of the elements
	 * 
	 * @param data - 1D int array
	 */
	public static void insertionSort(int[] data) {
		int i;
		int j;
		int item;
		int n = data.length;
		for (i = 1; i <= n - 1; i++) {
			
			System.out.println("Item set to " + data[i]);
			item = data[i];
			j = i;
			while (j > 0 && data[j - 1] > item) {
				
				/////////
				System.out.println(item + " is less than " + data[j - 1]);
				System.out.println("Shifting " + data[j - 1] + " up.");
				////////
				
				data[j] = data[j - 1];
				j--;
				
				//////////
//				printArray(data);
				/////////
			}
			
			System.out.println("Nothing to shift.");
			
			data[j] = item;
			
			printArray(data);
//			System.out.println();
		}
	}
	
	/**
	 * Bubble Sort:
	 * 
	 * Repeatedly compares adjacent elements - swaps 
	 *  if second element is less than the first
	 *  
	 * With each pass of the loop, the minimum element of the 
	 *  remaining portion of the array has been moved to its
	 *  proper position at the leftmost part of the array
	 *  
	 * @param data - 1D int array
	 */
	public static void bubbleSort(int[] data) {
		int i;
		int j;
		int n = data.length;
		
		for (i = 0; i <= n - 2; i++) {
			
			// start from end of array and work backwards until
			// you reach the current element (@ pos. i)
			for (j = n - 1; j > i; j--) {
				
				System.out.println("Comparing " + data[j] + 
						" and " + data[j - 1]);
				
				// check if current element is less than the element
				// directly before it
				if (data[j] < data[j - 1]) {
					
					////////
					System.out.println(data[j] + " is less than " +data[j - 1]);
					////////////
					
					
					swap(data, j, j - 1);
					
					///////////
					printArray(data);
					//////////
				}
			}
		}
		System.out.println();
		printArray(data);
	}
	
	/**
	 * swaps elements at the indices indicated by param i and param j
	 *  of the array param arr
	 * @param arr - 1D int array
	 * @param i - index of first element to be swapped
	 * @param j	- index of second element to be swapped
	 */
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		System.out.println("Swap " + arr[i] + " and " + arr[j]);
	}
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n");
	}
}
