import java.util.*;
/*
https://leetcode.com/problems/connecting-cities-with-minimum-cost/
prim algo
Time:
   O(ElgV);

Space:
   O(V+E);
 */
public class ConnectingCitiesMinCost {

    static class Edge  {
        int dest;
        int weight;
        Edge(int dest, int weight){
            this.dest = dest;
            this.weight = weight;
        }
    }

    public int minimumCost(int N, int[][] connections) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
//        Queue<Edge> queue = new PriorityQueue<>( (a,b)-> a.weight-b.weight);
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        for ( int[] edge : connections ){
            graph.computeIfAbsent(edge[0], (key)->new ArrayList<>() ).add( new Edge(edge[1], edge[2]));
            graph.computeIfAbsent(edge[1], (key)->new ArrayList<>() ).add( new Edge(edge[0], edge[2]));
        }
        // short circuit
        if ( graph.size() != N  ) return -1;

        int total = 0;
        Set<Integer> visited = new HashSet<>();
        int src = graph.keySet().iterator().next();
        visited.add(src);
        queue.addAll(graph.getOrDefault(src, Collections.emptyList()));

        while( !queue.isEmpty() ){
            Edge edge = queue.remove();
            if ( visited.contains(edge.dest) ) continue;
            visited.add(edge.dest);
            total += edge.weight;
            // double check, saw a performance win
            for ( Edge neighbor : graph.getOrDefault(edge.dest, Collections.emptyList()) ){
                if ( !visited.contains(neighbor.dest) ) queue.add(neighbor);
            }
        }
        return (visited.size() == graph.size()) ? total : -1;
    }
}
