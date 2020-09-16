import java.util.*;

/*
https://leetcode.com/problems/all-paths-from-source-to-target/

Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1,
and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.
graph[i] is a list of all nodes j for which the edge (i, j) exists.

 */
public class AllPathsGraph {

    static void findAllPaths(int[][] graph, List<List<Integer>> results, Set<Integer> visited, Deque<Integer> path, int src, int dest ){

        if ( src == dest ){
            results.add(new ArrayList<>(path));
            return;
        }
        visited.add(src);
        for ( int num: graph[src] ) {
            if ( !visited.contains(num) ){
                path.add(num);
                findAllPaths(graph,results,visited,path,num,dest);
                path.removeLast();
            }
        }
        visited.remove(src);
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> results = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> path = new LinkedList<>();
        path.add(0);
        findAllPaths(graph,results,visited,path,0,graph.length-1 );
        return results;
    }

    public static void main(String[] args){
         int[][] graph = {{1,2}, {3}, {3}, {}};
         System.out.println(allPathsSourceTarget(graph));
    }
}
