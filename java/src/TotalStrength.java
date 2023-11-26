import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class TotalStrength {

    static class Pair {
        int index;
        int num;
        Pair(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }
    int[] strength;
    int[] prefixSum;

    int mins[][];

    // 2,4,5,6,6

    long totalStrengthUtilBackward(int idx){
        if ( idx == 0 ) return 0;
        return 0;
    }

    long totalStrengthUtil(int idx){
        if ( idx == strength.length ) return 0;
        if ( idx == 0 ) return prefixSum[0] + totalStrengthUtil(idx+1);
        return prefixSum[idx] + totalStrengthUtilBackward(idx) + totalStrengthUtil(idx+1);
    }

    public int totalStrength(int[] strength) {
        int len = strength.length;
        this.strength = strength;
        this.prefixSum = new int[len];
        int sum = 0;
        prefixSum[0] = strength[0];
        Deque<Pair> deq = new ArrayDeque<>();
        Deque<Pair> deqSubset = new ArrayDeque<>();
        long sumMin = strength[0] * strength[0];
        deq.offer(new Pair(0,strength[0]));
        for( int i = 1; i < strength.length; ++i ){



            sumMin += deq.peek().num * prefixSum[i];


        }

        Stack<Pair> st = new Stack<>();

        for ( int i = 0; i < len ; ++i ) {
            // increasing stack
            while ( !st.isEmpty() && strength[i] < st.peek().num )  st.pop();
            st.push(new Pair(i,strength[i]));

            sum += prefixSum[i] * st.peek().num;
        }
        return (int)(sum%1000000007);
    }
}
