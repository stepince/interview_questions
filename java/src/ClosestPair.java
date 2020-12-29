import java.util.Arrays;
import java.util.List;
/* *
   find the pair of number closest to a value;

   input:  "2,44,6,7,8,9"  50
   output: 44,6


   Notes:

 */
public class ClosestPair {

     public static List<Integer> find(int[] nums, final int total){
          Arrays.sort(nums);
          int l = 0, r = nums.length-1, left = 0, right = nums.length-1, currentDiff = Integer.MAX_VALUE;
          while ( l < r ) {
              int pairTotal = nums[l] + nums[r];
              int diff = Math.abs(total-pairTotal);
              if ( diff < currentDiff  ){
                  currentDiff=diff;
                  left=l;
                  right=r;
              }

              // found
              if ( pairTotal == total ) {
                  break;
              }
              else if ( pairTotal < total ){
                  ++l;
              }
              else{
                  --r;
              }
          }
          return Arrays.asList(nums[left],nums[right]);
     }

     static public void main(String[] args) {
//        int[] nums =  Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int total = Integer.parseInt(args[1]);

         int[] nums = {2,44,6,7,8,9};
         int total = 49;
         System.out.println(find(nums,total));
     }
}