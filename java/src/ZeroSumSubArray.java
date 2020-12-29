import java.util.HashSet;
import java.util.Set;

/*

Given an array of positive and negative numbers,
find if there is a sub array (of size at-least one) with 0 sum.

Input: {4, 2, -3, 1, 6}
Output: true
There is a sub array with zero sum from index 1 to 3.

Input: {4, 2, 0, 1, 6}
Output: true
There is a sub array with zero sum from index 2 to 2.

Input: {-3, 2, 3, 1, 6}
Output: false
There is no sub array with zero sum.
O(n);

Notes: sub array is a contiguous set of elements
 */
public class ZeroSumSubArray {

    public static boolean find(int[] nums) {
        Set<Integer> prefixSet = new HashSet<>();
        int accSum = 0;
        for ( int num: nums ) {
            accSum += num;
            if ( accSum == 0 || prefixSet.contains(accSum) ) return true;
            prefixSet.add(accSum);
        }
        return false;
    }

    public static void main(String[] args){
//        int[] arr =  Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] nums = {1,3,-3};
        System.out.println(find(nums));
    }
}