import java.util.Stack;
/*


https://leetcode.com/problems/basic-calculator-ii/
 */
public class Calculator2 {

    static int eval ( Integer operand1, Character oper, Integer operand2){
        if ( oper == null ){
            return operand2;
        }
        else if ( oper == '*' ){
            return operand1 * operand2;
        }
        else {
            return operand1 / operand2;
        }
    }

    public static int calculate(String s) {
        int result = 0;
        int num = 0;
        int sign = 1;
        Integer operand = null;
        Character oper = null;
        Stack<Integer> results = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (Character.isSpaceChar(ch)) continue;
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
                continue;
            }
            if (ch == '+') {
                results.add(eval(operand, oper, num));
                results.add(sign);
                sign = 1;
                num = 0;
                operand = null;
                oper = null;
            }
            else if (ch == '-') {
                results.add(eval(operand, oper, num));
                results.add(sign);
                sign = -1;
                num = 0;
                operand = null;
                oper = null;
            }
            else if (ch == '/') {
                operand = eval(operand, oper, sign*num);
                oper = '/';
                sign = 1;
                num = 0;
            }
            else if (ch == '*') {
                operand = eval(operand, oper, sign*num);
                oper = '*';
                sign = 1;
                num = 0;
            }
        }
        results.add(eval(operand, oper, num));
        results.add(sign);
        while (!results.isEmpty()) {
            result += results.pop() * results.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        String expr = "5*-2";
        System.out.println(calculate(expr));
    }
}
