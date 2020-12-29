import java.util.Deque;
import java.util.LinkedList;

/*
combining both
https://leetcode.com/problems/basic-calculator/

https://leetcode.com/problems/basic-calculator-ii/

Note: I doubt I will every be asked to this but I did as an exercise
      It is a little complicated
 */
public class Calculator4 {

    static int calculate( Deque<Integer> nums, Deque<Character> opers ) {
        // keep track of previous minus
        int sign = 1;
        int result = 0;
        while( !opers.isEmpty() ){
            char oper = opers.remove();
            if ( oper == '-' ){
                result += sign * nums.remove();
                sign = -1;
            }
            else if ( oper == '+' ){
                result += sign * nums.remove();
                sign = 1;
            }
            else if ( oper == '/' && !opers.isEmpty() && opers.peek() == '(' ){
                opers.remove();
                int num1 = nums.remove();
                int num2 = calculate( nums, opers );
                nums.addFirst(num1/num2 );
            }
            else if ( oper == '/' ){
                int num1 = nums.remove();
                int num2 = nums.remove();
                nums.addFirst(num1/num2 );
            }
            else if ( oper == '*' && !opers.isEmpty() && opers.peek() == '(' ){
                opers.remove();
                int num1 = nums.remove();
                int num2 = calculate( nums, opers );
                nums.addFirst(num1*num2 );
            }
            else if ( oper == '*' ){
                int num1 = nums.remove();
                int num2 = nums.remove();
                nums.addFirst(num1*num2 );
            }
            else if ( oper == '(' ){
                int num = calculate( nums, opers );
                nums.addFirst(num);
            }
            else if ( oper == ')' ){
                break;
            }
        }
        result += sign * nums.remove();
        return result;
    }

    public static int calculate(String s) {
        // default to 0 for number also -12-4 is converted 0-12-4
        int num = 0;
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
            if ( ch == '(' ){
                opers.add(ch);
            }
            else if ( ch == ')' ){
                opers.add(ch);
            }
            else {
                opers.add(ch);
                nums.add(num);
                num = 0;
            }
        }
        // add last item;
        nums.add(num);
        return  calculate(nums,opers);
    }

    public static void main(String[] args) {

        System.out.println(calculate("5*(-2)"));
    }
}
