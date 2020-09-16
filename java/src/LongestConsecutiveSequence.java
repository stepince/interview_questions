/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.
https://leetcode.com/problems/longest-consecutive-sequence/

 */

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

    public static int find(int[] nums) {

        Map<Integer,Integer> numMap = new HashMap<>();
        int globalCount = 0;
        for ( int num: nums ){
            // don't double dip
            if ( numMap.containsKey(num) ) continue;
            int left = numMap.getOrDefault(num-1,0);
            int right = numMap.getOrDefault(num+1,0);
            int sum = left + right + 1;
            numMap.put(num,sum);
            //update the outer bounds of the number range with the sum
            numMap.put(num-left, sum);
            numMap.put(num+right, sum);

            globalCount = Math.max(globalCount,sum);
        }
        return globalCount;
    }

    public static void main(String[] args){
        int[] nums = {100,4,200,1,3,2};

//        int[] nums = {-2,-3,7,5,0,-8,-4,-1,2};
//        int[] nums = {1,3,5,2,4};
//        int[] nums = {};
        System.out.println(find(nums));
    }
}
