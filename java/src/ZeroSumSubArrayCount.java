import java.util.*;
import java.util.stream.Stream;

public class ZeroSumSubArrayCount {

    public static int find(int[] arr){
        Map<Integer,Integer> prefixSumMap = new HashMap<>();
        int count = 0;
        int currSum = 0;
        for( int num:  arr ){
            currSum += num;
            if( currSum == 0 ) ++count;
            if ( prefixSumMap.containsKey(currSum )  ) {
                int prefixSum = prefixSumMap.get(currSum);
                count += prefixSum;
                prefixSumMap.put(currSum, prefixSum+1 );
            }
            else {
                prefixSumMap.put(currSum, 1);
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}