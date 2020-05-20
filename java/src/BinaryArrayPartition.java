import java.util.Arrays;
import java.util.stream.Stream;

/*
  * Check if an array can be partition in 3 parts evenly
  *
  *  return [-1,-1] if it can not be partitioned
  *         else return the partition indices
  *  input:  "1,0,1,0,1"
  *  output: [0.2]
  *
  *
  *  approach count the ones and divide one evenly
 */
public class BinaryArrayPartition {

    public static int[] find(int[] arr ){
        int total = 0;
        for ( int num: arr ){
            if ( num == 1 ) ++total;
        }
        if ( total%3 != 0 ) return new int[]{-1,-1};
        if ( total == 0 ) return new int[]{0,1};
        int perPartition =  total/3;
        int p1 = -1;
        int count = 0;
        for ( int i = 0; i < arr.length; ++i){
            if ( arr[i] == 1 ) ++count;
            if ( count == perPartition ) {
                p1 = i;
                break;
            }
        }
        // should not happen
        if ( p1 == -1 ) return new int[]{-1,-1};

        count = 0;
        for ( int i = arr.length-1; i >= 0; --i){
            if ( arr[i] == 1 ) ++count;
            if ( count == perPartition ) {
                return new int[]{p1,i-1};
            }
        }
        return new int[]{-1,-1};
    }

    public static void main( String[] args){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(find(arr)));
    }

}