/*
Partition a set into two subsets such that the difference of subset sums is minimum

Input:  arr[] = {1, 6, 11, 5}
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11

*/

import java.util.Arrays;
import java.util.stream.Stream;

public class PartitionSubsetMinDiff {

    public static int find( int[] arr ){
        return findUtil(arr,0,0,0);
    }

    private static int findUtil( int[] arr, int idx, int sum1, int sum2 ){
        if ( idx == arr.length ) return Math.abs(sum1 - sum2);
        return Math.min(  findUtil(arr,idx+1, sum1+arr[idx], sum2), findUtil(arr,idx+1,sum1, sum2+arr[idx])   );
    }

    public static int find2( int[] arr ){
        int total = Arrays.stream(arr).sum();
        return findUtil2(arr,0, 0, total);
    }

    private static int findUtil2( int[] arr, int idx, int sum, int total ){
        if ( idx == arr.length ) return Math.abs(total - 2*sum);
        return Math.min(  findUtil2(arr,idx+1, sum+arr[idx], total), findUtil2(arr,idx+1,sum, total ));
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
        System.out.println(find2(arr));
    }
}