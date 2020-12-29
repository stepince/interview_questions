import java.util.*;

/*

GGiven an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such
that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

https://leetcode.com/problems/4sum/

Time: O(N^3)
Space: O(N)
 */
public class FourSum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for ( int i = 0; i < nums.length-2; ++i ){
            for ( int j = i +1; j < nums.length-1; ++j ){
                final int sum = nums[i] + nums[j];
                for( int k = j + 1, l = nums.length-1; k < l; ){
                    int total = sum + nums[k] + nums[l];
                    if ( total == target ){
                        String key = nums[i] + ":"+nums[j]+":"+nums[k]+":"+nums[l];
                        if ( !visited.contains(key)){
                            results.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));
                            visited.add(key);
                        }
                        ++k;
                        --l;
                    }
                    else if ( total < target ){
                        ++k;
                    }
                    else {
                        --l;
                    }
                }
            }
        }
        return results;
    }

    public static void main(String[] args){
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        System.out.println(fourSum(nums,target));
    }
}
