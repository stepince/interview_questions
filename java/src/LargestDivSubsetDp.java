/*

Given an array of n distinct elements,
find length of the largest subset such that every pair in the subset
is such that the larger element of the pair is divisible by smaller element

This works because all you need to do is check that the path all meet the criteria,
so you just need to increment the count by one
input: "10, 1, 8, 4, 2"
output: [2, 4, 8, 1]
O(2^N)
algorithm: is using recursion
notes: this type of subset problem needs to be treated differently than
       the sum subset problems, with sum problems you can initialize curr to the total or 0.

                               10,1,8,4,2
                            1,2,4,8,10/null
                1,2,4,8/10                        1,2,4,8/null
       1,2,4/10          1,2,4/10          1,2,4/8      1,2,4/null
    1,2,4/4    1,2/8    1,2/4   1,2/10                  1,2/4     1,2/null
  1/2  1/4  1/2 1/8                                         1/2    1/null
 1 2   1 4      1 8


*/

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LargestDivSubsetDp {

    private static List<Integer> maxList(List<Integer> a, List<Integer> b ){
        if ( a == null && b == null ) return null;
        if ( a == null ) return b;
        if ( b == null ) return a;
        return b.size() > a.size() ? b : a;
    }

    private static List<Integer> find(int[] arr, int idx, Map<String, List<Integer>> mem){
        if ( idx == 0 ) return new ArrayList<>();
        String key = String.valueOf(idx);
        if ( mem.containsKey(key) ) return new ArrayList<>(mem.get(key));
        List<Integer> maxList = new ArrayList<>();
        for ( int i = idx; i > 0 ; --i ){
            List<Integer> l = find(arr, i-1, mem);
            if ( l.isEmpty() || arr[i-1] % l.get(l.size()-1) == 0 ){
                l.add( arr[i-1] );
            }
            maxList = maxList(maxList, l);
        }
        mem.put(key, new ArrayList<>(maxList));
        return maxList;
    }

    public static List<Integer> find(int[] arr){
        if ( arr == null || arr.length == 0 ) return new ArrayList<>();
        Arrays.sort(arr);
        System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        return find(arr,arr.length, new HashMap<>());
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}