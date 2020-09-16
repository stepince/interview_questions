import java.util.*;

/*

Minimum height trees

A tree is an undirected graph in which any two vertices are connected by exactly
one path. In other words, any connected graph without simple cycles is a tree.

https://leetcode.com/problems/minimum-height-trees/


Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi]
indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose
any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among
all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class MinHeightTrees {


    // faster algo,
    // the root node is the center node
    // you get to the center by constantly removing the degrees of the nodes
    //https://leetcode.com/problems/minimum-height-trees/discuss/827284/c%2B%2B99-TC-with-explanation-this-is-pretty-much-a-bfs-top-sort
    //
    public static List<Integer> findMinHeightTrees1(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> degrees = new HashMap<>();

        List<Integer> results = new ArrayList<>();
        // at least one node "0" according to the problem
        if ( edges.length == 0 ) {
            results.add(0);
            return results;
        }
        for ( int[] edge : edges ) {
            graph.computeIfAbsent(edge[0], (key) -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], (key) -> new HashSet<>()).add(edge[0]);
            degrees.merge(edge[0],1,Integer::sum);
            degrees.merge(edge[1],1,Integer::sum);
        }
        Queue<Integer> queue = new LinkedList<>();
        for ( Integer node: degrees.keySet()){
            if ( degrees.get(node) == 1 ) queue.add(node);
        }
        while( !queue.isEmpty() ) {
            results.clear();
            for ( int size = queue.size(); size > 0; --size ){
                Integer node = queue.remove();
                results.add(node);
                for ( Integer neighbor : graph.getOrDefault(node, Collections.emptySet()) ){
                    if ( degrees.merge(neighbor,-1, Integer::sum ) == 1 ){
                        queue.add(neighbor);
                    }
                }
            }
        }
        return results;
    }

    static int dfs(Map<Integer, Set<Integer>> graph, Integer node, Set<Integer> visited, int height, int min){
        // exit early
        if ( height > min ) return height;
        visited.add(node);
        int max = height;
        for( Integer neighbor: graph.getOrDefault(node, Collections.emptySet()) ){
            if ( !visited.contains(neighbor) ){
                max = Math.max(max,dfs(graph, neighbor, visited, height + 1,min));
            }
        }
        return max;
    }

    public static List<Integer> findMinHeightTrees2(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        List<Integer> results = new ArrayList<>();
        // at least one node "0" according to the problem
        if ( edges.length == 0 ) {
            results.add(0);
            return results;
        }
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], (key) -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], (key) -> new HashSet<>()).add(edge[0]);
        }

        int min = Integer.MAX_VALUE;

        for( Integer node: graph.keySet() ){
            if ( graph.get(node).size() == 1 ) continue;
            int height = dfs(graph,node,new HashSet<>(), 0,min);
            if ( min == height ){
                results.add(node);
            }
            else if ( height < min  ){
                min = height;
                results.clear();
                results.add(node);
            }
        }
        return results;
    }

    public static void main(String[] args){
//        int[][] edges = {{1,0},{1,2},{1,3}};
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        System.out.println(  findMinHeightTrees1(edges) );
        System.out.println(  findMinHeightTrees2(edges) );
    }
}
