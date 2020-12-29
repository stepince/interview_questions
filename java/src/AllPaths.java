import java.util.*;

/*
Time:
    O(n*2^n)

Auxiliary Space:
    O(n*2^n)
 */
public class AllPaths {

    public static class Node {
        private final String value;
        private final List<Node> neighbors = new LinkedList<>();

        // Basic constructor
        public Node(String value) {
            this.value = value;
        }
    }

    void allPathsUtil(Node node, List<List<Node>> results, Set<String> visited, Deque<Node> path, String dst ){
        if ( node.value.equals(dst) ){
            results.add( new ArrayList<>(path) );
            return;
        }
        visited.add(node.value);
        for ( Node nei: node.neighbors ){
            if ( !visited.contains(nei.value)) {
                path.addLast(nei);
                allPathsUtil(nei, results, visited, path, dst);
                path.removeLast();
            }
        }
        visited.remove(node.value);
    }

    List<List<Node>> findAllPaths( Node n, String dst ){
        List<List<Node>> results = new ArrayList<>();
        Deque<Node> path = new ArrayDeque<>();
        path.add(n);
        allPathsUtil(n, results, new HashSet<>(), path, dst );
        return results;
    }

    public static void main(String[] args){

    }

}
