import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 *
 * average 0(n)
 * worst case O(n^2)
 *
 * note:  this is using quick select
 *        this is not using Hoare implementation of quick select
 *        most implementations of quick select use Hoare with 2 indices
 *
 */

public class KSmallest {

     private static void swap( int[] arr, int a, int b ){
          if ( a != b ) {
               int temp = arr[a];
               arr[a] = arr[b];
               arr[b] = temp;
          }
     }

     private static int partition(int[] arr, int begin, int end){
          int pivotIdx = begin;
          int pivot = arr[end];
          for( int i = begin; i < end; ++i ){
               if ( arr[pivotIdx] < pivot ){
                    swap(arr, i, pivotIdx++);
               }
          }
          swap(arr, pivotIdx, end);
          return pivotIdx;
     }

     private static int quickSelect(int[] arr, int k, int begin, int end){
          int pivotIdx = partition(arr,begin,end);
          if ( pivotIdx == k ) return arr[k];
          // should never happen
          //if ( begin >= end ) return arr[k];
          return (k < pivotIdx ) ? quickSelect(arr, k, begin, pivotIdx-1) : quickSelect(arr,  k, pivotIdx+1, end );
     }

     public static int find(int[] arr, int k ){
          return  quickSelect(arr, k, 0, arr.length-1);
     }

     public static void main(String[] args){
          int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
          int k = Integer.parseInt(args[1]);
          arr = Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).mapToInt(x->x).toArray();
          System.out.println(find(arr,k-1));
     }
}