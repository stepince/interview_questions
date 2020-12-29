import java.util.Arrays;

/*
 *
 *     count the number of islands in a matrix
 *     eight different directions
 *
 *     O(N*M)
 *
 * https://leetcode.com/problems/number-of-islands/
 */
public class IslandMatrix {

    private static boolean isIsland(int[][] matrix, int row, int column ) {
        if ( row < 0 || column < 0 || row >= matrix.length || column >= matrix[row].length || matrix[row][column] == 0 ) {
            return false;
        }
        matrix[row][column] = 0;
        // eight directions
        isIsland(matrix, row-1,column );
        isIsland(matrix, row+1,column );
        isIsland(matrix, row-1,column-1 );
        isIsland(matrix, row+1,column-1 );
        isIsland(matrix, row-1,column+1 );
        isIsland(matrix, row+1,column+1 );
        isIsland(matrix,row,column-1 );
        isIsland(matrix,row,column+1 );
        return true;
    }

    public static int find(int[][] matrix){
        int count = 0;
        for ( int row = 0; row < matrix.length; ++row ){
            for ( int column = 0; column < matrix[row].length; ++column ){
                if ( isIsland(matrix,row,column) )  ++count;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[][] matrix = new int[args.length][];
        for( int i = 0; i < args.length; ++i){
            matrix[i] = Arrays.stream(args[i].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        }       
        System.out.println(find(matrix));
    }
}