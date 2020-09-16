
/*

https://leetcode.com/explore/interview/card/top-interview-questions-medium/103/array-and-strings/781/

Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

find2 is a brute force dynamic programing approach.
Time:
   O(2^n)

find3
  Time:
      O(3n^2)

find1: is linear solution.

https://medium.com/@xiaogegexiao/increasing-triplet-subsequence-problem-995471b338f1
  Time:
      O(n)

 */

import java.util.HashMap;
import java.util.Map;

public class IncreasingTripletSubsequence {

    static int increment( Integer prev, int curr, int count){
        if ( prev == null ) return 1;
        return ( curr > prev ) ? count+1 : 1;
    }

    public static boolean find2(int[] nums, int idx, Integer prev, int count){
        if ( count >= 3 ) return true;
        if( idx == nums.length ) return false;
        return find2(nums, idx+1, nums[idx], increment(prev,nums[idx],count) ) || find2( nums, idx+1, prev, count);
    }

    public static boolean find3(int[] nums, int idx, Integer prev, int count, Map<String,Boolean> mem){
        if ( count >= 3 ) return true;
        if( idx == nums.length ) return false;
        String key = idx +":" + prev + ":" + count;
        if (mem.containsKey(key)) return mem.get(key);

        boolean ans = find3(nums, idx+1, nums[idx], increment(prev,nums[idx],count),mem ) || find3( nums, idx+1, prev, count,mem);
        mem.put(key,ans);
        return ans;
    }

    public static boolean find2(int[] nums){
        return find2(nums, 0, null, 1);
    }

    public static boolean find1(int[] nums){
        int val1 = Integer.MAX_VALUE;
        int val2 = Integer.MAX_VALUE;

        for ( int num: nums ){
            val1 = Math.min(val1,num);
            if ( num > val1 ) val2 = Math.min(val2,num);
            if ( num > val2 ) return true;
        }
        return false;
    }

    public static boolean find3(int[] nums){
        return find3(nums, 0, null, 1, new HashMap<>());
    }

    public static void main(String[] args ){
//        int[] nums = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();

        int[] nums = { 2,3,-1,-5,-1};
        System.out.println(find1(nums));
        System.out.println(find2(nums));
        System.out.println(find3(nums));
    }
}
