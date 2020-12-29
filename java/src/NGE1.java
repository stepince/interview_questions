import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/*
https://leetcode.com/problems/next-greater-element-i/


 */
public class NGE1 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] out1 = new int[nums1.length];
        Map<Integer,Integer> map = new HashMap<>();
        for ( int i = 0; i < nums1.length; i++ ){
            map.put(nums1[i],i);
        }
        Stack<Integer> st = new Stack<>();
        // decreasing 2,3,4,5
        for ( int i = nums2.length - 1; i >= 0 ; --i ){
            while( !st.empty() && nums2[i] >= st.peek() ) st.pop();
            int val = st.empty() ? -1 : st.peek();
            if ( map.containsKey(nums2[i])){
                out1[map.get(nums2[i])] = val;
            }
            st.push(nums2[i]);
        }
        return out1;
    }
}
