import java.util.*;
/*
https://leetcode.com/problems/connecting-cities-with-minimum-cost/

minimum spanning tree ( mst )
prim algo
Time:
   O(ElgV);

Space:
   O(V+E);
 */
public class ConnectingCitiesMinCost {

    static class Edge  {
        int dst;
        int wgt;
        Edge(int dst, int wgt){
            this.dst = dst;
            this.wgt = wgt;
        }
    }

    public int minimumCost(int N, int[][] connections) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
//        Queue<Edge> queue = new PriorityQueue<>( (a,b)-> a.weight-b.weight);
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.wgt));
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
            Edge edge = queue.poll();
            if ( visited.contains(edge.dst) ) continue;
            visited.add(edge.dst);
            total += edge.wgt;
            // double check, saw a performance win
            for ( Edge nei : graph.getOrDefault(edge.dst, Collections.emptyList()) ){
                if ( !visited.contains(nei.dst) ) queue.add(nei);
            }
        }
        return (visited.size() == graph.size()) ? total : -1;
    }
}
