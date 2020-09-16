import java.util.*;

/*

Given an array nums of n integers, are there elements
a, b, c in nums such that a + b + c = 0? Find all unique triplets
in the array which gives the sum of zero.

https://leetcode.com/problems/3sum/submissions/

Solution 1: is the fastest
Solution 2: is the  2nd fastest
Solution 3: is the slowest.

All three solution passed leetcode tests.

 */
public class ThreeSum {

    static public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        for ( int i = 0; i < nums.length-2;++i){
            for ( int j = i + 1, k = nums.length-1; j < k ; ){
                int sum = nums[i] + nums[j] + nums[k];
                if ( sum == 0 ){
                    results.add( Arrays.asList(nums[i],nums[j],nums[k]) );
                    ++j;
                    --k;
                    // fast forward and fast backward same numbers
                    while( j < k && nums[j] == nums[j-1] ) ++j;
                    while( j < k && nums[k] == nums[k+1] ) --k;
                }
                else if ( sum > 0 ){
                    --k;
                    while( j < k && nums[k] == nums[k+1] ) --k;
                }
                else {
                    ++j;
                    while( j < k && nums[j] == nums[j-1] ) ++j;
                }
            }
            while( i < nums.length-2 && nums[i] == nums[i+1] ) ++i;
        }
        return results;
    }

    static public  List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        Set<String> marked = new HashSet<>();
        List<List<Integer>> results = new ArrayList<>();
        for ( int i = 0; i < nums.length-2;++i){
            for ( int j = i + 1, k = nums.length-1; j < k ; ){
                int sum = nums[i] + nums[j] + nums[k];
                if ( sum == 0 ){
                    String key = nums[i] + ":" + nums[j] + ":" +nums[k];
                    if ( !marked.contains(key) ){
                        results.add( Arrays.asList(nums[i],nums[j],nums[k]) );
                        marked.add(key);
                    }
                    ++j;
                    --k;
                }
                else if ( sum > 0 ){
                    --k;
                }
                else {
                    ++j;
                }
            }
        }
        return results;
    }

    static public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        Set<String> marked = new HashSet<>();
        Set<Integer> s = new HashSet<>();
        for ( int i = 0; i < nums.length-1; ++i ){
            for ( int j = i+1; j < nums.length; ++j ) {
                int sum = nums[i] + nums[j];
                int complement = -sum;
                if (sum + complement > 0) break;
                if (s.contains(complement)) {
                    String key = complement + ":" + nums[i] + ":" + nums[j];
                    if (!marked.contains(key)) {
                        results.add(Arrays.asList(complement, nums[i], nums[j]));
                        marked.add(key);
                    }
                }
            }
            s.add(nums[i]);
        }
        return results;
    }



    public static void main(String[] args){
        int[] nums = {-1, 0, 1, 2, -1, -4};

        System.out.println(threeSum1(nums));
        System.out.println(threeSum2(nums));
        System.out.println(threeSum3(nums));
    }
}
