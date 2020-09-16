import java.util.Arrays;
import java.util.stream.Stream;
/*

Given a set of non-negative distinct integers, and a value m, determine if
there is a subset of the given set with sum divisible by m.

Input : arr[] = {3, 1, 7, 5};
        m = 6;
Output : YES

Input : arr[] = {1, 6};
        m = 5;
Output : NO

O(n^2)

notes: 0 % M is zero

n > m then this is always a subset that is divisible, you really only need to check for the case
n <= m
       subset is any list of any integers.

 */

public class SubsetSumDivisibleByM {

    private static boolean find(int[] arr, int idx, int m, int sum){
        if ( sum != 0 && sum % m == 0 ) return true;
        if ( idx == 0 ) return false;
        return  find(arr, idx-1, m, sum - arr[idx-1]) || find(arr, idx-1, m, sum);
    }

    public static boolean find(int[] arr, int m) {
        int sum = Arrays.stream(arr).sum();
        return find(arr,arr.length,m,sum);
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(args[1]);
        System.out.println(find(arr,m));
    }

}