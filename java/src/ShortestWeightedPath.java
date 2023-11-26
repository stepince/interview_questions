import java.util.*;
/*
 single source shortest weighted path

 dijkstra algo
Time:
   O(ElgV)

Space:
   O(V+E)
 */
public class ShortestWeightedPath {
    static class Edge {
        int dst;
        int wgt;
        Edge(int dst,int wgt){
            this.dst = dst;
            this.wgt= wgt;
        }
    }
    Map<Integer, List<Edge>> graph = new HashMap<>();

    int shortestWeightedPath(int src, int dst){

        Map<Integer,Integer> distMap = new HashMap<>();
//        Map<Integer,Integer> predMap = new HashMap<>();
//        Queue<Edge> q = new PriorityQueue<>( (a, b) -> distMap.get(a) - distMap.get(b) );
        Queue<Integer> q = new PriorityQueue<>(Comparator.comparingInt(distMap::get));
        distMap.put(src,0);
        q.add( src);

        while( !q.isEmpty() ){
            Integer node = q.poll();
            int dist = distMap.get(node);
            for ( Edge nei: graph.getOrDefault(node,Collections.emptyList()) ) {
                int curDist = distMap.getOrDefault(nei.dst,Integer.MAX_VALUE);
                int newDist = dist + nei.wgt;
                if ( newDist < curDist ) {
                    distMap.put( nei.dst, newDist );
//                    predMap.put ( nei.dst, node );
                    q.add( nei.dst );
                }
            }
        }

        return distMap.getOrDefault(dst,-1);
    }

    void buildGraph(int[][] edges ){
        for ( int[] edge : edges ){
            graph.computeIfAbsent(edge[0], key->new ArrayList<>() ) .add(new Edge(edge[1],edge[2]) );
        }
    }

    public static void main(String[] args){
        ShortestWeightedPath solution = new ShortestWeightedPath();
        int[][] edges =
                {{3,4,4},{2,5,6},{4,7,10},{9,6,5},{7,4,4},{6,2,10},{6,8,6},{7,9,4},
                        {1,5,4},{1,0,4},{9,7,3},{7,0,5},{6,5,8},{1,7,6},{4,0,9},{5,9,1}};
        int src = 6;
        int dst = 0;

//        int[][] edges = {{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
//        int src = 0;
//        int dst = 3;
        solution.buildGraph(edges);
//        System.out.println(solution.findCheapestPrice(edges,src,dst,K));
        System.out.println(solution.shortestWeightedPath(src,dst));
    }
}
