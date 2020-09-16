import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

/*
  A, B, C
  A, B
  A,
  B, C,
  B,
  C

  Runtime: O(N^2)
  (n/n+1)/2
 */
public class PrintSubArrays {

    public static void print(List<?> arr, int begin, int end){
        if ( begin == arr.size() ) return;
        if ( begin == end ) {
            print(arr, 0, end + 1);
        }
        else {
            System.out.println(arr.subList(begin,end));
            print(arr, begin+1, end);
        }
    }

    public static void print(List<?> arr ){
        print(arr,0,1);
    }

    public static void print2(List<?> arr ){
        for ( int i = 0; i < arr.size() ; ++i ){
            int end = arr.size() - i;
            System.out.println(arr.subList(0,end));
            for ( int j = 1; j < end; ++j ){
                System.out.println(arr.subList(j,end));
            }
        }
    }

    public static void main(String[] args){
        // string to char
//        List<Character> arr = Stream.of(args[0].split("\\s*,\\s*")).map(x->x.charAt(0)).collect(Collectors.toList());
        List<Integer> arr = List.of( 1, -2,  4, -5, 1  );
        print(arr);
        System.out.println("------------");
        print2(arr);
    }
}