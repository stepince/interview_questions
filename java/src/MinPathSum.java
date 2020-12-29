import java.util.*;

/*
*
* algo: shortest weighted path
  find:  O(4^n)
  find2: O(e lgv)
  *
  *
  *  void relax(u, v) {
  *     if (u.dist + w(u,v) < v.dist) {
  *         v.dist = u.dist + w(u,v);
  *        // predecessor is only needed to get the path
  *        v.pred = u;
  *      }
  *  }
  *
  * void dijkstra(s) {
  *     queue = new PriorityQueue<Vertex>();
  *     for (each vertex v) {
  *         v.dist = infinity;  // can use Integer.MAX_VALUE or Double.POSITIVE_INFINITY
  *         queue.add(v);
  *         v.pred = null;
  *     }
  *     s.dist = 0;
  *
  *     while (!queue.isEmpty()) {
  *         u = queue.remove();
  *         for (each vertex v adjacent to u)
  *            relax(u, v);
  *     }
  *}
 */
public class MinPathSum {

    public static class Node {
        final int row;
        final int column;
        Node(int row, int column){
            this.row = row;
            this.column = column;
        }
        public String toString(){
            return this.row + ":" + this.column;
        }
    }

    public static int findUtil(int[][] matrix, int row, int col, int total, Set<String> visited) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || total == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        String key = row + ":" + col;
        if (visited.contains(key)) return Integer.MAX_VALUE;

        total += matrix[row][col];
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            return total;
        }
        visited.add(key);
        int newTotal = findUtil(matrix, row, col - 1, total, visited);
        newTotal = Math.min(newTotal, findUtil(matrix, row, col + 1, total, visited));
        newTotal = Math.min(newTotal, findUtil(matrix, row + 1, col, total, visited));
        newTotal = Math.min(newTotal, findUtil(matrix, row - 1, col, total, visited));
        visited.remove(key);
        //System.out.println(key + "->" + newTotal);
        return newTotal;
    }

    // find2 is more efficient than the dynamic programming
    // It is using Dijkstra
    public static int find2(int[][] matrix) {
        if ( matrix.length == 0 || matrix[0].length == 0 ) return 0;
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] distances = new int[rows][columns];
        for ( int[]a: distances ) Arrays.fill(a,Integer.MAX_VALUE);
        //Queue<Node> queue = new PriorityQueue<>(a,b)-> distances[a.row][a.column] - distances[b.row][b.column] ));
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> distances[a.row][a.column]));
        queue.add( new Node(0,0) );

        // this would normally be zero
        distances[0][0] = matrix[0][0];
        while( !queue.isEmpty() ){
            Node node = queue.remove();
            int distance = distances[node.row][node.column];
            List<Node> adj = Arrays.asList(
                    new Node(node.row,node.column-1),
                    new Node(node.row,node.column+1),
                    new Node(node.row-1,node.column),
                    new Node(node.row+1,node.column) );

            for( Node v: adj ){
                if ( v.row < 0 || v.column < 0 || v.row >= rows || v.column >= columns ) continue;
                int newDistance = distance + matrix[v.row][v.column];
                if ( newDistance < distances[v.row][v.column] ){
                    queue.remove(v);
                    distances[v.row][v.column]=newDistance;
                    queue.add(v);
                }
            }
        }
        return distances[rows-1][columns-1];
    }

    public static int find(int[][] matrix){
        return findUtil(matrix,0,0,0, new HashSet<>());
    }

    public static void main(String[] args){
        int[][] matrix = new int[][] {
                {1,3,1},
                {1,5,1},
                {4,2,1} };
        //System.out.println(find(matrix));

        int[][] matrix2 = new int[][] {
                {1,1,1,1,9,9,9},
                {9,9,9,1,9,9,9},
                {1,1,1,1,9,9,9},
                {1,9,9,9,9,9,9},
                {1,1,1,1,1,1,1}
        };
        System.out.println(find(matrix));
        System.out.println(find2(matrix2));
    }
}
