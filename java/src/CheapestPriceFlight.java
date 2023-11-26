/*

https://leetcode.com/problems/cheapest-flights-within-k-stops/


here are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task
is to find the cheapest price from src to dst with up to k stops. If there is no such route,


Time:
   O(ElgV)

Space:
   O(V+E)
 */

import java.util.*;

public class CheapestPriceFlight {

    static class Edge {
        int dst;
        int price;

        Edge(int dst, int price ) {
            this.dst = dst;
            this.price = price;
        }
    }

    static class EdgeInfo {
        int dst;
        int cost;
        int stops;

        EdgeInfo(int dst, int stops, int cost ) {
            this.dst = dst;
            this.stops = stops;
            this.cost = cost;
        }
    }

    public int findCheapestPrice(int[][] flights, int src, int dst, int K) {
        Map<Integer,List<Edge>> g = new HashMap<>();
        Map<Integer,Integer> costMap = new HashMap<>();
        Map<Integer,Integer> stopMap = new HashMap<>();
        Queue<EdgeInfo> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        for ( int[] flight: flights ){
            g.computeIfAbsent( flight[0], (key)-> new ArrayList<>()).add(new Edge(flight[1],flight[2]));
        }
        costMap.put(src,0);
        stopMap.put(src,0);
        q.add( new EdgeInfo(src,0,0) );
        while (!q.isEmpty()) {

            EdgeInfo e = q.poll();
            int node = e.dst, stops = e.stops, cost = e.cost;

            // If destination is reached, return the cost to get here
            if (node == dst) {
                return cost;
            }

            // If there are no more steps left, continue
            if (stops == K + 1) {
                continue;
            }

            // Examine and relax all neighboring edges if possible
            for ( Edge nei : g.getOrDefault(e.dst,Collections.emptyList())) {
                int newCost = cost + nei.price;
                int oldCost = costMap.getOrDefault(nei.dst,Integer.MAX_VALUE);
                if ( newCost < oldCost ) {
                    q.offer( new EdgeInfo(nei.dst, stops+1, newCost) );
                    costMap.put(nei.dst,newCost);
                    stopMap.put(nei.dst,stops+1);
                } else if ( stops < stopMap.getOrDefault(nei.dst,Integer.MAX_VALUE)  ) {
                    q.offer( new EdgeInfo(nei.dst, stops+1, newCost) );
                }
            }
        }

        return costMap.getOrDefault(dst,-1);
    }

    public static void main(String[] args){
        CheapestPriceFlight solution = new CheapestPriceFlight();
//        int[][] edges = {{16,1,81},{15,13,47},{1,0,24},{5,10,21},{7,1,72},{0,4,88},{16,4,39},{9,3,25},{10,11,28},{13,8,93},{10,3,62},{14,0,38},{3,10,58},{3,12,46},{3,8,2},{10,16,27},{6,9,90},{14,8,6},{0,13,31},{6,4,65},
//                {14,17,29},{13,17,64},{12,5,26},{12,1,9},{12,15,79},{16,11,79},{16,15,17},{4,0,21},{15,10,75},{3,17,23},{8,5,55},{9,4,19},{0,10,83},{3,7,17},{0,12,31},{11,5,34},{17,14,98},{11,14,85},{16,7,48},{12,6,86},{5,17,72},{4,12,5},{12,10,23},{3,2,31},{12,7,5},{6,13,30},{6,7,88},{2,17,88},{6,8,98},{0,7,69},{10,15,13},{16,14,24},{1,17,24},{13,9,82},{13,6,67},{15,11,72},{12,0,83},{1,4,37},{12,9,36},{9,17,81},{9,15,62},{8,15,71},{10,12,25},{7,6,23},{16,5,76},{7,17,4},{3,11,82},{2,11,71},{8,4,11},{14,10,51},{8,10,51},{4,1,57},{6,16,68},{3,9,100},{1,14,26},{10,7,14},{8,17,24},{1,11,10},{2,9,85},{9,6,49},{11,4,95}};
//
//        int src = 7;
//        int dst = 2;
//        int K = 6;

//        int[][] edges = {{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}};
//        int src = 0;
//        int dst = 2;
//        int K = 2;

//        int[][] edges =  {{0,1,100},{1,2,100},{0,2,500}};
//        int src = 0;
//        int dst = 2;
//        int K = 1;

        int[][] edges =  {{11,12,74},{1,8,91},{4,6,13},{7,6,39},{5,12,8},{0,12,54},{8,4,32},{0,11,4},{4,0,91},{11,7,64},{6,3,88},{8,5,80},{11,10,91},{10,0,60},{8,7,92},{12,6,78},{6,2,8},{4,3,54},{3,11,76},{3,12,23},{11,6,79},{6,12,36},{2,11,100},{2,5,49},{7,0,17},{5,8,95},{3,9,98},{8,10,61},{2,12,38},{5,7,58},{9,4,37},{8,6,79},{9,0,1},{2,3,12},{7,10,7},{12,10,52},{7,2,68},{12,2,100},{6,9,53},{7,4,90},{0,5,43},{11,2,52},{11,8,50},{12,4,38},{7,9,94},{2,7,38},{3,7,88},{9,12,20},{12,0,26},{10,5,38},{12,8,50},{0,2,77},{11,0,13},{9,10,76},{2,6,67},{5,6,34},{9,7,62},{5,3,67}};
        int src = 10;
        int dst = 1;
        int K = 10;

        System.out.println(solution.findCheapestPrice(edges,src,dst,K));
    }

}
