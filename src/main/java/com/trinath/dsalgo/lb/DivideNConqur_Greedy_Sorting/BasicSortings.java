package com.trinath.dsalgo.lb.DivideNConqur_Greedy_Sorting;

public class BasicSortings {
    // Select an index from index 0 till length-1 and get the right element for the selected index; bring the smalest element to index zero by comparing other elements and move to the next index.
    public static int[] selectionSort(int[] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++)
            if(arr[i]>arr[j]){
                swap(i, j, arr); // dont swap always , but save in a temp variable compare and swap once at last; then you have to memorize
            }
        }
        return arr;
    }
    //Bubble up the largest element to end of the arry in first pass, the 2nd largest in second pass and so on.. by compare adjacent element in every iteratiion. Leave the sorted elements in subsequent iteration
    public static int[] bubbleSort(int[] arr){
        for(int i=0; i<arr.length; i++){
            int j=0;
            while(j<arr.length-i-1){
                if(arr[j]>arr[j+1]){
                    swap(j, j+1,arr);
                }
                j++;
            }
        }
        return arr;
    }
    // This insert the correct element in the correct position(index), so scan from an index(left to it already sorted) to zero and insert in right place(when a bigger element is found) when found
    public static int[] insertionSort(int[] arr){
        for(int i=1; i<arr.length; i++){
            int j=i-1;
            int elem = arr[i];
            while(j>0 && elem<arr[j]){ // Smaller to left and left of index i is already sorted
                    //swap(j,j-1,arr); // Dont need swap Just shift when condition met and insert at last
                    arr[j+1] = arr[j]; // arr[j] can be inserted at any time when found suitable https://www.youtube.com/watch?v=i-SKeOcBwko
                j--;
            }
                arr[j+1] = elem; // inserted at j+1 as j= i-1
        }
        return arr;
    }

    /**
     * Heap is a min or max heap depending on the root element. If we use min heap and remove the root from the heap every iteration then we have it sorted in Ascending order
     * We dont need to remove and put in a new array, instead we can use the last element of same array and declare it sorted. In that case we have to consider max heap for ascending.
     * An array representation of heap is arr[0] = root, children of a[i] --- left child--> a[2i+1]  and a[i]--right chiild-->a[2i+2] start from i=0; also from child to parent parent of a[i] is a[(i-1)/2]
     * To make first max heap we need to heapify all element ( last level elements can be excluded as there is no childer to compare for last level) so start with size/2- 1 elements to 0th
     * When remove an element then heapify( swim up - put last element to top/0th) and sink down(swap with smallest element from childeren with parent)
     * Mean Heap properties -
     * 1.parennt is smaller than childeren
     * 2.
     *
     * @param arr - input
     * @return sorted array
     */
    public static int[] heapSort(int[] arr){
    int size = arr.length;
    //Heapify each element from second lowest level
        for(int i=size/2 -1;i>=0;i-- ){
            heapifyDown(i,size,arr);//instead of size-1 as to take care of array size < 3
        }
      //Take out and swap
      for(int i=0; i<size-1; i++){
            swap(0,size-i-1, arr);//Take top out and swap with last element of unsorted portion  so, new_size = size -i "This is swim up"
          heapifyDown(0,size-i-1,arr); // This is sink down
      }
        return arr;
    }

    /**
     * This is heapify down, after swapping last elemet to top/ index 0 for removing top  or just making an heap
     * Note:: To add an element , we should add the element at the last and then heapiify up to the root to keep th disturbed heap as heap after addiition, check HeapOerations.java class
     * @param i  start of point
     * @param n - end here
     * @param arr array
     * @return
     */
    public static void heapifyDown(int i,int n,int[] arr){
        //Base case nothing
        int smallest = i;// assume parent is largest
        int l= 2*i+1;
        int r= 2*i+2;

            if(l<n && arr[l]<arr[smallest]){
                smallest = l;
            }
            if(r<n && arr[r]<arr[smallest]){ // this is not else if as always compare with parent and other child what is smallest;
                smallest = r;
            }
            if(smallest!=i){ // stops here, base condition
                swap(i,smallest,arr);
                heapifyDown(smallest,n,arr);
            }

    }
    private static int[] quickSort(int arr[]){
        return quickSortHelper(0, arr.length-1, arr);
    }

    private static int[] quickSortHelper(int start, int end, int[] arr){
        if(end<=start) {
            return arr;
        }
        int pivot = partition(start, end,arr);
        quickSortHelper(start, pivot-1,arr);
        quickSortHelper(pivot+1,end,arr);
        return arr;
    }

    /**
     * Take a pivot(may be a[0]).Goal is to partition across pivot, left to pivot will have smaller numbers than pivot. And right to pivot will have greater numbers.
     * Algo - Until end < start , start comparing  a[start] with pivot, if greater than pivot, then stop incrementing. Similarly from opposite side a[end] to comparee with pivot until find a smaller than pivot. Then swap. Keep doing until loop exit. Then replace end index value with pivot.
     * @param start - start index
     * @param end - end index
     * @param arr
     * @return partitioned array's  pivot in it's correct position
     */
    private static int partition(int start, int end, int[] arr){
        int pivot = start; // A randomized pivot will divide the array by 2 in average case
        //start++;
        while(end>=start){ // As pivot is start and condition starts with start not start++, have to make >=
            if(arr[start]>arr[pivot] && arr[end]<arr[pivot]){
                swap(start,end, arr); // Swap once when both pointer can't proceed any more
            }
            else {//Dont do anything if pivot is in correct position
                if (arr[start] <= arr[pivot]) { // We need to do <= as pivot is start; if we do start++ in the beginning then we can have strict lesser condition
                    start++;
                }
                if (arr[end] >= arr[pivot]) {
                    end--;
                }
            }

        }
        swap(end,pivot,arr);// finish the partition
        return end;
    }

    public static void mergeSort(int[] arr){
         mergeSortHelper( arr, 0, arr.length-1);
    }

    private static void mergeSortHelper(int[] arr, int low, int high){
        if(low<high) {
            int mid = low + (high - low) / 2;
            mergeSortHelper(arr, low, mid);// this will finish first recursively for the first half
            mergeSortHelper(arr, mid + 1, high);
            merge1(arr, low, mid, high);

        }
        //return  arr;
    }
    /**
     * There is a small error in the below function , find out?? merge1() is correct
     *Merge two logical subarray a[low] - a[mid] & a[mid+1] a[high] use temporary memory and copy over
     * @param a
     * @param low
     * @param mid
     * @param high
     * @return
     */
    public static int[] merge(int[] a, int low, int mid, int high){
        int[] temp = new int[a.length];
        //int[] arr2 =  copyArrayElements(a,mid+1, high);
        //compare and merge, use two pointer ofr two different array
        int i=low,j=mid+1, k=0;
        while(i<=mid || j<=high){
            if(i<mid && j<high) {
                if (a[i] < a[j]) {
                    temp[k] = a[i];
                    i++;
                } else {
                    temp[k] = a[j];
                    j++;
                }
            }
            else if(i<=mid){
                temp[k] = a[i];
                i++;
            }
            else if(j<=high){
                temp[k] = a[j];
                j++;
            }
            k++;
        }
// Copy
        k=0;
        while(low<=high)
        {
            a[low]=temp[k];
            k++;
            low++;
        }
        return a;

    }

    private static void merge1(int[] arr,int low, int middle, int high) {
        int i=low; // initial index of 1st part of array
        int j=middle+1; // initial index of 2nd part of array

        int k=0;  // index for auxillay array

        int temp[]=new int[arr.length]; //auxillay Array

        // Copying the smalller of the two elements int the two parts
        while(i<=middle && j<=high)
        {
            if(arr[i] < arr[j])
            {
                temp[k]=arr[i];
                i++;
            }
            else
            {
                temp[k]=arr[j];
                j++;
            }
            k++;
        }

        // simply 1st part of array copying if 2nd part gets exausted
        while(i<=middle)
        {
            temp[k]=arr[i];
            i++;
            k++;
        }

        // simply 2nd part of array copying if 1st part gets exausted
        while(j<=high)
        {
            temp[k]=arr[j];
            j++;
            k++;
        }

        // copying content from auxillay array to the original array
        k=0;
        while(low<=high)
        {
            arr[low]=temp[k];
            k++;
            low++;
        }
    }
    private static int[] copyArrayElements(int[] arr, int start, int end){//inclusive
        int[] copy = new int[end-start+1];
        for(int i=start; i<=end; i++){// index
            copy[i-start]= arr[i];
        }
        return copy;
    }

    //Helper methods
    public static void main(String arr[]){
        int a[]= { 1, 3, 6, 2, 7};
        int b[]= { 1, 3, 6, 2, 7};
        int c[]= { 1, 3, 6, 2, 7};
        int d[]= { 15, 2,4, 3, 6, 2, 7};
        int e[]= { 5,16, 2,4, 3, 6, 4, 7};
        int e1[]= { 1, 5,16, 2,4,-3, 27, 84,3, 6, 4, 7, 0};
        int e2[]= { 1, 5,16, 2,4,-3, 27, 84,3, 6, 4, 7, 0};
        int e3[]= { 1, 5,16, 23,4,13, 27, 84};
        int e4[]= { 1, 5,16, 23,4,13, 27, 84};

        //printArray(selectionSort(a));
        //printArray(bubbleSort(b));
        //printArray(insertionSort(c));
        //printArray(heapSort(d));
        //printArray(quickSort(e));
        //printArray(quickSort(e1));
        //mergeSort(e2);
        //printArray(e2);
        printArray(merge(e3,0,3,7));
        merge1(e4,0,3,7);
        printArray(e4);

    }


    public static void swap(int i, int j, int[] arr){
        if(i!=j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }

    static void printArray(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
        System.out.println("Done..");
    }

}
