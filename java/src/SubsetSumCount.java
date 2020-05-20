import java.util.Arrays;
import java.util.stream.Stream;
/*

Given a set of distinct integers, and a value m,
count the number of subset of the given set with sum a m.
for non negative numbers

Input : arr[] = {2,4,6,10};
        m = 1;
Output : 2


 */

public class SubsetSumCount {

    private static int find(int[] arr, int idx, int sum){
        if ( sum == 0 ) return 1;
        if ( idx == arr.length) return 0;
        if ( arr[idx] > sum ) return 0;
        return  find(arr, idx+1, sum - arr[idx]) + find(arr, idx+1, sum);
    }

    public static int find(int[] arr, int sum) {
        Arrays.sort(arr);
        return find(arr,0, sum);
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(args[1]);
        System.out.println(find(arr,m));
    }

}