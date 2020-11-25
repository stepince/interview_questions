/*
    Bloomberg optimization problem

    Given a two dimension array representing travel costs.
    Find the lowest cost for travel.
    Half can go to either SF of NY

         e.g.
           NY   SF
A         600  400
B         300  500
C         200  400
D         300  700

    CD - NY  , AB -  SF
       500   +   900      =   1400

optimizations:  you can sort the travel for NY and SF
                if the set of cheapest NY and cheapest SF don't overlap then return sum(ny) + sum(sf);
                for the overlapping subproblems
                   1) you can use dynamic programming
                   2) ???? or maybe repeat steps for next optimal choice
 */

public class MinTravel {

    static int minCost( int[][] costs, int idx, int count, int cost1, int cost2, int total2 ){
        if ( count == costs.length/2 ) return cost1 + (total2 - cost2);
        if ( idx == costs.length ) return -1;
        int includeCost = minCost( costs, idx+1, count+1, cost1+costs[idx][0], cost2+costs[idx][1], total2 );
        int excludeCost = minCost( costs, idx+1, count, cost1, cost2, total2);
        if ( includeCost == -1 ) return excludeCost;
        if ( excludeCost == -1 ) return includeCost;
        return Math.min(includeCost,excludeCost);
    }

    static int minCost( int[][] costs ){
        int total2 = 0;
        for ( int[] cost : costs ) total2 += cost[1];
        return minCost( costs, 0, 0, 0, 0, total2 );
    }

    public static void main ( String[]  args){
        int[][] costs = {
                {600, 400},
                {300, 500},
                {200, 400},
                {300, 700}
        };
        System.out.println( minCost( costs) );
    }
}
