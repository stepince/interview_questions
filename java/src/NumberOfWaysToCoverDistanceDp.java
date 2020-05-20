/*

Given a distance �dist, count total number of ways to cover the distance with 1, 2 and 3 steps.
This is basically the same as the staircase problem.

Input:  n = 3, "1,2,3"

Output: 4
*/


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberOfWaysToCoverDistanceDp {

    private static int find( int dist, int[] steps, Map<Integer,Integer> mem ){
       if ( dist < 0 ) return 0;
       if ( dist == 0 ) return 1;
       if ( mem.containsKey(dist) ) return mem.get(dist);
       int total = 0;
       for ( int step: steps ) {
           total += find( dist - step, steps, mem );
       }
       mem.put(dist,total);
       return total;          
    }

    public static int find( int dist, int[] steps ) {
        return find( dist, steps, new HashMap<>() );
    }

    public static void main(String[] args){
        int dist = Integer.parseInt(args[0]);
        int[] steps = Arrays.stream(args[1].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(dist,steps));
    }
}