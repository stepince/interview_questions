import java.util.Stack;
/*

https://leetcode.com/problems/basic-calculator/

 */
public class Calculator {

    public static int calculate(String s) {
        int num = 0;
        int result = 0;
        int sign = 1;
        Stack<Integer> st = new Stack<>();

        for ( char ch: s.toCharArray() ){
            if ( Character.isSpaceChar(ch) ) continue;
            if ( Character.isDigit(ch) ){
                num = num * 10 + (ch - '0');
                continue;
            }
            if ( ch == '-' ){
                result += sign * num;
                sign = -1;
                num = 0;
            }
            else if ( ch == '+' ){
                result += sign * num;
                sign = 1;
                num = 0;
            }
            else if ( ch == '(' ){
                st.push(sign);
                st.push(result);
                num = 0;
                sign = 1;
                result = 0;
            }
            else if ( ch == ')' ){
                result+= num*sign;
                result = st.pop() + st.pop()*result;
                num = 0;
                sign = 1;
            }
        }
        result += sign * num;
        return result;
    }

    public static void main(String[] args) {
        //System.out.println( calc(args[0]));
//        "(1+(4+5+2)-3)+(6+8)"

//        "(7)-(0)+(4)"
        //(7)+5
        System.out.println(calculate("7+(-5)"));
    }
}
