package com.trinath.dsalgo.lb;

import java.util.*;


public class SubArraySumKProblems {

    /**
     * This method finds the smallest SubArray sum AT lEAST K( i. e; sum>=K.. that's not same as ==K) using two for loop
     * //https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
     * O(^2) complexity// works for both positive and negative numbers
     * @param A
     * @param K
     * @return
     */
    private int shortestSubArraySumAtleastK_slow(int[] A, int K){
        int len= A.length;
        int shortest = -1;
        if(A.length==0) return -1;
        //Using two pointers
        for(int i=0; i<len; i++){
            int sum=0;
            for(int j=i; j<len; j++){
                sum+=A[j];
                if(sum>=K){
                    if(shortest==-1){
                        shortest= j-i+1;
                    }
                    else if(j-i<shortest){
                        shortest=j-i+1;
                    }
                }
            }
        }
        return shortest;
    }
// This does not work for a6[] while sum increase and decrease with many -ve
    private int shortestSubArraySumAtleastK_fast(int[] A, int K){
        int len= A.length;
        int shortest = -1;
        if(A.length==0) return -1;
        //int[] sum = new int[len];
       // sum[0] = A[0]<0?0: A[0];
//        //Using two pointers and cumulative sum
//        for(int i=1; i<len; i++){
//
//            if(sum[i-1]+A[i] <0){
//                sum[i]=0; // Otherwise it won't work
//            }
//            else{
//                sum[i]=sum[i-1]+A[i];
//            }
//        }
//
//        int j=0;
//        //Iterate with two pointers i & j to find a[j]-a[i] >=K and sub array size = j-i+1; then reset i once condition met or if there is a -ve sum
//        for (int i = 0; i < len; i++) {
//            //shrink i until sum is >=K and  i<j
//            if(i<=j && i>0 && j<len && sum[j]-sum[i-1] >=K ){
//                //Shrink window
//                shortest = j-i+1 <shortest ? j-i+1:shortest;
//                //i++ id done in this loop
//                continue;
//            }
//            j=i;
//            while (j < len) {
//                if(i==0 && sum[j]>=K){
//                    shortest = shortest==-1? (j-i+1): j-i+1 <shortest ? j-i+1:shortest;
//                    break;
//                }
//                else if (sum[j]-sum[i]>= K) {
//                    shortest = shortest==-1? (j-i+1): j-i+1 <shortest ? j-i+1:shortest;
//                    break;
//                }
//                j++;
//
//            }
//
//        }
//        return shortest;
      int j=0; //end
        int i=0;//start
      int sum=0;
      int minLen=len+1;
        while(j<len){
            //Make sum until <=K
            while(sum<=K && j<len){
                sum+=A[j++];
            }
            //Shrink
            while(sum>=K && i<len ) {
                if (i < j && j - i < minLen) {
                    minLen = j - i;
                }
                sum = sum-A[i++];
            }
        }
    return minLen;
    }

    public int shortestSubarray(int[] A, int K) {
        int N = A.length;
        long[] P = new long[N+1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + (long) A[i];

        // Want smallest y-x with P[y] - P[x] >= K
        int ans = N+1; // N+1 is impossible
        Deque<Integer> monoq = new LinkedList(); //opt(y) candidates, as indices of P

        for (int y = 0; y < P.length; ++y) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()])
                monoq.removeLast();
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K)
                ans = Math.min(ans, y - monoq.removeFirst());

            monoq.addLast(y);
        }

        return ans < N+1 ? ans : -1;
    }
    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/
     * Count the number of sub array with exact sum == K .. Use prefix sum and a hash map. count when you encounter map.get(sum
     *
     * Algo: If the cumulative sum(repreesnted by sum[i]sum[i] for sum upto i^{th}i
     th
     index) upto two indices is the same, the sum of the elements lying in between those indices is zero.
     Extending the same thought further, if the cumulative sum upto two indices, say ii and jj is at a difference of kk i.e. if sum[i] - sum[j] = ksum[i]−sum[j]=k,
     the sum of elements lying between indices ii and jj is kk.
     * @param A
     * @param K
     * @return
     */
    private int countSubArraySumK(int[] A, int K){
        int result=0; int sum=0;
        Map<Integer, Integer> sumCountMap = new HashMap<>();
        sumCountMap.put(0,1);//zero to be considered
        //Find prefix/continuous sum and push to a map
        for(int i=0; i<A.length; i++){
            sum+=A[i];
            //Check sum-k exists in map then add that count to result..
            if(sumCountMap.containsKey(sum-K)) {
                result += sumCountMap.get(sum - K);
            }
            sumCountMap.put(sum,sumCountMap.getOrDefault(sum,0)+1);
        }
        return result;

    }

    /**
     * Shortest subarray with Exact sum  K
     * @param nums
     * @param k
     * @return
     */
    private int shortestSubArraySumK(int[] nums, int k){
        int result=0; int sum=0;
        Map<Integer, Integer> sumCountMap = new HashMap<>();
        sumCountMap.put(0,1);//zero to be considered
        //Find prefix/continuous sum and push to a map
        for(int i=0; i<nums.length; i++){
            sum+=nums[i];
            //Check sum-k exists in map then add that count to result..
            if(sumCountMap.containsKey(sum-k)) {
                result += sumCountMap.get(sum - k);
            }
            sumCountMap.put(sum,sumCountMap.getOrDefault(sum,0)+1);
        }
        return result;

    }

    /**
     * This method returns the start and end index of the minimum sum of its elements along with its sum for sub array of size K
     * @param arr input array
     * @param K size of subarray
     * @return
     */
    private static int[]  minimumSumSubArrayOfSizeK(int[] arr, int K){
        //check if array size < K
        if(arr.length<K){
            throw new UnsupportedOperationException("Array size is small");
        }
        int[] result = new int[3];
        int minSum=0;
        int start=0, end =0;
        //Find first K sum
        for(int i=0; i<K; i++){
            minSum+=arr[i];
            end=i;
        }
        int i=0;
        int sum = minSum;
        for(int j=K; j<arr.length; j++){
           sum += arr[j]-arr[i++]; // pre increment J and post increment i
            if(sum<minSum){
                minSum= sum;
                start=i;
                end=j;
            }
        }
        result[0]=start;
        result[1]=end;
        result[2]=minSum;
        return result;
    }
      static void printArray(int[] nums){
        for(int i=0; i<nums.length; i++){
            System.out.println(nums[i]);
        }
    }
    public static void main(String args[]){
        SubArraySumKProblems ssa = new SubArraySumKProblems();
        int[] a={1, 0,-1,2,-1,2};
        int[] b = {1,1,1,1,-1,1,2,3};
        int[] c ={2, -1,2};
        int[] d ={48,99,37,4,-31};
        int[] a1={1};
        int[] a2={1,2};
        int[] a3={1,1,1};
        int[] a4 ={17,85,93,-45,-21};
        int[] a5 = {-11,-15,76,41,-41,68,41,12,73,-8};
        int[] a6 = {-34,37,51,3,-12,-50,51,100,-47,99,34,14,-13,89,31,-14,-44,23,-38,6};
        int[] b1={10, 4, 3, 5 , 6, 3, 8 , 1};
        //System.out.println(ssa.shortestSubArraySumAtleastK_slow(a2,4));
        //System.out.println(ssa.shortestSubArraySumAtleastK_slow(a1,1));
        //System.out.println(ssa.shortestSubArraySumAtleastK_fast(a2,4));
        System.out.println("Count should be 1: "+ssa.shortestSubarray(a5,50));
        System.out.println("Count should be 1: "+ssa.shortestSubArraySumAtleastK_fast(a6,151));
        System.out.println("Count should be 1: "+ssa.shortestSubArraySumAtleastK_fast(a5,50));
        System.out.println("Count should be 2: "+ssa.shortestSubArraySumAtleastK_fast(a4,150));
        System.out.println(" Count should be 1: "+ssa.shortestSubArraySumAtleastK_fast(a1,1));
        System.out.println(" Count should be 3: "+ssa.shortestSubArraySumAtleastK_fast(c,3));

        System.out.println("Count should be 2: "+ssa.shortestSubArraySumAtleastK_fast(d,140));
        System.out.println(ssa.countSubArraySumK(a,3));
        System.out.println("Count should be 2: "+ssa.countSubArraySumK(a3,2));
        System.out.println("Count should be 5: "+ssa.countSubArraySumK(b,3));

        printArray(minimumSumSubArrayOfSizeK(a,2));
       printArray(minimumSumSubArrayOfSizeK(b1,3));
    }
}
