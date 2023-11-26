import java.util.*;

/*
https://leetcode.com/problems/all-paths-from-source-to-target/

Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1,
and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.
graph[i] is a list of all nodes j for which the edge (i, j) exists.

weirdly worded question src = 0, dst = graph.length -1,  graph[i] is a a list of all the nodes it is connected
Time:
O(N 2^N)

Space
O(N 2^N)
 */
public class AllPathsDag {
    static void allPathsUtil(int[][] graph, Integer src,Deque<Integer> l, List<List<Integer>> results) {
        if(src.equals(graph.length-1)) {
           results.add(new ArrayList<>(l));
           return;
        }
        for (int nei: graph[src])  {
            l.add(nei);
            allPathsUtil(graph,nei,l,results);
            l.removeLast();
        }
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> results = new ArrayList<>();
        allPathsUtil(graph, 0,new ArrayDeque<>(List.of(0)), results);
        return results;
    }

    public static void main(String[] args){
         int[][] graph = {{1,2}, {3}, {3}, {}};
         System.out.println(allPathsSourceTarget(graph));
    }
}
