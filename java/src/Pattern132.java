import java.util.Stack;
/*

Given an array of n integers nums, a 132 pattern is a subsequence of three integers
nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

Return true if there is a 132 pattern in nums, otherwise, return false.


 */
public class Pattern132 {

    public boolean find132pattern(int[] nums) {
        // second largest
        int s2 = Integer.MIN_VALUE;
        Stack<Integer> st = new Stack<>();
        // use an increasing monotone stack, top of the stack is the s3
        for ( int i = nums.length-1; 0 <= i; --i ){
            // check is nums[i] is s1
            if ( s2 > nums[i] ) return true;
            while ( !st.isEmpty() && nums[i] > st.peek() ) s2 = st.pop();
            st.push(nums[i]);
        }
        return false;
    }

}
