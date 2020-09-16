import java.util.*;

public class LongestSubArrayBySum {

    static int[] find(int[] arr, int sum){
        Map<Integer,Integer> prefixMap = new HashMap<>();
        int total = 0;
        int to = 0;
        int from = 0;
        int max = 0;
        for ( int i = 0;i < arr.length; ++i){
            total += arr[i];
            int complement = total - sum;
            if ( total == sum ){
                from = 0;
                to = i+1;
                max = i+1;
            }
            else if ( prefixMap.containsKey(complement) ){
                int start = prefixMap.get(complement);
                if ( ( i - start) > max ) {
                    from = start+1;
                    to = i+1;
                    max = i - start;
                }
            }
            prefixMap.putIfAbsent(total,i);
        }
        return Arrays.copyOfRange(arr,from,to);
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,7,5};
        int sum = 12;
        System.out.println(Arrays.toString(find(arr,sum)));
    }
}
