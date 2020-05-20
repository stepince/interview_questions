/*
 *  Number of ways to go down a stair case, 2 steps or one step
 *
*/


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 *  Number of ways to go down a staircase, n steps
 *  Input : 4 , steps "1,2,3"
 * Output : 7
 *
 * runtime: O(3^N)
 */
public class StaircaseWaysDp {

    private static int find(int stairs, int[] steps, Map<Integer,Integer> mem) {
        if ( stairs < 0 ) return 0;
        if ( stairs == 0 ) return 1;
        if ( mem.containsKey(stairs)) return mem.get(stairs);
        int total = 0;
        for( int step: steps){
           total += find( stairs-step, steps, mem);
        }
        mem.put(stairs,total);
        return total;
    }

    public static int find(int stairs, int[] steps) {
        return find(stairs, steps, new HashMap<>());
    }
    public static void main(String[] args){
       int stairs = Integer.parseInt(args[0]);
       int[] steps = Arrays.stream(args[1].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       System.out.println(find(stairs,steps));
    }



}
