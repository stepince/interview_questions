import java.util.ArrayList;
import java.util.List;
/*

https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

 */
public class DisappearedNumbers {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> results = new ArrayList<>();

        for ( int num: nums){
            int idx = Math.abs(num)%nums.length;
            if ( nums[idx] > 0 )  nums[idx] *= -1;
        }

        for ( int i = 1 ; i <= nums.length; ++i){
            int idx = i%nums.length;
            if ( nums[idx] > 0  ) results.add(i);
        }
        return results;
    }

}
