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
        int weight;
        int stops;
        int cost;
        Edge ( int dst, int weight ){
            this.dst = dst;
            this.weight = weight;
        }
        Edge ( int dst, int weight, int cost, int stops ){
            this.dst = dst;
            this.weight = weight;
            this.cost = cost;
            this.stops = stops;
        }
    }


    public int findCheapestPrice2(int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
//        Queue<Edge> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        for ( int[] edge: flights ) {
            graph.computeIfAbsent(edge[0], (key)->new ArrayList<>() ).add( new Edge(edge[1],edge[2]));
        }
        queue.add(new Edge(src,0));

        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            int node = e.dst, stops = e.stops, cost = e.cost;

            // If destination is reached, return the cost to get here
            if (node == dst) return cost;

            // If there are no more steps left, continue
            if (stops == K + 1) continue;

            for ( Edge neighbor : graph.getOrDefault(node,Collections.emptyList())) {
                // create a new Edge, !!!important
                queue.add(new Edge(neighbor.dst, neighbor.weight, cost + neighbor.weight, stops + 1));
            }
        }

        return -1;
    }

    public static void main(String[] args){
        CheapestPriceFlight solution = new CheapestPriceFlight();
        int[][] edges =
                {{3,4,4},{2,5,6},{4,7,10},{9,6,5},{7,4,4},{6,2,10},{6,8,6},{7,9,4},
                        {1,5,4},{1,0,4},{9,7,3},{7,0,5},{6,5,8},{1,7,6},{4,0,9},{5,9,1}};
        int src = 6;
        int dst = 0;
        int K = 9;



//        int[][] edges = {{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
//        int src = 0;
//        int dst = 3;
//        int K = 1;
//        System.out.println(solution.findCheapestPrice(edges,src,dst,K));
        System.out.println(solution.findCheapestPrice2(edges,src,dst,K));
    }

}
