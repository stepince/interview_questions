/*
Given an array of positive numbers, find the maximum sum of a subsequence
with the constraint that no 2 numbers in the sequence should be adjacent in the array.


 */

public class MaxSumSubsequenceNonConsecutive {

    public static int find(int[] arr){
        if ( arr == null || arr.length == 0 ) return 0;
        int globalSum = 0;
        int includeSum = 0;
        int excludeSum = 0;
        for (int current : arr) {
            includeSum += current;

            //swap include/exclude
            int temp = includeSum;
            includeSum = excludeSum;
            excludeSum = temp;

            // change?? iff current elem is larger
            excludeSum = Math.max(current, excludeSum);

            // update global max
            globalSum = Math.max(globalSum, excludeSum);
            globalSum = Math.max(globalSum, includeSum);
        }
        return globalSum;
    }

    public static void main(String[] args){
       int[] arr = {5, 5, 10, 100, 10, 5};
       System.out.println(find(arr));
    }
}
