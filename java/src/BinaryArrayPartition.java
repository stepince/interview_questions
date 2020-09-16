import java.util.Arrays;

/*
  * Check if an array can be partition in 3 parts evenly
  *
  *  return [-1,-1] if it can not be partitioned
  *         else return the partition indices
  *  input:  "1,0,0,1,0,1"
  *  output: [0.4]
  *
  *
  *  approach count the ones and divide one evenly
  *
  * https://www.geeksforgeeks.org/divide-binary-array-into-three-equal-parts-with-same-value/
  *
  *
  Input : A = [1, 1, 1, 1, 1, 1]
Output : [1, 4]
All three parts are,
A[0] to A[1] first part,
A[2] to A[3] second part,
A[4] to A[5] third part.

Input : A = [1, 0, 0, 1, 0, 1]
Output : [0, 4]
  *
  *
  *
 */
public class BinaryArrayPartition {

    public static int[] find(int[] nums ){
        int total = 0;
        for ( int num: nums ){
            if ( num == 1 ) ++total;
        }
        if ( total%3 != 0 ) return new int[]{-1,-1};
        if ( total == 0 ) return new int[]{0,1};
        int perPartition =  total/3;
        int p1 = -1;
        for ( int i = 0, count = 0; count < perPartition && i < nums.length; ++i){
            if ( nums[i] == 1 && ++count == perPartition ) {
                p1 = i;
            }
        }

        for ( int i = nums.length-1, count = 0; i >= 0; --i){
            if ( nums[i] == 1 && ++count == perPartition ) {
                return new int[]{p1,i};
            }
        }
        return new int[]{-1,-1};
    }

    public static void main( String[] args){
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int[] nums = {1,0,1,0,1,0};

        int[] nums =  {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(find(nums)));
    }

}