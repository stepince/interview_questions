/*

Given a distance �dist, count total number of ways to cover the distance with 1, 2 and 3 steps.
This is basically the same as the staircase problem.

Input:  "1,2,3" n = 3

Output: 4
*/

import java.util.Arrays;

public class NumberOfWaysToCoverDistance {

    public static int find( int dist, int[] steps){
        if ( dist < 0 ) return 0;
        if ( dist == 0 ) return 1;
        int total = 0;
        for ( int step: steps ) {
            // notice you can repeat the step
            total += find( dist - step, steps);
        }
        return total;
    }

    public static void main(String[] args){
        int[] steps = Arrays.stream(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int dist = Integer.parseInt(args[1]);
        System.out.println(find(dist,steps));
    }
}