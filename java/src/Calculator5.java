import java.util.ArrayDeque;
import java.util.Deque;

/*

https://leetcode.com/problems/basic-calculator-ii/

I think this and calculator3 are the simplest and most intuitive solutions to follow.
It is also pretty fast 72% on leetcode

note: non-negative
 */
public class Calculator5 {

    public static int calculate(String s) {
        int num = 0;
        Deque<Character> opers1 = new ArrayDeque<>();
        Deque<Integer> operands1 = new ArrayDeque<>();

        Deque<Character> opers2 = new ArrayDeque<>();
        Deque<Integer> operands2 = new ArrayDeque<>();

        for ( char ch:  s.toCharArray() ) {
            if ( ch == '+' || ch == '-' || ch == '*' || ch == '/' ) {
                operands1.add( num );
                opers1.add( ch );
                num = 0;
            }
            else if ( Character.isDigit(ch) ) {
                num = num * 10 + ( ch - '0' );
            }
        }

        operands1.add( num );
        while ( !opers1.isEmpty() ) {
            char ch = opers1.remove();
            if ( ch == '*' ) {
                int num1 = operands1.remove();
                int num2 = operands1.remove();
                operands1.addFirst( num1 * num2 );
            }
            else if ( ch == '/' ) {
                int num1 = operands1.remove();
                int num2 = operands1.remove();
                operands1.addFirst( num1 / num2 );
            }
            else if ( ch == '+' || ch == '-') {
                operands2.add(operands1.remove());
                opers2.add(ch);
            }
        }
        operands2.add(operands1.remove());
        while ( !opers2.isEmpty() ) {
            char oper = opers2.remove();
            if ( oper == '+' ) {
                int num1 = operands2.remove();
                int num2 = operands2.remove();
                operands2.addFirst(num1+num2);
            }
            else if ( oper == '-' ){
                int num1 = operands2.remove();
                int num2 = operands2.remove();
                operands2.addFirst(num1-num2);
            }
        }
        return operands2.pop();
    }

    public static void main(String[] args) {
        String expr = "5*2";
        System.out.println(calculate(expr));
    }
}
