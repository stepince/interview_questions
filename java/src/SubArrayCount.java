import java.util.HashMap;
import java.util.Map;
//import java.util.stream.Stream;

/*
*  number of subarrays that equal sum
*
* input:  array: 1, 2, 4   sum:2
* ouput: 1
*
* input array "1,20,20,-10,-10,20,3"  sum: 40
* output: 2
*
*
*   https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubArrayCount {

    public static int find(int[] arr, int sum){
        int accSum = 0;
        Map<Integer,Integer> prefixMap = new HashMap<>();
        int count = 0;
        for( int num : arr ){
            accSum += num;
            if ( accSum == sum ) ++count;
            count += prefixMap.getOrDefault(accSum - sum,0 );
            //increment
            prefixMap.merge(accSum,1,Integer::sum);
        }
        return count;
    }

    public static void main(String[] args){
//        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
//        int sum = Integer.parseInt(args[1]);
        int[] arr = {1,20,20,-10,-10,20,3};
        int sum = 40;
        System.out.println(find(arr,sum));
    }
}