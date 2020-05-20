import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubArraysPrint {

   public static void find(int[] arr, int sum){
        int accSum = 0;
        Map<Integer,List<Integer>> prefixMap = new HashMap<>();
        for ( int i = 0; i < arr.length; ++i){
            accSum += arr[i];
            if ( accSum == sum ){
                System.out.println( Arrays.stream(Arrays.copyOfRange(arr,0,i+1)).boxed().collect(Collectors.toList()) );
            }
            int complement = accSum - sum;
            if ( prefixMap.containsKey(complement) ) {
                List<Integer> startIndices = prefixMap.get( complement );
                for ( int start: startIndices ){
                    System.out.println( Arrays.stream(Arrays.copyOfRange(arr,start+1,i+1)).boxed().collect(Collectors.toList()) );
                }
            }
            prefixMap.putIfAbsent(accSum,new ArrayList<>());
            prefixMap.get(accSum).add(i);
        }
   }


    public static void main(String[] args){
        int[] arr =  Stream.of(args[0].split("[\\s,s]+")).mapToInt(Integer::parseInt).toArray();
        int sum = Integer.parseInt(args[1]);
        find(arr,sum);
    }
 
}