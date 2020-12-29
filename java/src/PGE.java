import java.util.Arrays;
import java.util.Stack;
/*
*
* Given an array, print the Previous Greater Element (PGE) for every element.
**
*/

public class PGE {

    public static int[] find(int[] nums){
        int[] out = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        // decreasing monotone stack
        for ( int i = 0;  i < nums.length; ++i ){
            while( !st.empty() && nums[i] >= st.peek() ) st.pop();
            out[i] = st.empty() ? -1 : st.peek();
            st.push(nums[i]);
        }
        return out;
    }

    public static void main(String[] args){
//       int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       int[] nums = {2,5,6,1,8};
       System.out.println(Arrays.toString(nums));
       System.out.println(Arrays.toString(find(nums)));
    }


}