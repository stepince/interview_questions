/*
 *  Number of ways to go down a staircase, 2 steps or one step
 *
*/


import java.util.Arrays;
/*
 *  Number of ways to go down a staircase, n steps
 *  Input : 4 , steps "1,2,3"
 * Output : 7
 *
 * runtime: O(3^N)
 */
public class StaircaseWays {

    public static int find(int stairs, int[] steps) {
        if ( stairs < 0 ) return 0;
        if ( stairs == 0 ) return 1;
        int total = 0;
        for( int step: steps){
           total += find( stairs-step, steps);
        }
        return total;
    }

    public static void main(String[] args){
       int stairs = Integer.parseInt(args[0]);
       int[] steps = Arrays.stream(args[1].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       System.out.println(find(stairs,steps));
    }
}
