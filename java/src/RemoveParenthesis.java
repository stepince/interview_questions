import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
/*

https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 */
public class RemoveParenthesis {


    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        Set<Integer> badIndices = new HashSet<>();
        for ( int i = 0 ; i < chars.length ;++i  ){
            char ch = chars[i];
            if ( st.isEmpty() && ch == ')' ) {
                badIndices.add(i);
            }
            else if ( ch == '('  ) {
                st.push(i);
            }
            else if ( !st.empty() && ch == ')' ){
                st.pop();
            }
            else if ( ch == ')' ){
                badIndices.add(i);
            }
        }

        while( !st.empty() ) badIndices.add(st.pop());
        for ( int i = 0 ; i < chars.length; ++i ){
            if ( !badIndices.contains(i)  ) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args){
        String str = "lee(t(c)o)de)";
        System.out.println(minRemoveToMakeValid(str));
    }
}
