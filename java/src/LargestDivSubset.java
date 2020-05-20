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
             1,2,4,8/10              1,2,4,8/null
         1,2,4/10  1,2,4/8

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LargestDivSubset {

    static List<Integer> maxSize( List<Integer> a,  List<Integer> b  ){
        return b.size() > a.size() ? b : a;
    }

    private static List<Integer> find(int[] arr, int idx, List<Integer> l ){
        if ( idx == 0 ) return l;
        List<Integer> includeList = new ArrayList<>(l);
        // empty or (lastElement % arr[idx-1] == 0)
        if ( l.isEmpty() || l.get( l.size()-1) % arr[idx-1] == 0 ){
            includeList.add( arr[idx-1] );
            includeList = find(arr, idx-1, includeList);
        }
        return maxSize( includeList, find(arr,idx-1, l ) );
    }

    private static List<Integer> find2(int[] arr, int idx, List<Integer> l ){
        if ( idx == 0 ) return l;
        List<Integer> maxList = l;
        for ( int i = idx; i > 0 ; --i ){
            List<Integer> list = new ArrayList<>(l);
            // empty or (lastElement % arr[idx-1] == 0)
            if ( l.isEmpty() || l.get( l.size()-1) % arr[i-1] == 0 ){
                list.add( arr[i-1] );
            }
            maxList = maxSize(maxList,find2(arr, i-1, list) );
        }
        return maxList;
    }

    public static List<Integer> find(int[] arr){
        if ( arr == null || arr.length == 0 ) return new ArrayList<>();
        Arrays.sort(arr);
        return find(arr,arr.length, new ArrayList<>());
    }

    public static List<Integer> find2(int[] arr){
        if ( arr == null || arr.length == 0 ) return new ArrayList<>();
        Arrays.sort(arr);
        return find2(arr,arr.length, new ArrayList<>());
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
        System.out.println(find2(arr));
    }
}