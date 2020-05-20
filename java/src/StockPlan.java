import java.util.Arrays;
import java.util.Stack;
/*
The stock span problem is a financial problem where we have a series of
n daily price quotes for a stock and we need to calculate span of stock’s
price for all n days.
The span Si of the stock’s price on a given day i is defined as the maximum
number of consecutive days just before the given day, for which the price of the
stock on the current day is less than or equal to its price on the given day.
For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85},
then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}


input: {100, 80, 60, 70, 60, 75, 85}
output: {1, 1, 1, 2, 1, 4, 6}

notes: optimal solution is to use a stack that represents the current range for nth element
 */
public class StockPlan {

    //O(N)
    public static int[] find(int[] arr){
        int[] out = new int[arr.length];
        out[0]=1;
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for( int i = 1; i < arr.length; ++i ){
            if ( arr[i-1] > arr[i] ){
                out[i]=1;
            }
            else {
                while( !st.empty() && arr[i] >= arr[st.peek()] ) st.pop();
                if ( st.empty() ) {
                    out[i]=1;
                }
                else {
                    out[i] = i - st.peek();
                }
            }
            st.push(i);
        }
        return out;
    }

    public static void main(String[] args){
        //int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] arr = {50, 100, 80, 60, 70, 60, 75, 85};
        System.out.println(Arrays.toString(find(arr)));
    }

}