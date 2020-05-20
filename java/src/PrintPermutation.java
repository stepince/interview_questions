import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
Runtime
O(N!)
*/

public class PrintPermutation {

    private static void find(List<Character> arr, int idx){
        if ( idx == arr.size() ){
            System.out.println(arr);
        }

        for ( int i = idx; i < arr.size(); ++i ){
            Collections.swap(arr,idx,i);
            find(arr,idx+1);
            Collections.swap(arr,idx,i);
        }
    }

    public static void find(List<Character> arr){
        find(arr,0);
    }

    public static void main(String[] args){
        List<Character> arr = args[0].chars().mapToObj(x->(char)x).collect(Collectors.toList());
        find(arr);
    }
}