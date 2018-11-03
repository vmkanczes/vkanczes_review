package main.java;

/** 
 * A collection of various sorting algorithms for sorting an array
 * of items with int key
 */

public final class SortAlgos {

        /**
         * Sorts with bubblesort algorithm
         * 
         * Returns a NullPointerException when the array is empty
         * Returns a sorted array with the same length as the param array numberArray
         * 
         * @param numberArray the array to be sorted
	     * @exception NullPointerException if <code>numberArray</code> 
	     * is not initialized
         */
        public static void bubbleSort(Item[] numberArray) 
                            throws NullPointerException {
                if (numberArray == null) throw new NullPointerException();
        
		        int n = numberArray.length-1;
		        Item temp;
                int bottom;       // bottom for each pass        
                for (bottom = 1; bottom < n; bottom++)  {
                        for (int i = n-1; i >= bottom; i--) {
                                if (numberArray[i-1].key > numberArray[i].key) {
                                        temp = numberArray[i-1];
                                        numberArray[i-1] = numberArray[i];
                                        numberArray[i] = temp;
                                }
                        }
                }
        }
    
        /**
         * Sorts with selectionsort algorithm
         * @param numberArray the array to be sorted
         * @exception NullPointerException if <code>numberArray</code> 
         * is not initialized
         */
        public static void selectionSort(Item numberArray[]) 
                            throws NullPointerException {
                if (numberArray == null) throw new NullPointerException();
                
                int minIndx;            // Index of smallest key in each pass
                int bottom;             // bottom for each pass
                int i;
                Item temp;
                int n = numberArray.length;
            
                for (bottom = 0; bottom < n-1; bottom++) {
                             //  INVARIANT (prior to test):
                             //  All numberArray[bottom+1..n-1] are >= numberArray[bottom]
                             //  && numberArray[0..bottom] are in ascending order
                             //  && bottom >= 0
                        minIndx = bottom;
                        for (i = bottom+1; i < n; i++) {
                                    // INVARIANT (prior to test):
                                    // numberArray[minIndx] <= all
                                    // numberArray[0..i-1]
                                    // && i >= bottom+1
                                if (numberArray[i].key < numberArray[minIndx].key) { 
                                        minIndx = i; 
                                }
                        }
                        temp = numberArray[bottom];
                        numberArray[bottom] = numberArray[minIndx];
                        numberArray[minIndx] = temp; 
                }
        }
    
        /**
         * Sorts with insertionsort algorithm
         * @param numberArray the array to be sorted
         * @exception NullPointerException if <code>numberArray</code> 
         * is not initialized
         */
        public static void insertionSort(Item numberArray[]) 
                        throws NullPointerException {
                if (numberArray == null) throw new NullPointerException();
                int n = numberArray.length;
                int currentPos, insPos; 
                for (currentPos = 1; currentPos < n; currentPos++) {
                        insPos = findInsPosition(numberArray, currentPos - 1, 
                                        numberArray[currentPos].key);
                        insertAtPosition(numberArray, insPos, currentPos);
                }   
        }
        
        /**
         * Finds insertion position with binary search
         * @param numberArray the array to be sorted
         * @param range upper bound for insertion position
         * @param x the value determining the position 
         * @return the insertion position 
         */
        private static int findInsPosition(Item[] numberArray, int range, int x) {
                int index;   // variable to hold the position
                int i,j,k;
                i = 0; j = range; // initialize lower index i and upper index j
                do { 
                        k = (i + j) / 2;  // choose k halfway between i and j
                        if (x >= numberArray[k].key) { 
                                i = k + 1;   // update lower index i
                        } else {                 
                                j = k - 1;   // update upper index j
                        }
                } while (i <= j);
                if (x >= numberArray[k].key) {
                        index = k + 1;
                } else  {                   
                        index = k;
                }
                return index;
        }
        
        /**
         * Inserts array component into a sorted range below the component
         * such that the result is again sorted
         * @param numberArray the array in which this happens
         * @param insPos the insertion position
         * @param fromPos the position whose value 
         * has to be inserted at <code>insPos</code>
         */
        private static void insertAtPosition(Item[] numberArray, int insPos, 
                                                         int fromPos ) {
                if (insPos == fromPos) return;
                Item temp = numberArray[fromPos];
                for (int i = fromPos; i > insPos; i--) numberArray[i] = numberArray[i-1];
                numberArray[insPos] = temp;
        }
    
    
        /**
         * Sorts with mergesort algorithm
         * @param numberArray the array to be sorted
         * @exception NullPointerException if <code>numberArray</code> 
         * is not initialized
         */
        public static void mergeSort(Item numberArray[]) 
                                throws NullPointerException {
                if (numberArray == null) throw new NullPointerException();
                mergeSort(numberArray, 0, numberArray.length - 1);
        }
        
        
        /**
         * merges two sorted adjacent ranges of an array
         * @param numberArray the array in which this happens
         * @param left start of the first range
         * @param middle end of the first range
         * @param right end of the second range
         */
        private static void merge(Item[] numberArray, int left, int middle, int right) {
                int i, j;
                int m = middle - left + 1; // length of first array region
                int n = right - middle;    // length of second array region
            
                // make copies of array regions to be merged 
                // (only the references to the items)
                Item[] copy1 = new Item[m];
                Item[] copy2 = new Item[n];
                for (i = 0; i < m; i++) copy1[i] = numberArray[left + i]; 
                for (j = 0; j < n; j++) copy2[j] = numberArray[middle + 1 + j]; 
                    
                i = 0; j = 0;   
                // merge copy1 and copy2 into  numberArray[left...right]
                while (i < m && j < n) {
                        if (copy1[i].key < copy2[j].key) {
                                numberArray[left+i+j] = copy1[i];
                                i++;
                        } else {
                                numberArray[left+i+j] = copy2[j];
                                j++;
                        }//endif
                }//endwhile
                if (j == n) { // second array region is completely handled, 
                              // so copy rest of first region
                        while (i < m) {
                                numberArray[left+i+j] = copy1[i];
                                i++;
                        }
                }
                // if (i == m) do nothing, 
                // rest of second region is already in place
        }
        
        /**
         * sorts array by mergesort in a certain range
         * @param numberArray the array in which this happens
         * @param first start of the range
         * @param last end of the range
         */
        private static void mergeSort(Item[] numberArray, int first, int last) {
                if (first == last) return;	
                // devide numberArray into 2 equal parts
                int middle = (first + last) / 2; 
                mergeSort(numberArray, first, middle);   // sort the first part
                mergeSort(numberArray, middle+1, last);  // sort the second part
                merge(numberArray, first, middle, last); // merge the 2 sorted parts
        }
    
    
        /**
         * Sorts with quicksort algorithm
         * @param numberArray the array to be sorted
         * @exception NullPointerException if <code>numberArray</code> 
         * is not initialized
         */
        public static void quickSort(Item[] numberArray) 
                                throws NullPointerException {
                if (numberArray == null) throw new NullPointerException();
                quickSort(numberArray, 0, numberArray.length - 1);
        }
        
        /**
         * sorts array by quicksort in a certain range
         * @param numberArray the array in which this happens
         * @param loBound start of the range
         * @param hiBound end of the range
         */
        private static void quickSort(Item[] numberArray, int loBound, int hiBound) {
                int loSwap, hiSwap;
                int pivotKey, pivotIndex;
                Item temp, pivotItem;
        
                if (hiBound - loBound == 1) {         // Two items to sort
                        if (numberArray[loBound].key > numberArray[hiBound].key) {
                                temp = numberArray[loBound];
                                numberArray[loBound] = numberArray[hiBound];
                                numberArray[hiBound] = temp;
                        }
                        return;
                }
                pivotIndex = (loBound + hiBound) / 2; // 3 or more items to sort
                pivotItem = numberArray[pivotIndex];       
                numberArray[pivotIndex] = numberArray[loBound];
                numberArray[loBound] = pivotItem;    
                pivotKey = pivotItem.key; 
                loSwap = loBound + 1;
                hiSwap = hiBound;
                do {
                        while (loSwap <= hiSwap && numberArray[loSwap].key <= pivotKey)
                            // INVARIANT (prior to test):
                            // All numberArray[loBound+1..loSwap-1]
                            // are <= pivot  &&  loSwap <= hiSwap+1
                                loSwap++;
                        while (numberArray[hiSwap].key > pivotKey)
                            // INVARIANT (prior to test):
                            //    All numberArray[hiSwap+1..hiBound]
                            //    are > pivot  &&  hiSwap >= loSwap-1
                                hiSwap--;
                        if (loSwap < hiSwap) {
                                temp = numberArray[loSwap];
                                numberArray[loSwap] = numberArray[hiSwap];
                                numberArray[hiSwap] = temp;
                                loSwap++;
                                hiSwap--;
                        }
                        // INVARIANT: All numberArray[loBound..loSwap-1] are <= pivot
                        // && All numberArray[hiSwap+1..hiBound] are > pivot
                        // && (loSwap < hiSwap) --> 
                        //                 numberArray[loSwap] <= pivot < numberArray[hiSwap]
                        // && (loSwap >= hiSwap) --> numberArray[hiSwap] <= pivot
                        // && loBound <= loSwap <= hiSwap+1 <= hiBound+1
                } while (loSwap <= hiSwap);
                numberArray[loBound] = numberArray[hiSwap];
                numberArray[hiSwap] = pivotItem;
        
                if (loBound < hiSwap-1)     // 2 or more items in 1st subnumberArray
                        quickSort(numberArray, loBound, hiSwap-1);
        
                if (hiSwap+1 < hiBound)     // 2 or more items in 2nd subnumberArray
                        quickSort(numberArray, hiSwap+1, hiBound);
        }
    
    
        /**
         * establishes heap property in a certain range
         * @param numberArray the array in which this happens
         * @param top start of the range
         * @param bottom end of the range
         */
        private static void heapify(Item[] numberArray, int top, int bottom) {
                Item temp;
                int child;
            
                if (2*top+1 > bottom) return; // nothing to do 
            
                if (2*top+2 > bottom) { 
                        // numberArray[2*top+1] is only child of numberArray[top]
                        child = 2*top+1;
                } else {                  // 2 sons, determine bigger one
                        if (numberArray[2*top+1].key > numberArray[2*top+2].key) {
                                child = 2*top+1;
                        } else {
                                child = 2*top+2;
                        }
                }//endif
            
                // check if exchange is necessary
                if (numberArray[top].key < numberArray[child].key) {
                        temp = numberArray[top]; 
                        numberArray[top] = numberArray[child]; 
                        numberArray[child] = temp;
                        // recursive call for possible further exchanges
                        heapify(numberArray, child, bottom); 
                }//endif
        }
    
        /**
         * turns array into a heap
         * @param numberArray the array to which this happens
         */
        private static void createHeap(Item[] numberArray) {
                for (int i = numberArray.length/2 - 1; i >= 0; i--) {
                        heapify(numberArray, i, numberArray.length - 1);
                }
        }
    
        /**
         * sorts array by heapsort in a certain range
         * @param numberArray the array in which this happens
         */
        public static void heapSort(Item[] numberArray) 
                                throws NullPointerException {
                if (numberArray == null) throw new NullPointerException();
    
                Item temp;
                int last;
                int n = numberArray.length;
            
                createHeap(numberArray);
                for (last = n-1; last > 0; last--) {
                        // exchange top component with 
                        // current last component of numberArray
                        temp = numberArray[0]; 
                        numberArray[0] = numberArray[last]; 
                        numberArray[last] = temp;
                        // call Heapify to to reestablish heap property
                        heapify(numberArray, 0, last-1);
                }//endfor
        }


}