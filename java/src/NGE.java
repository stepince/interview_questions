import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;
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

    public static int[] find(int[] arr){
        int[] out = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        out[arr.length-1] = -1;
        st.push(arr.length-1);
        for( int i = arr.length-1; i > 0; --i ){
            if ( arr[i] > arr[i-1] ){
                out[i-1] = arr[i];
            }
            else {
                while(!st.empty() && arr[i-1] >= arr[st.peek()]) st.pop();
                if ( st.empty() ) {
                    out[i-1] = -1;
                }
                else {
                    out[i-1] = arr[st.peek()];
                }
            }
            st.push(i-1);
        }
        return out;
    }

    public static void main(String[] args){
       int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       System.out.println(Arrays.toString(find(arr)));
    }


}