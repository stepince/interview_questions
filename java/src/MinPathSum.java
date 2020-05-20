import java.util.*;

/*
*
* algo: shortest weighted path
  find:  O(4^N)
  find2:  O(V+E)
  *
  * init visited
  * init distance
  * queue = new priorityQueue()
  * queue.add(src)
  * visited.add(src)
  * distance(src) = 0
  * while(!empty) {
  *    node =  queue.remove();
  *    loop adj = node.adj ;
  *    if( adj != visited ) {
  *         new_distance =  distance(node) + adj.weight;
  *         if ( new_distance < distance(adj)  ){
  *              queue.remove(adj)
  *              distance(adj) = new_distance
  *              queue.add(adj);
  *         }
  *    }
  *    visited.add(adj)
  * }
  * return distance(dest)
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
        public int hashCode(){
            return toString().hashCode();
        }
        public boolean equals( Object o ){
            if ( o == this ) return true;
            if ( !(o instanceof Node)) return false;
            return toString().equals(o.toString());
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
    // It is using Bellman-Ford
    public static int find2(int[][] matrix) {
        if ( matrix.length == 0 || matrix[0].length == 0 ) return 0;
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] distances = new int[rows][columns];
        boolean[][] visited = new boolean[rows][columns];
        for ( int[]a: distances ) Arrays.fill(a,Integer.MAX_VALUE);
        //Queue<Node> queue = new PriorityQueue<>(a,b)-> distances[a.row][a.column] - distances[b.row][b.column] ));
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> distances[a.row][a.column]));
        queue.add( new Node(0,0) );

        // this would normally be zero
        distances[0][0] = matrix[0][0];
        visited[0][0] = true;
        while( !queue.isEmpty() ){
            Node node = queue.remove();
            int distance = distances[node.row][node.column];
            List<Node> adj = Arrays.asList(
                    new Node(node.row,node.column-1),
                    new Node(node.row,node.column+1),
                    new Node(node.row-1,node.column),
                    new Node(node.row+1,node.column) );

            for( Node v: adj ){
                if ( v.row < 0 || v.column < 0 || v.row >= rows || v.column >= columns || visited[v.row][v.column] ) continue;
                int newDistance = distance + matrix[v.row][v.column];
                if ( newDistance < distances[v.row][v.column] ){
                    queue.remove(v);
                    distances[v.row][v.column]=newDistance;
                    queue.add(v);
                }
                visited[node.row][node.column] = true;
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
        System.out.println(find(matrix));

        int[][] matrix2 = new int[][] {
                {1,1,1,1,9,9,9},
                {9,9,9,1,9,9,9},
                {1,1,1,1,9,9,9},
                {1,9,9,9,9,9,9},
                {1,1,1,1,1,1,1}
        };
        //System.out.println(find(matrix2));
        System.out.println(find2(matrix2));
    }
}
