import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*

Given a n*n matrix where all numbers are distinct, 
find the maximum length path (starting from any cell)
such that all cells along the path are in increasing order with a difference of 1.

We can move in 4 directions from a given cell (i, j),
 i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or 
(i, j-1) with the condition that the adjacent cells have a difference of 1.


Input:  mat[][] = {{1, 2, 9}
                   {5, 3, 8}
                   {4, 6, 7}}
Output: 4
The longest path is 6-7-8-9. 
"1 2 9" "5 3 9" "4 6 7"
*/
public class LongestMatrixPath {

   private static int findLongestPath(int[][] matrix,int x, int y, int prev, int total, Set<String> visited) {
       String key = x + ":" + y;
       if ( visited.contains(key) ) return total;
       visited.add(key);

       if ( x >= matrix.length ) return total;
       if ( x < 0 ) return total;
       if ( y >= matrix[0].length ) return total;
       if ( y < 0 ) return total;

       if ( prev >= matrix[x][y] ) return total;
       // prev==0 starting point, so don't increase
       if ( prev != 0 ) ++total;

       int left = findLongestPath(matrix,x-1,y,matrix[x][y], total, visited);
       int right = findLongestPath(matrix,x+1,y,matrix[x][y], total, visited);
       int up = findLongestPath(matrix,x,y+1,matrix[x][y], total, visited);
       int down = findLongestPath(matrix,x,y-1,matrix[x][y], total, visited);

       total = Math.max(total,left);
       total = Math.max(total,right);
       total = Math.max(total,up);
       total = Math.max(total,down);

      return total;
   }


    public static int find(int[][] matrix) {
        int max = 0;
        for ( int i = 0;i <matrix.length;++i){
            for ( int j = 0;j < matrix[0].length;++j){
                max = Math.max(max,findLongestPath(matrix,i,j,0,0,new HashSet<>()));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[args.length][];
        for ( int i = 0; i < args.length; ++i ){
            matrix[i] = Arrays.stream(args[i].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(find(matrix));
    }

}