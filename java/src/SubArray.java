import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SubArray {

    public static boolean find(int[] arr, int sum){
       int accSum = 0;
       Set<Integer> prefixSet = new HashSet<>();

       for ( int num: arr){
           accSum += num;
           if ( accSum == sum || prefixSet.contains(accSum-sum) ) return true;
           prefixSet.add(accSum);
       }
       return false;
    }

    public static void main(String[] args){
       int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       int sum = Integer.parseInt(args[1]);
       System.out.println(find(arr,sum));
    }

}