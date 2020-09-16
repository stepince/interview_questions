/*


https://leetcode.com/problems/valid-parenthesis-string/

Given a string containing only three types of characters: '(', ')' and '*',
write a function to check whether this string is valid. We define the validity
of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
 */

import java.util.Stack;

public class ValidParenthesis2 {

    public static boolean checkValidString(char[] chars, int idx, Stack<Character> st, Boolean[][] dp) {
        // this was a guess to use the stack size as part of the key.
        int stSize =  st.size();
        if ( dp[idx][stSize] != null ) return dp[idx][stSize];
        for ( int i = idx; i < chars.length; ++i ){
            char ch = chars[i];
            if ( st.isEmpty() ){
                if ( ch == ')' ) return false;
                st.push(ch);
                continue;
            }
            char top = st.peek();
            if ( ch == '*' ){
                // three paths
                // path 1, ignore, default
                // path 2, '('
                Stack<Character> st2 = (Stack<Character>)st.clone();
                st2.push('(' );
                if ( checkValidString(chars,i+1, st2,dp)  ) return true;
                // path 3, ')'
                if ( top == '(' ) {
                    Stack<Character> st3 = (Stack<Character>) st.clone();
                    st3.pop();
                    if (checkValidString(chars, i + 1, st3, dp)) return true;
                }
            }
            else if ( top == '(' && ch == ')' ){
                st.pop();
            }
            else{
                st.push(ch);
            }
        }
        while ( !st.isEmpty() && st.peek() == '*' ) st.pop();
        return dp[idx][stSize] = st.isEmpty();
    }

    public static boolean checkValidString(String s) {
        Stack<Character> st = new Stack<>();
        char[] chars = s.toCharArray();
        Boolean[][] dp = new Boolean[s.length()+1][s.length()+1];
        return checkValidString(chars, 0, st, dp);
    }

    public static void main(String[] args){
//        String str = "(())()())(*))(((((())()*))**))**()(*(()()*)(**(())()**)((**(()(((()()**)())*(*))(())()()*";
        long t = System.currentTimeMillis();
//        String str = "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
        String str = "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
//        String str = "((()))()(())(*()()())**(())()()()()((*()*))((*()*)";
        System.out.println(checkValidString(str));
        System.out.println("time = " + ( System.currentTimeMillis() - t ) );
    }
}
