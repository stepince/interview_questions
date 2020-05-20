import java.util.Stack;
/*

  check for balanced parentheses
  input: [()]{}{[()()]()}
  output: true

  input: [(])
  output: false
 */
public class CheckBalancedParentheses {

    static boolean isParentheses(char ch){
        return ch == '{' || ch == '}' || ch == '[' || ch == ']' || ch == '(' || ch == ')';
    }

    public static boolean find(String str){
        Stack<Character> st = new Stack<>();
        for ( char ch: str.toCharArray() ){
            if ( !isParentheses(ch) ) continue;
            if ( st.empty() ){
                st.push(ch);
            }
            else if ( (ch == '}' && st.peek() == '{') || (ch == ']' && st.peek() == '[' ) || ( ch == ')' && st.peek() == '(')) {
                st.pop();
            }
            else {
                st.push(ch);
            }
        }
        return st.isEmpty();
    }

    public static void main(String[] args){
        String str = args[0];
        System.out.println(find(str));
    }
}