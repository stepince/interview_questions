import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinkedListAdder {

    public static double calc(LinkedList<Integer> l1, LinkedList<Integer> l2 ){
        double result = 0;
        int minLen = Math.min(l1.size(), l2.size());
        int maxLen = Math.max(l1.size(), l2.size());

        for( int i = 0; i < minLen; ++i ){
             int val1 = l1.get(l1.size()-i -1);
             int val2 = l2.get(l2.size()-i -1);
             result += (val1 + val2) * Math.pow(10,i);
        }

        for( int i = minLen; i < maxLen; ++i ){
            if ( l1.size() > l2.size() ){
                int val1 = l1.get(l1.size()-i -1);
                result += val1 * Math.pow(10,i);
            }
            else {
                int val2 = l2.get(l2.size()-i -1);
                result += val2 * Math.pow(10,i);
            }
        }
        return result;
    }

    public static void main(String[] args){
        List<Integer> l1 = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        List<Integer> l2 = Stream.of(args[1].split("[\\s,]+")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        System.out.println(calc(new LinkedList<>(l1), new LinkedList<>(l2)   ));
   }








}