/*
Longest Path in a Directed Acyclic Graph

https://www.geeksforgeeks.org/find-longest-path-directed-acyclic-graph/

Time:
   O(V+E)

Space:
   O(V);
 */


import java.util.*;

public class LongestWeightedPath {

    static class Edge {
        int src;
        int dst;
        int wgt;
        Edge( int src, int dst, int wgt ){
            this.src=src;
            this.dst=dst;
            this.wgt=wgt;
        }
    }

    private Map<Integer,List<Edge>> graph = new HashMap<>();

    public void addEdge( Edge edge ){
        graph.computeIfAbsent(edge.src, (key)->new ArrayList<>()).add(edge);
    }

    void topSortUtil(Integer node, Set<Integer> visited, Deque<Integer> results ){
        for ( Edge edge: graph.getOrDefault(node,Collections.emptyList()) ){
            if ( ! visited.contains(edge.dst) ) {
                visited.add(node);
                topSortUtil(edge.dst, visited, results);
            }
        }
        results.addFirst(node);
    }

    // just nodes that can be reached from src
    public List<Integer> topSort( int src ){
        LinkedList<Integer> results = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        topSortUtil(src, visited, results);
        return results;
    }

    public int findLongest(int src){
    // int distance[]
    // topSort graph
    // for all nodes
    // if ( dist[v] < dist[u] + weight(u,v) )
    //    dist[v] = dist[u] + weight(u,v)
    // max(dest);
        int max = 0;
        Map<Integer,Integer> distMap = new HashMap<>();
        distMap.put(src,0);
        for ( Integer node : topSort(src) ){
            Integer dist = distMap.getOrDefault(node,Integer.MIN_VALUE);
            if ( dist !=  Integer.MIN_VALUE ) {
                for (Edge e : graph.getOrDefault(node, Collections.emptyList())) {
                    int newDist = dist + e.wgt;
                    int curDist = distMap.getOrDefault(e.dst, Integer.MIN_VALUE);
                    if ( newDist > curDist ) {
                        max = Math.max(max, distMap.merge(e.dst, newDist, (prev, curr) -> curr));
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args){
        LongestWeightedPath graph = new LongestWeightedPath();
        int[][] edges = {
                { 0, 1, 5 },
                { 0, 2, 3 },
                { 1, 3, 6 },
                { 1, 2, 2 },
                { 2, 4, 4 },
                { 2, 5, 2 },
                { 2, 3, 7 },
                { 3, 5, 1 },
                { 3, 4, -1 },
                { 4, 5, -2 }
        };
        for( int[] edge: edges ){
            graph.addEdge( new Edge(edge[0],edge[1],edge[2]) );
        }
        int src = 1;
        System.out.println(graph.findLongest(src));
    }
}
