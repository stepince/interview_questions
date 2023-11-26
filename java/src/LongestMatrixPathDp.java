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
public class LongestMatrixPathDp {

    static int max( int a, int b, int c, int d){
        return Math.max(Math.max(a,b), Math.max(c,d));
    }

    private static int findLongestPath(int[][] matrix, int x, int y, int prev, boolean[][] visited, Integer[][] mem ) {

        if ( x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || ( prev != Integer.MIN_VALUE && Math.abs(prev - matrix[x][y]) != 1) || visited[x][y] ) return 0;
        if ( mem[x][y] != null ) return mem[x][y];
        visited[x][y] = true;
        int left = findLongestPath(matrix,x-1,y, matrix[x][y], visited, mem);
        int right = findLongestPath(matrix,x+1,y, matrix[x][y], visited, mem);
        int up = findLongestPath(matrix,x,y+1, matrix[x][y], visited, mem);
        int down = findLongestPath(matrix,x,y-1, matrix[x][y], visited, mem);
        int total = 1 + max(left,right,up,down);
        visited[x][y] = false;
        return mem[x][y] = total;
    }

    public static int find(int[][] matrix) {
        int max = 0;
        for ( int i = 0;i <matrix.length;++i){
            for ( int j = 0;j < matrix[0].length;++j){
                max = Math.max(max, findLongestPath(matrix,i,j, Integer.MIN_VALUE, new boolean[matrix.length][matrix[0].length] , new Integer[matrix.length][matrix[0].length] ));
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[args.length][];
//        fo.r ( int i = 0; i < args.length; ++i ){
//            matrix[i] = Arrays.stream(args[i].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        }
        int[][] matrix =
                {{1, 2, 9},
                {5, 3, 8},
                {4, 6, 7}};
        System.out.println(find(matrix));
    }

}