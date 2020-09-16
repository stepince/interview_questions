import java.util.*;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

public class SubArraysPrint {

    public static List<List<Integer>> find(int[] arr, int sum){
        final List<List<Integer>> l = new ArrayList<>();
        int accSum = 0;
        Map<Integer,List<Integer>> prefixMap = new HashMap<>();
        for ( int i = 0; i < arr.length; ++i){
            accSum += arr[i];
            if ( accSum == sum ) l.add( Arrays.stream(Arrays.copyOfRange(arr,0,i+1)).boxed().collect(Collectors.toList()) );
            int complement = accSum - sum;
            prefixMap.putIfAbsent(complement, new ArrayList<>());
            final int to = i + 1;
            // start is to the left of complement
            prefixMap.get(complement).forEach(start ->
                    l.add(Arrays.stream(Arrays.copyOfRange(arr,start+1,to)).boxed().collect(Collectors.toList()))
            );
            prefixMap.computeIfAbsent(accSum,(k)->new ArrayList<>()).add(i);
        }
        return l;
    }

    public static void main(String[] args){
        int[] arr = {1,20,20,-10,-10,20,3};
        int sum = 40;
//        int[] arr =  Stream.of(args[0].split("[\\s,s]+")).mapToInt(Integer::parseInt).toArray();
//        int sum = Integer.parseInt(args[1]);
        System.out.print(find(arr,sum));
    }
 
}