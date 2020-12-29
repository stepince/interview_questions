/*

https://leetcode.com/problems/find-peak-element/

A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.


Notes:
   I am sure that this can be simplified but this understandable
 */

public class PeakFinder {

    public static int findPeakElement(int[] nums, int l, int r) {
        if ( l == r ) return l;
        if ( l + 1 == r ) return nums[l] > nums[r] ? l : r ;
        int mid = (l+r)/2;
        if ( nums[mid] < nums[mid+1] ){
            return findPeakElement(nums,mid+1,r );
        }
        else if ( nums[mid] < nums[mid-1]  ) {
            return findPeakElement(nums,l, mid-1);
        }
        else {
            return mid;
        }
    }

    // nums = [1,2,3,1]
    public static int findPeakElement(int[] nums) {
        return findPeakElement(nums,0,nums.length-1);
    }


    public static void main(String[] args){
        int[] nums = {1,2,3,4,5,4,3,3,3,3,1};
        System.out.println(findPeakElement(nums));
    }
}
