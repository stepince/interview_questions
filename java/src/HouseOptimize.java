import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
   If both sides of the house or active, or in active, then it is inactive else active
   What is the state after N days.

 */
public class HouseOptimize {

    static List<Integer> find(int[] state, int days){
        for ( int i = 0; i < days; ++i){
            int[] prevState = state.clone();
            for ( int j = 0; i < state.length; ++j){
                int left = j>0? prevState[j-1] : 0;
                int right = j<state.length-1? prevState[j+1] : 0;
                state[i] = ( left == right ) ? 0 : 1;
            }
        }
        return Arrays.stream(state).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int days = Integer.parseInt(args[1]);
        System.out.println( find( arr, days));
    }
}
