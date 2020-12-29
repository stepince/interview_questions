/*

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach
the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

https://leetcode.com/problems/unique-paths-ii/submissions/

Time:
  O(m*n)

Space:
  O(m*n)
   Note: You could use the array itself as the mem
   Solution: would to turn the block to Integer.MAX_VALUE or Integer.MIN_VALUE
 */
public class UniquePathsMatrix {
    static int allPaths( int[][] obstacleGrid, int row, int column, Integer[][] mem){
        if ( obstacleGrid.length == row || obstacleGrid[0].length == column || obstacleGrid[row][column] == 1 ){
            return 0;
        }
        if ( obstacleGrid.length-1 == row && obstacleGrid[0].length-1 == column ){
            return 1;
        }
        if ( mem[row][column] != null ) return mem[row][column];
        return mem[row][column] = allPaths( obstacleGrid, row, column+1,mem ) + allPaths( obstacleGrid, row+1, column,mem );
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return allPaths( obstacleGrid, 0, 0, new Integer[obstacleGrid.length+1][obstacleGrid[0].length+1] );
    }

    public static void main(String[] args){
        int[][] matrix = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        System.out.println(uniquePathsWithObstacles(matrix));
    }
}
