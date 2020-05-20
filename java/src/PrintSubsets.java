import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*

   Runtime:
   O(2^N)

 */
public class PrintSubsets {

    private static void print(List<Character> arr, int idx, Stack<Character> st){
       if ( idx == arr.size() ) {
           System.out.println(st);
           return;
       }
       st.push(arr.get(idx));
       print(arr,idx+1,st);
       st.pop();
       print(arr,idx+1,st);
    }

    public static void print(List<Character> arr){
        print(arr,0,new Stack<>());
    }

    public static void main(String[] args){
       List<Character> arr =  Stream.of(args[0].split("[\\s,+]")).map( x->x.charAt(0)).collect(Collectors.toList());
       print(arr);
    }
}