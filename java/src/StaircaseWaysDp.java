/*
 *  Number of ways to go down a stair case, 2 steps or one step
 *
*/

import java.util.Arrays;

/*
 *  Number of ways to go down a staircase, n steps
 *  Input : 4 , steps "1,2,3"
 * Output : 7
 *
 * runtime: O(N)
 */
public class StaircaseWaysDp {

    private static int find(int stairs, int[] steps, Integer[] mem) {
        if ( stairs < 0 ) return 0;
        if ( stairs == 0 ) return 1;
        if ( mem[stairs] != null ) return mem[stairs];
        int total = 0;
        for( int i = 0; i < steps.length && steps[i] <= stairs; ++i){
            total += find( stairs-steps[i], steps, mem);
        }
        return mem[stairs] =  total;
    }

    public static int find(int stairs, int[] steps) {
        Arrays.sort(steps);
        return find(stairs, steps, new Integer[stairs+1] );
    }

    public static void main(String[] args){
//       int stairs = Integer.parseInt(args[0]);
//       int[] steps = Arrays.stream(args[1].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int stairs = 4;
        int[] steps = {1,2,3};
        System.out.println(find(stairs,steps));
    }

}
