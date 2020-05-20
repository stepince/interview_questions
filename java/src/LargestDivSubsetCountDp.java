/*

Given an array of n distinct elements, 
find length of the largest subset such that every pair in the subset
is such that the larger element of the pair is divisible by smaller element

This works because all you need to do is check that the path all meet the criteria,
so you just need to increment the count by one
input: "10, 4, 1, 8, 2, 5,33, 21, 11, 17, 19, 23"
output: 4 [2, 4, 8, 1]
O(N^2)
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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LargestDivSubsetCountDp {

    private static int counter;

    private static int find(int[] arr, int idx, Integer prev, int count, Map<String,Integer> mem ){
        ++counter;
        if ( idx == arr.length -1 ) return count;
        String key = idx + ":" + prev;
        if ( mem.containsKey(key) ) return mem.get(key);
        int include = 0;
        if ( prev == null ){
            include = find(arr, idx - 1, arr[idx-1],1, mem);
        }
        else if ( prev % arr[idx-1] == 0 ){
            include = find(arr, idx - 1, arr[idx-1],count + 1,mem);
        }
        int exclude = find(arr,idx+1, arr[idx+1], 0, mem);
        int result = Math.max(include,exclude);
        mem.put(key,result);
        return result;
    }

    public static int find(int[] arr){
        if ( arr == null || arr.length == 0 ) return 0;
        Arrays.sort(arr);
        return find(arr,arr.length,null,0,new HashMap<>());
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
        System.out.println( arr.length + ":" + counter);
    }
}