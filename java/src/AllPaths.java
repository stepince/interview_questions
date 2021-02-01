import java.util.*;

/*
Time:
    O(n*2^n)

Auxiliary Space:
    O(n*2^n)

    note: visited set is needed if it is not an DAG
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

    void allPathsUtil(Node node, String dst, Set<String> visited, List<List<Node>> paths, Deque<Node> path ){
        if ( node.value.equals(dst) ){
            paths.add( new ArrayList<>(path) );
            return;
        }
        for ( Node nei: node.neighbors ){
            if ( !visited.contains(nei.value)) {
                visited.add(node.value);
                // addLast
                path.add(nei);
                allPathsUtil(nei, dst, visited, paths, path);
                path.removeLast();
                visited.remove(node.value);
            }
        }
    }

    List<List<Node>> findAllPaths( Node n, String dst ){
        List<List<Node>> paths = new ArrayList<>();
        Deque<Node> path = new ArrayDeque<>();
        path.add(n);
        allPathsUtil(n, dst, new HashSet<>(), paths, path );
        return paths;
    }

    public static void main(String[] args){

    }

}
