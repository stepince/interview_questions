/*

A sub-array has one number of some continuous numbers. 
Given an integer array with positive numbers and negative numbers,
get the maximum sum of all sub-arrays. Time complexity should be O(n). 
For example, in the array {1, -2, 3, 10, -4, 7, 2, -5}, 
its sub-array {3, 10, -4, 7, 2} has the maximum sum 18.

Kadane algorithm
O(n)
*/

import java.util.stream.IntStream;

public class MaxSumSubArray {

   public static int find( int[] arr ){
       if ( arr.length == 0 ) return 0;
       int curr = arr[0], maxGlobal = arr[0];
       for ( int i = 1; i < arr.length; ++i ){
           curr = Math.max( curr + arr[i], arr[i]);
           maxGlobal = Math.max(maxGlobal,curr);
       }
       return maxGlobal;
   }
    public static void main(String[] args){
//        int[] arr = Arrays.stream(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(find(arr));
        int k = 5;
        int curr = IntStream.of(arr).limit(k).sum();
        int max = curr;
        for (int i = k; i < arr.length; ++i) max = Math.max(max, curr += (arr[i] - arr[i-k]) );
//        return max;
        System.out.println("max:" + max);

    }
}