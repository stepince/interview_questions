import java.util.*;

/*
https://leetcode.com/problems/all-paths-from-source-to-target/

Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1,
and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.
graph[i] is a list of all nodes j for which the edge (i, j) exists.

Time:
O(N 2^N)

Space
O(N 2^N)
 */
public class AllPathsGraph {

    static void findAllPaths(int[][] graph, List<List<Integer>> results, Deque<Integer> path, int src, int dst ){
        if ( src == dst ){
            results.add(new ArrayList<>(path));
            return;
        }
        for ( int nei: graph[src] ) {
            path.addLast(nei);
            findAllPaths(graph,results,path,nei,dst);
            path.removeLast();
        }
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> results = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        path.add(0);
        findAllPaths(graph,results,path,0,graph.length-1 );
        return results;
    }

    public static void main(String[] args){
         int[][] graph = {{1,2}, {3}, {3}, {}};
         System.out.println(allPathsSourceTarget(graph));
    }
}
