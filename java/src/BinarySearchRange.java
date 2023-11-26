import java.util.Arrays;

/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

 */
public class BinarySearchRange {

    public static int upperBound(int[] nums, int target, int start) {
        int lo = start;
        int hi = nums.length;
        int idx = start;
        while ( lo < hi ) {
            int mid = (lo + hi)/2;
            if ( nums[mid] == target ) {
                lo = mid + 1;
                idx = mid;
            }
            else if ( target < nums[mid] ) {
                hi = mid;
            }
            else {
                lo = mid +1;
            }
        }
        return idx;
    }

    public static int lowerBound(int[] nums, int target, int start) {
        int lo = 0;
        int hi = start;
        int idx = start;
        while ( lo < hi ) {
            int mid = (lo + hi)/2;
            if ( nums[mid] == target ) {
                hi = mid;
                idx = mid;
            }
            else if ( target < nums[mid] ) {
                hi = mid;
            }
            else {
                lo = mid +1;
            }
        }
        return idx;
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1,-1};
        int idx = -1;
        int lo = 0;
        int hi =  nums.length;
        while ( lo < hi ){
            int mid = (lo + hi)/2;
            if ( nums[mid] == target){
                idx = mid;
                break;
            }
            else if ( target < nums[mid] ){
                hi = mid;
            }
            else {
                lo = mid +1;
            }
        }
        if ( idx == -1 ) return result;
        int lower = lowerBound(nums, target, idx);
        int upper = upperBound(nums, target, idx);
        return new int[]{lower,upper};
    }

    public static void main(String[] args){
//       int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//       int num = Integer.parseInt(args[1]);
      int[] nums = {5,7,7,7,7,7,8,8,10};

       System.out.println(Arrays.toString(searchRange(nums, 7)));

    }
}