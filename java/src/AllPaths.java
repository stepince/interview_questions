import java.util.*;

/*



Time:
    O(2^n*n!)

Auxiliary Space:
    O(2^n*n!)
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

    void dfs(Node node, List<List<Node>> results, Set<String> visited, Deque<Node> path, String dest ){
        if ( node.value.equals(dest) ){
            results.add( new ArrayList<>(path) );
            return;
        }
        visited.add(node.value);
        for ( Node n: node.neighbors ){
            if ( !visited.contains(n.value)) {
                path.add(n);
                dfs(n, results, visited, path, dest);
                path.removeLast();
            }
        }
        visited.remove(node.value);
    }

    List<List<Node>> findAllPaths( Node n, String dest ){
        List<List<Node>> results = new ArrayList<>();
        Deque<Node> path = new LinkedList<>();
        path.add(n);
        dfs(n, results, new HashSet<>(), path, dest );
        return results;
    }

    public static void main(String[] args){

    }

}
