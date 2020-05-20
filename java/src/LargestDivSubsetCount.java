/*

Given an array of n distinct elements,
find length of the largest subset such that every pair in the subset
is such that the larger element of the pair is divisible by smaller element

This works because all you need to do is check that the path all meet the criteria,
so you just need to increment the count by one
input: "10, 1, 8, 4, 2"
output: 4 [2, 4, 8, 1]
O(2^N)
algorithm: is using recursion
notes: this type of subset problem needs to be treated differently than
       the sum subset problems, with sum problems you can initialize curr to the total or 0.

                10,1,8,4,2
                1,2,4,8,10/null
             1,2,4,8/10              1,2,4,8/null
         1,2,4/10  1,2,4/8

*/

import java.util.Arrays;
import java.util.stream.Stream;

public class LargestDivSubsetCount {

    private static int find(int[] arr, int idx, Integer prev, int count ){
        if ( idx == 0 ) return count;
        int include = 0;
        if ( prev == null ){
            include = find(arr, idx-1, arr[idx-1], 1);
        }
        else if ( prev % arr[idx-1] == 0 ){
            include = find(arr, idx-1, arr[idx-1], count + 1);
        }
        int exclude = find(arr,idx-1, prev, count );
        return Math.max(include,exclude);
    }

    public static int find(int[] arr){
        if ( arr == null || arr.length == 0 ) return 0;
        Arrays.sort(arr);
        return find(arr,arr.length,null,0);
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}