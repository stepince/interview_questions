import java.util.*;
import java.util.stream.Stream;

public class ZeroSumSubArraysPrint {

    private static List<Integer> getSubArray(int[] arr, int begin, int end){
        List<Integer> l = new ArrayList<>();
        for ( int i = begin; i < end; ++i ){
            l.add(arr[i]);
        }
        return l;
    }

    public static void find( int[] arr ){
        Map<Integer,List<Integer>> prefixMap = new HashMap<>();
        int accSum = 0;
        for ( int i = 0; i < arr.length; ++i ){
            accSum += arr[i];
            if ( accSum == 0 ) {
                System.out.println(getSubArray(arr, 0, i+1));
            }
            else if ( prefixMap.containsKey(accSum) ){
                for ( Integer begin: prefixMap.get(accSum) ){
                    System.out.println(getSubArray(arr,begin,i+1));
                }
            }
            else {
                prefixMap.put(accSum, Collections.singletonList(i));
            }
        }
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        find(arr);
    }
}