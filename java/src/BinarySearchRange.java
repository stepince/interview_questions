
/*

https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

 */
public class BinarySearchRange {

    public static int searchRange(int[] nums, int target){
        int lo = 0;
        int hi =  nums.length;
        int idx = - 1;
        while ( lo <= hi ){
            int mid = lo + (hi - lo) / 2;
            // int mid = (lo + hi)/2;
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
        return idx;
    }

    public static void main(String[] args){
//       int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//       int num = Integer.parseInt(args[1]);
      int[] nums = {5,7,7,7,7,7,8,8,10};

       System.out.println(searchRange(nums,8));

    }
}