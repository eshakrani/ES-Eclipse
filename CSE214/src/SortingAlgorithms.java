import java.util.Scanner;
public class SortingAlgorithms {
	static Scanner stdin = new Scanner(System.in);
	static int[] arr = {4, 2, 9, 8, 5, 3, 10, 1, 7, 6};
	public static void main (String[] args) {
		
		
		
		while (true) {
			options();
		}
		
		
		
	}
	
	static void options() {
		System.out.println("0. End.");
		System.out.println("1. First Selection Sort");
		System.out.println("2. Second Selection Sort");
		System.out.println("3. Insertion Sort");
		System.out.println("4. Bubble Sort");
		System.out.println("5. Merge Sort");
		System.out.println("6. First Quick Sort");
		System.out.println("7. Second Quick Sort");
		System.out.println("8. Heap Sort");
		
		System.out.print("Enter option: ");
		int n = stdin.nextInt();
		switch(n) {
			case 1:
				int[] sel1 = arr.clone();
				System.out.println("\n\n\n\n\n\n");
				System.out.println("First Selection Sort:");
				System.out.println("Original: ");
				printArray(sel1);
				System.out.println();
				selectionSort(sel1);
				break;
			case 2:
				int[] sel2 = arr.clone();
				System.out.println("\n\n\n\n\n\n");
				System.out.println("Second Selection Sort: ");
				System.out.println("Original: ");
				printArray(sel2);
				System.out.println();
				selectionSort2(sel2);
				break;
			case 3:
				int[] ins = arr.clone();
				System.out.println("\n\n\n\n\n\n");
				System.out.println("Insertion Sort: ");
				System.out.println("Original: ");
				printArray(ins);
				System.out.println();
				insertionSort(ins);
				break;
			case 4:
				int[] bub = arr.clone();
				System.out.println("\n\n\n\n\n\n");
				System.out.println("Bubble Sort: ");
				System.out.println("Original: ");
				printArray(bub);
				System.out.println();
				bubbleSort(bub);
				break;
			case 5:
				int[] mer = arr.clone();
				System.out.println("\n\n\n\n\n\n");
				System.out.println("Merge Sort: ");
				System.out.println("Original: ");
				printArray(mer);
				System.out.println();
				mergeSort(mer, 0, mer.length - 1);
				printArray(mer);
				break;
			case 6:
				int[] qu = arr.clone();
				System.out.println("\n\n\n\n\n\n");
				System.out.println("First Quick Sort: ");
				System.out.println("Original: ");
				printArray(qu);
				System.out.println();
				quickSort(qu, 0, qu.length - 1);
				System.out.println();
				printArray(qu);
				break;
			case 7:
				int[] qu2 = arr.clone();
				System.out.println("\n\n\n\n\n\n");
				System.out.println("Second Quick Sort: ");
				System.out.println("Original: ");
				printArray(qu2);
				System.out.println();
				quickSort2(qu2, 0, qu2.length - 1);
				System.out.println();
				printArray(qu2);
				break;
			case 8:
				
				break;
			case 0: System.out.println("Done.");System.exit(1);
			default: break;
		}
	}
	
	/**
	 * takes the last element as pivot and places the pivot element
	 *  at its correct position in the sorted array
	 * places all elements smaller than the pivot to the left of the pivot
	 *  and all elements greater than the pivot to the right of the pivot
	 *  
	 * @param arr - int[]
	 * @param low - int
	 * @param high - int
	 * @return int - index of pivot element
	 */
	public static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		System.out.println("\nPivot: " + pivot);
		
		int i = (low - 1); // index of smaller element
		System.out.println("Index i: " + i);
		
		for (int j = low; j < high; j++) {
			
			// if current element is smaller than the pivot
			if (arr[j] < pivot) {
				
				System.out.println("\n" + arr[j] + " is less than " + pivot);
				
				i++;
				System.out.println("Increment i to " + i);
				
				// swap arr[i] and arr[j]
				swap(arr, i, j);
			}
			else {
				System.out.println("\n" + arr[j] + " is not less than " + 
						pivot);
				System.out.println("Do nothing.");
				System.out.println("i is still " + i);
			}
		}
		
		// swap arr[i + 1] and arr[high] (the pivot)
		swap(arr, i+1, high);
		
		System.out.println("\nArray after partitioning:");
		printArray(arr);
		
		return i + 1;
	}
	
	/**
	 * the main function for quickSort that implements the partition function
	 * 
	 * @param arr - int[]
	 * @param low - int
	 * @param high - int
	 */
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			
			System.out.println("\nStart of partitioning...");
			// pi is the partitioning index
			int pi = partition(arr, low, high);
			// the pivot element is now at the right place
			
			System.out.println("\nEnd of partitioning...");
			
			// sort the elements before the pivot and then after the pivot
			
			System.out.print("\nQuick Sort on elements before pivot:");
			for (int i = low; i <= pi - 1; i++) {
				System.out.print(arr[i] + " ");
			}
			quickSort(arr, low, pi - 1);
			
			System.out.print("\nQuick Sort on elements after pivot:");
			for (int i = pi + 1; i <= high; i++) {
				System.out.print(arr[i] + " ");
			}
			quickSort(arr, pi + 1, high);
		}
	}
	
	/**
	 * choose a pivot element of the array and partition the array so that
	 *  the elements to the left of the pivot are less than the pivot and
	 *  the elements to the right of the pivot are greater than the pivot
	 * sort the subarray to the left of the pivot and the subarray to the
	 *  right of the pivot recursively using quicksort
	 * 
	 * Quick Sort typically runs in O(n * log(n)) time
	 * Worst Case: O(n ^ 2)
	 * 
	 * @param data - int[]
	 * @param first - int
	 * @param last - int
	 */
	public static void quickSort2(int[] data, int first, int last) {
		int left, right, pivot;
		if (first >= last) return;
		left = first;
		right = last;
		pivot = data[ (first + last) / 2];
		
		System.out.println("\nLeft: " + data[left]);
		System.out.println("Right: " + data[right]);
		System.out.println("Pivot: " + data[pivot] + "\n");
		
		do {
			while (data[left] < pivot) {
				System.out.println(data[left] + " is less than " 
						+ data[pivot] + " so increment 'left'");
				left++;
				System.out.println("New 'left': " + data[left]);
				
			}
			while (data[right] > pivot) {
				System.out.println(data[right] + " is greater than " 
						+ data[pivot] + " so decrement 'right'");
				right--;
				System.out.println("New 'right': " + data[right]);
			}
			if (left <= right) {
				swap(data, left++, right--);
			}
		} while (left <= right);
		
		printArray(data);
		
		System.out.println("Now do quick sort from " + data[first] + 
				" to " + data[right]);
		quickSort2(data, first, right);

		System.out.println("Now do quick sort from " + data[left] + 
				" to " + data[last]);
		quickSort2(data, left, last);
	}
	
	
	/**
	 * Merge Sort:
	 * Run time analysis
	 * Worst Case = Average Case = Best Case = O(n * log(n))
	 * 
	 * repeated splits the array into 'n' arrays that each hold 
	 *  one element
	 * rebuilds the array by combining these arrays in order
	 * 
	 * @param arr - int[]
	 * @param l - int - left bound of subarray 
	 * @param r - int - right bound of subarray
	 */
	public static void mergeSort(int[] arr, int l, int r) {
		if (l < r) {
			// find the middle point
			int m = (l + r) / 2;
			
			// sort first and second halves
			mergeSort(arr, l, m);
		
			mergeSort(arr, m + 1, r);
			
			// merge the sorted halves
			merge(arr, l, m, r);
		}
	}
	
	/*
	 * merges two subarrays of arr[]
	 * first subarray is arr[l...m]
	 * second subarray is arr[m+1...r]
	 */
	public static void merge(int arr[], int l, int m, int r) {
		
		System.out.println("\nMerging two subarrays: ");
		System.out.println("Subarray #1: ");
		for (int i = l; i <= m; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		System.out.println("Subarray #2: ");
		for (int i = m + 1; i <= r; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		// find sizes of the two subarrays to be merged
		int size1 = m - l + 1;
		int size2 = r - m;
		
		// create temp arrays
		int[] ltemp = new int[size1];
		int[] rtemp = new int[size2];
		
		// copy data to temp arrays
		for (int i = 0; i < size1; i++) {
			ltemp[i] = arr[l + i];
		}
		for (int j = 0; j < size2; j++) {
			rtemp[j] = arr[m + 1 + j];
		}
		
		// merge the temp arrays
		
		// initial indices of first and second subarrays
		int i = 0; 
		int j = 0;
		
		// initial index of merged subarray array
		int k = l;
		while (i < size1 && j < size2) {
			if (ltemp[i] <= rtemp[j]) {
				arr[k] = ltemp[i];
				i++;
			}
			else {
				arr[k] = rtemp[j];
				j++;
			}
			k++;
		}
		
		// copy remaining elements of ltemp if any
		while (i < size1) {
			arr[k] = ltemp[i];
			i++;
			k++;
		}
		
		// copy remaining elements of rtemp if any
		while (j < size2) {
			arr[k] = rtemp[j];
			j++;
			k++;
		}
	}
	
	/**
	 * Selection Sort:
	 * Run time analysis
	 * Worst Case = Average Case = Best Case = O(n^2)
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
			
			System.out.println("\nElement #" + (i + 1) + "\n");
			
			// j will always start at the elements following the 
			// already sorted ones so they remain unaffected 
			// after getting sorted properly
			
			// traverse through remaining portion of array after base position
			for (j = i + 1; j <= n - 1; j++) {
				
				System.out.println("Comparing " + data[i] + " and " + data[j]);
				
				// if element at current position is less than the current
				// base element (@ pos. i), swap the two elements
				if (data[j] < data[i]) {
					System.out.println(data[j] + " is less than " + data[i]);
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
