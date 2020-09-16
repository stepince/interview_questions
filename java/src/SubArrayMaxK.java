/*
https://leetcode.com/problems/contains-duplicate-iii/


Given an array of integers, find out whether there are two
distinct indices i and j in the array such that the absolute
difference between nums[i] and nums[j] is at most t and the
absolute difference between i and j is at most k.

Notes:
   K is the window size and T is the max difference
Input: nums = [1,2,3,1], k = 3, t = 0
Output: true

windowing problem

 */

import java.util.LinkedList;
import java.util.TreeMap;

public class SubArrayMaxK {

    public static class MyQueue extends LinkedList<Long>{
        TreeMap<Long,Integer> treeMap = new TreeMap<>();

        @Override
        public boolean add(Long val) {
            treeMap.merge(val,1,Integer::sum);
            return super.add(val);
        }

        @Override
        public Long remove() {
            Long key = super.remove();
            Integer val = treeMap.merge(key,-1,Integer::sum);
            if ( val <= 0 ) treeMap.remove(key);
            return key;
        }

        public Long floorKey(Long key){
            return treeMap.floorKey(key);
        }

        public Long ceilingKey(Long key){
            return treeMap.ceilingKey(key);
        }
    }

    public static boolean find(int[] nums, int k, int t) {
        if ( k <= 0 ) return false;
        MyQueue myQueue = new MyQueue();
        // 1, 0, 1, 1    k=1 , t=2
        // 1  1  2  3

        // 2  4 5  6 13
        // 2  6 11 17

        // 2 -6  5 6 13
        // 2 -4 1  7
        for (long num : nums) {
            Long floor = myQueue.floorKey(num);
            if (floor != null && t >= Math.abs(floor - num)) return true;
            Long ceiling = myQueue.ceilingKey(num);
            if (ceiling != null && t >= Math.abs(ceiling - num)) return true;
            if (myQueue.size() == k) myQueue.remove();
            myQueue.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] nums  = {1, 0, 1, 1};
//        int K = 1;
//        int T = 2;

        int[] nums  = {-1,2147483647};
        int K = 1;
        int T = 2147483647;
        System.out.println(find(nums,K,T));
    }

}
