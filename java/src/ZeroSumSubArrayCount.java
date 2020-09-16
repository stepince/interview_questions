import java.util.*;

public class ZeroSumSubArrayCount {

    public static int find(int[] arr){
        Map<Integer,Integer> prefixSumMap = new HashMap<>();
        int count = 0;
        int currSum = 0;
        for( int num:  arr ){
            currSum += num;
            if( currSum == 0 ) ++count;
            count += prefixSumMap.getOrDefault(currSum,0);
            prefixSumMap.merge(currSum, 1,Integer::sum);
        }
        return count;
    }

    public static void main(String[] args){
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] arr = {1,5,-5,0};
        System.out.println(find(arr));
    }



}