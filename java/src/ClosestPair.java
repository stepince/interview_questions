import java.util.Arrays;
import java.util.List;
/* *
   find the pair of number closest to a value;

   input:  "2,44,6,7,8,9"  50
   output: 44,6


   Notes:

 */
public class ClosestPair {

     public static List<Integer> find(int[] arr, final int total){
          Arrays.sort(arr);
          int l = 0, r = arr.length-1, left = 0, right = arr.length-1, currentDiff = Integer.MAX_VALUE;
          while ( l < r ) {
              int pairTotal = arr[l] + arr[r];
              // found
              if ( pairTotal == total ) return Arrays.asList(arr[l],arr[r]);

              int diff = Math.abs(total - pairTotal);
              if ( diff < currentDiff ){
                  left = l;
                  right = r;
                  currentDiff = diff;
              }
              if ( pairTotal > total ){
                  --r;            
              }
              else{
                  ++l;
              }
          }
          return Arrays.asList(arr[left],arr[right]);
     }

     static public void main(String[] args) {
//        int[] arr =  Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int total = Integer.parseInt(args[1]);

         int[] arr = {2,44,6,7,8,9};
         int total = 49;
         System.out.println(find(arr,total));
     }
}