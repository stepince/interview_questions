import java.util.*;
import java.util.stream.Collectors;

/*
    https://www.lintcode.com/problem/walls-and-gates/description
    -1 - wall
    0 - gate
    INF - empty room

    Transform the grid for each room the distance to the nearest gate
    use INF if it can reached

 */

public class WallAndGates {

    static int min( int a, int b, int c, int d){
        return Math.min(Math.min(a,b), Math.min(c,d));
    }

    static int dfsVisit( int[][] grid, int row, int col, Set<String> visited ){
        String key = row +":" + col;
        if ( row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited.contains(key) || grid[row][col] == -1) return INF;
        visited.add( key );
        if ( grid[row][col] != INF ) {
            visited.remove( key );
            return grid[row][col];
        }
        int val = min( dfsVisit(grid,row-1,col,visited),
                    dfsVisit(grid,row+1,col,visited),
                    dfsVisit(grid,row,col+1,visited),
                    dfsVisit(grid,row,col-1,visited)
                    );
        visited.remove( key );
        return val == INF ? INF : val + 1;
    }

    public static final Integer INF = Integer.MAX_VALUE;
    static void find(int[][] grid){
        for ( int i = 0; i < grid.length; ++i ){
            for ( int j = 0; j < grid[0].length; ++j ){
                if ( grid[i][j] == INF ){
                    grid[i][j] = dfsVisit(grid, i,j, new HashSet<>());
                }
            }
        }
    }

    // just a helper function
    public static List<List<Integer>> toList( int[][] matrix ){
        List<List<Integer>> l = new ArrayList<>();
        for ( int[] row: matrix ){
            l.add( Arrays.stream(row).boxed().collect(Collectors.toList()));
        }
        return l;
    }

    public static void main(String[] args){
//        int[][] grid = {
//                {INF,-1,0,INF},
//                {INF,INF,INF,-1},
//                {INF,-1,INF,-1},
//                {0,-1,INF,INF},
//        };

        int[][] grid = {
                {INF,INF,0,INF},
                {INF,INF,INF,INF},
                {INF,INF,INF,INF},
                {INF,0,INF,INF},
        };


        find(grid);
        System.out.println(toList(grid));
    }
}
