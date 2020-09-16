/*


Given an array of integers and a value, determine
if there are any three integers in the array whose sum equals the given value.

https://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TripletsSum {

    public static boolean find1(int[] nums, int target){
        Set<Integer> set = new HashSet<>();
        for ( int i = 0; i < nums.length-2;++i){
            for ( int j = i+1; j < nums.length; ++j ){
                if ( set.contains(nums[i]+nums[j]) ) {
//                    System.out.println(nums[i] + "," + nums[j] + "," + (target-nums[i]-nums[j]) );
                    return true;
                }
            }
            set.add(target-nums[i]);
        }
        return false;
    }

    public static boolean find2(int[] nums, int target){
        Arrays.sort(nums);
        for ( int i = 0; i < nums.length-2;++i){
            for ( int j = i + 1, k = nums.length-1; j < k; ) {
                int sum = nums[i] + nums[j] + nums[k];
                if ( sum == target){
//                    System.out.println(nums[i] + "," + nums[j] + "," + nums[k]);
                    return true;
                }
                else if ( sum < target ){
                    ++j;
                }
                else {
                    --k;
                }
            }
        }
        return false;
    }
    public static void main(String[] args){

//        int[] arr = {8,6,5,1};
//        int sum = 15;

        int[] arr = {8,6,5,1,2,3,4,5,55,22,11,9,55,2,1,34,32,88,7,8,8,8,8,8,8,6,5,1,2,3,80,4,5,33,55,22,11,9,55,2,1,34,32,88,7,8,8,8,8,8};
        int sum = 190;
        long t = System.currentTimeMillis();
        System.out.println(find1(arr,sum));
        System.out.println("Time 1: " + ( System.currentTimeMillis() - t));
        t = System.currentTimeMillis();

        System.out.println(find2(arr,sum));
        System.out.println("Time 2: " + ( System.currentTimeMillis() - t));

    }

}
