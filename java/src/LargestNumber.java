import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
   https://leetcode.com/problems/largest-number/

   Given a list of non negative integers, arrange them such that they form the largest number.


   Note: Solution1 is far simpler. Lexical string comparison for number is the same int comparison.
         Solution2 is far faster..
 */
public class LargestNumber {


    public static String largestNumber1(int[] nums) {
        List<Integer> results = Arrays.stream(nums).boxed().sorted( (a, b) ->{
           String ab = String.valueOf(a) + b;
           String ba = String.valueOf(b) + a;
           return ba.compareTo(ab);
        }).collect(Collectors.toList());

        // edge case, leading 0
        if ( results.size() > 0 && results.get(0) == 0  ) return "0";

        StringBuilder sb = new StringBuilder();
        for( Integer num: results ){
            sb.append(num);
        }
        return sb.toString();
    }

    public static String largestNumber2(int[] nums) {

        List<List<Integer>> results = new ArrayList<>();
        for( int num: nums ){
            List<Integer> l = new ArrayList<>();
            if ( num == 0 ) {
                l.add(0);
                results.add(l);
                continue;
            }
            for ( int i = num; i != 0 ; i /=10 ){
                l.add(i%10);
            }
            Collections.reverse(l);
            results.add(l);
        }

        results.sort((a, b) -> {
            List<Integer> listA = new ArrayList<>(a);
            listA.addAll(b);

            List<Integer> listB = new ArrayList<>(b);
            listB.addAll(a);

            for (int i = 0; i < listB.size(); ++i) {
                int numA = listA.get(i);
                int numB = listB.get(i);
                if (numA == numB) continue;
                return numB - numA;
            }
            return 0;
        });

        // edge case, leading 0
        if ( results.size() > 0 && results.get(0).size() > 0 && results.get(0).get(0) == 0  ) return "0";

        StringBuilder sb = new StringBuilder();
        for( List<Integer> digits: results ){
            for ( int digit: digits) sb.append(digit);
        }
        return sb.toString();
    }

    public static void main(String[] args){
        int[] nums = {3,30,34,5,9};
        System.out.println(largestNumber1(nums));
        System.out.println(largestNumber2(nums));
    }

}
