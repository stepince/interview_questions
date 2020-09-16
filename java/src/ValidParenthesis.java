import java.util.Stack;

public class ValidParenthesis {
    public static boolean isValid(String str){
        Stack<Character> st = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (st.empty()) {
                st.push(ch);
            }
            else if (st.peek() == '{' && ch == '}') {
                st.pop();
            }
            else if (st.peek() == '(' && ch == ')') {
                st.pop();
            }
            else if (st.peek() == '[' && ch == ']') {
                st.pop();
            }
            else if ( ch == '}' || ch == ']' || ch == ')' ) {
                return false;
            }
            else {
                st.push(ch);
            }
        }
        return st.empty();
    }

    public static void main(String[] args){
//        String str = "[(])";
        String str = "()([]))";
        System.out.println(isValid(str));
    }
}
