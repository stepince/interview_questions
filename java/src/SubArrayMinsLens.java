import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/


Given an array of integers arr and an integer target.

You have to find two non-overlapping sub-arrays of arr each with sum equal target. There can be multiple
answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.

Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot
find such two sub-arrays.
 */
public class SubArrayMinsLens {

    static void reverse(int[] arr){
        for ( int i = 0, j=arr.length-1; i < j; ++i, --j ){
            int tmp = arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
        }
    }

    static int[] createPrefixSumMin( int[] arr, int target ){
        Map<Integer,Integer> prefixSumMap =  new HashMap<>();
        int[] prefixSumMin = new int[arr.length];
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for ( int i = 0; i < arr.length; ++i ){
            sum += arr[i];
            int complement = sum - target;
            if ( sum == target ){
                int len = i + 1;
                min = Math.min(len,min);
            }
            else if ( prefixSumMap.containsKey(complement) ){
                int len = i - prefixSumMap.get(complement);
                min = Math.min(len,min);
            }
            // update the current min len for the target
            prefixSumMin[i] = min;
            prefixSumMap.put(sum, i);
        }
        return prefixSumMin;
    }

    public static int minSumOfLengths(int[] arr, int target) {
        if ( arr.length < 2 ) return -1;
        int len = Integer.MAX_VALUE;
        int[] prefixSumMin = createPrefixSumMin( arr,  target );
        reverse(arr);
        int[] suffixSumMin = createPrefixSumMin( arr,  target );
        reverse(suffixSumMin);
        // compare prefixSumMin and suffixSumMin
        for ( int i = 0 ; i < arr.length-1; ++i ){
            // comparing arrays
            if ( prefixSumMin[i] != Integer.MAX_VALUE && suffixSumMin[i+1] != Integer.MAX_VALUE ){
                len = Math.min(len, prefixSumMin[i] + suffixSumMin[i+1]);
            }
        }
        return len == Integer.MAX_VALUE ? -1 : len ;
    }

    public static void main(String[] args){
//        int[] nums = {3,2,2,4,3};
//        int target = 3;

        int[] nums = {4,3,2,6,2,3,4};
        int target = 6;
        long t = System.currentTimeMillis();
        System.out.println(minSumOfLengths(nums, target));
        System.out.println("time = " + ( System.currentTimeMillis() - t ) );
    }
}
