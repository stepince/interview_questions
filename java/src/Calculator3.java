import java.util.Deque;
import java.util.LinkedList;

/*

https://leetcode.com/problems/basic-calculator-ii/

I think this is the simplest and most intuitive solution to follow.
It is also pretty fast 81% on leetcode

note: non-negative
 */
public class Calculator3 {

    public static int calculate(String s) {
        // default to 0 for number also -12-4 is converted 0-12-4
        int num = 0;
        int result = 0;
        // use a queue, process inorder
        // nums always has one more than operators
        Deque<Integer> nums = new LinkedList<>();
        Deque<Character> opers = new LinkedList<>();
        for ( char ch: s.toCharArray() ){
            if ( Character.isSpaceChar(ch) ) continue;
            if ( Character.isDigit(ch ) ) {
                num = num * 10 + (ch - '0');
                continue;
            }
            opers.add(ch);
            nums.add(num);
            num = 0;
        }
        // add last item;
        nums.add(num);
        // keep track of previous minus
        int sign = 1;
        while( ! opers.isEmpty() ){
            char oper = opers.remove();
            int num1 = nums.remove();
            if ( oper == '-' ){
                result += sign * num1;
                sign = -1;
            }
            else if ( oper == '+' ){
                result += sign * num1;
                sign = 1;
            }
            else if ( oper == '/' ){
                int num2 = nums.remove();
                nums.addFirst(num1/num2 );
            }
            else if ( oper == '*' ){
                int num2 = nums.remove();
                nums.addFirst(num1*num2 );
            }
        }
        result += sign * nums.remove();
        return result;
    }

    public static void main(String[] args) {
        String expr = "5*2";
        System.out.println(calculate(expr));
    }
}
