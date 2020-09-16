import java.util.Arrays;

/*

Given two sorted lists (or arrays) and a number k,
create an algorithm to fetch k numbers of the two lists.

Note: the original question was "at least k". Ask the interviewer if k is least than nums1.length + nums2.length
 */
public class FetchK {

    public static int[] fetch(int[] nums1, int[] nums2, int k){
        int[] ans = new int[ Math.min(k, nums1.length+nums2.length) ];
        int i = 0, nums1Idx = 0, nums2Idx = 0;

        for ( ; nums1Idx < nums1.length && nums2Idx < nums2.length && i < ans.length; ++i ){
            if  ( nums1[nums1Idx] < nums2[nums2Idx] ){
                ans[i] = nums1[nums1Idx++];
            }
            else {
                ans[i] = nums2[nums2Idx++];
            }
        }
        for ( ; nums1Idx < nums1.length && i < ans.length ; ++i ) {
            ans[i] = nums1[nums1Idx++];
        }
        for ( ; nums2Idx < nums2.length && i < ans.length; ++i ) {
            ans[i] = nums1[nums2Idx++];
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums2 = {0,3,4,5,5};
        int[] nums1 = {7,8,9,10,11};
        int k = 100000;
        System.out.println(Arrays.toString(fetch(nums1,nums2,k)));
    }
}
