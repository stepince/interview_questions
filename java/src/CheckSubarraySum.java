
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/continuous-subarray-sum/

Given a list of non-negative numbers and a target integer k,
write a function to check if the array has a continuous subarray
of size at least 2 that sums up to a multiple of k, that is,
sums up to n*k where n is also an integer.

 */
public class CheckSubarraySum {
    public static boolean find(int[] nums, int k) {

        // edge case checks
        if ( nums.length < 2 ) return false;

        for ( int i = 1; i < nums.length ; ++i){
            // zeroes are multiples of everything including zeros,  n*k=0
            if ( nums[i-1] == 0 && nums[i] == 0 ) return true;
        }
        // only zero is a multiple of zero
        if ( k == 0 ) return false;

        Set<Integer> prefixSet = new HashSet<>();
        int prefixSum = nums[0];
        prefixSet.add(nums[0]);

        // numbers are positive but k does not have to be positive.    10 is multiple of -5. -5 * -2=10, n=-2
        k=Math.abs(k);
        for ( int i = 1; i < nums.length; ++i){

            prefixSum += nums[i];
            // check for current [2,4] k = 6, should pass
            if ( prefixSum % k == 0 ) return true;

            for( int multipleK = k; multipleK < prefixSum;  multipleK += k) {

                // don't include just added multipleK e.g [2,12] k=6,  should fail,
                if ( nums[i] == multipleK ) continue;

                // check for all multiples of k
                if ( prefixSet.contains( prefixSum - multipleK ) ){
                    return true;
                }
            }
            prefixSet.add(prefixSum);
        }
        return false;
    }


    public static void main(String[] args){
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int[] arr = {11,5,7};
////        int k = 0;

//        int[] nums = {23,6,9};
//        int k = 6;


//        int[] nums = {23,2,6,4,7};
//        int k = -6;
//        int[] nums = {1,2,4,7};
//        int k = 6;
        int[] nums = {0,1,0};
        int k = -1;
        System.out.println(find(nums,k));
    }
}
