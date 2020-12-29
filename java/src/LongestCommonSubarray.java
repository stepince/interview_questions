/*
https://leetcode.com/problems/maximum-length-of-repeated-subarray/

Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 */
public class LongestCommonSubarray {

    private static int findLengthUtil(int[] A, int idxA, int[] B, int idxB, Integer[][] mem) {
        if ( idxA == A.length || idxB == B.length ) return 0;
        if ( mem[idxA][idxB] != null ) return mem[idxA][idxB];
        return mem[idxA][idxB] = (A[idxA] == B[idxB]) ? (1 + findLengthUtil(A, idxA+1, B, idxB+1, mem)) : 0;
    }

    public static int findLength(int[] A, int[] B) {
        int max = 0;
        Integer[][] mem = new Integer[A.length][B.length];
        for ( int i = 0; i < A.length; ++i ){
            for ( int j = 0; j < B.length; ++j ){
                if ( A[i] == B[j] ){
                    max = Math.max(max, (1 + findLengthUtil(A,i+1, B, j+1, mem)));
                }
            }
        }
        return max;
    }

    public static void main(String[] args){
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 =  {3,2,1,4,7};
        System.out.println(findLength(nums1,nums2));
    }
}
