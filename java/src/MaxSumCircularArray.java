
/*
*
*
*  https://leetcode.com/problems/maximum-sum-circular-subarray/
*
* Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
*
*
*   Solution
*    1, 2,3, 4,   -3 , -3,   4,5, 5
*    ----------||--minSum -||-----
*        a         b          c
*
*  maxSum = Max( Kadane, a+c );
*  except for all negative values
*  check for this case: total + abs(minSum)| != 0
*     -3,-2,-1  = -6 +6 == 0  minSum is done by inverting array and applying kadane algo
*
 */
public class MaxSumCircularArray {

    // kadane algo
    static int kadane( int[] A ){
        int curr = A[0], max = A[0];
        for ( int i = 1; i < A.length; ++i ){
            curr=Math.max(curr+A[i], A[i]);
            max=Math.max(max,curr);
        }
        return max;
    }

    public static int maxSubarraySumCircular(int[] A) {
        if (A.length == 0 ) return 0;
        if (A.length == 1 ) return A[0];
        int ks = kadane(A);
        int sum = 0;
        for (int i = 0; i < A.length; ++i){
            sum+=A[i];
            A[i]=-A[i];
        }
        int total = sum + kadane(A);
        // checks for all negative values
        // ( total == 0 ) return ks (all neg case )
        return total == 0 ? ks : Math.max(total, ks);
    }

    public static void main(String[] args){
        int[] nums = {1,-2,3,-2};
        System.out.println(maxSubarraySumCircular(nums));
    }

}
