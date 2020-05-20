
/*
  rotate a matrix counter clockwise 90 in O(1) space
  Time:
     O(N*M)
  Space
     O(1)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixRotate {

    static void swap(int[][] matrix, int a1, int b1, int a2, int b2){
        int temp = matrix[a1][b1];
        matrix[a1][b1] = matrix[a2][b2];
        matrix[a2][b2] = temp;
    }

    public static void rotate90(int[][] matrix){
        for ( int i = 0; i < matrix.length; i++){
            for ( int j = 0; j < matrix.length; j++){
                if ( i < j ) {
                    swap(matrix, i, j, j, i);
                }
            }
        }
        // reverse the row( column value)
        for ( int i = 0; i < matrix.length; i++){
            for ( int j = 0; j < matrix.length/2; j++){
                swap(matrix, i, j, i, matrix.length-j-1);
            }
        }
    }

    public static void rotate90Counter(int[][] matrix){
        for ( int i = 0; i < matrix.length; i++){
            for ( int j = 0; j < matrix.length; j++){
                if ( i < j ) {
                    swap(matrix, i, j, j, i);
                }
            }
        }

        //reverse rows, (row value)
        for ( int i = 0; i < matrix.length/2; i++){
            for ( int j = 0; j < matrix.length; j++){
                swap(matrix, i, j,matrix.length-i-1, j);
            }
        }
    }

    public static List<List<Integer>> toList(int[][] matrix ){
        List<List<Integer>> l = new ArrayList<>();
        for ( int[] row: matrix){
            l.add( Arrays.stream(row).boxed().collect(Collectors.toList()));
        }
        return l;
    }

    public static void main(String[] args){
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        //rotate90(matrix);
        rotate90Counter(matrix);
        System.out.println(toList(matrix));
    }
}
