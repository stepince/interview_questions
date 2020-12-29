import java.util.Arrays;
import java.util.Stack;
/*
*
* Given an array, print the Next Greater Element (NGE) for every element.
* The Next greater Element for an element x is the first greater element on the right side of x in array.
* Elements for which no greater element exist, consider next greater element as -1.
*
* input:  4, 5, 2, 25
* output: 5, 25, 25, -1
*
* input: 13, 7, 6, 12
* output: -1, 12, 12, -1
*
* input: 11, 13, 21, 3
* output: 13, 21, -1, -1
*
* input: 11, 13, 13, 21, 3
* output: 13, 21, 21, -1, -1
*
*/

public class NGE {

    public static int[] find(int[] nums){
        int[] out = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        // monotone stack
        // 2,3,4,5
        for ( int i = nums.length-1; i >= 0; --i ){
            while( !st.empty() && nums[i] >= st.peek()) st.pop();
            out[i] = st.empty() ? -1 : st.peek();
            st.push(nums[i]);
        }
        return out;
    }

    public static void main(String[] args){
//       int[] nums = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       int[] nums = {2,5,6,1,8};
       System.out.println(Arrays.toString(nums));
       System.out.println(Arrays.toString(find(nums)));
    }


}