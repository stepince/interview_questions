import java.util.Stack;

/*

https://leetcode.com/problems/longest-valid-parentheses/submissions/
Hard problem
-------------------------
Time:
  O(N)
Space:
  O(N)
 */
public class LongestValidParentheses {

    static final class Token {
        int val;
        Token(int val){
            this.val = val;
        }
    }

    final static Token OPEN = new Token(0);

    // helper function for longestValidParentheses1
    static int add(Stack<Token> st, Token tok ){
        if ( st.empty() || st.peek() == OPEN ){
            st.push( tok );
            return tok.val;
        }
        else {
            return st.peek().val += tok.val;
        }
    }

    // faster running but more difficult to follow
    public static int longestValidParentheses1(String s) {
        Stack<Token> st = new Stack<>();
        int longest = 0;
        for ( final char ch: s.toCharArray() ) {
            if ( ch == '(' ){
                st.push(OPEN);
                continue;
            }
            if ( st.empty() ) {
                // ignore )
                continue;
            }

            // only ( are put on stack
            // ) are processed but never added to stack
            // this can be done with numbers 0 for open
            // keeping adding 2 for every () pairing
            Token tok = st.pop();
            // () -> 2
            if ( tok == OPEN ){
                // push number
                longest = Math.max(longest,add(st,new Token(2)));
            }
            // ( num ) -> num + 2
            else if ( !st.empty() ) {
                st.pop();
                tok.val += 2;
                longest = Math.max(longest, add(st,tok));
            }
        }
        return longest;
    }

    // easier to implement and follow
    public static int longestValidParentheses2(String s) {
        Stack<Token> st = new Stack<>();
        int max = 0;
        for ( final char ch: s.toCharArray() ) {
            if ( ch == '(' ){
                st.push(OPEN);
                continue;
            }
            if ( st.empty() ) {
                // ignore )
                continue;
            }

            Token tok = st.pop();
            // () -> 2
            if ( tok == OPEN ){
                st.push(new Token(2));
                max = Math.max(max,2);
            }
            // ( num ) -> num + 2
            else if ( !st.empty() && st.peek() == OPEN ) {
                st.pop();
                max = Math.max(max,tok.val += 2);
                st.push( tok );
            }
            // else invalid input

            // collapse the numbers
            while ( !st.empty() && st.peek() != OPEN ) {
                Token tok2 = st.pop();
                if ( !st.empty() && st.peek() != OPEN ){
                    max = Math.max( max, st.peek().val += tok2.val );
                }
                else {
                    st.push(tok2);
                    break;
                }
            }
        }
        return max;
    }

    // slightly refactoring of longestValidParentheses2
    public static int longestValidParentheses3(String s) {
        Stack<Token> st = new Stack<>();
        int max = 0;
        for ( final char ch: s.toCharArray() ) {
            if ( ch == '(' ){
                st.push(OPEN);
                continue;
            }
            if ( st.empty() ) {
                // ignore ')'
                continue;
            }

            // process ')'
            Token tok = st.pop();
            // () -> 2
            if ( tok == OPEN ){
                max = Math.max(max,addOpenClose(st));
            }
            // ( num ) -> num + 2
            else if ( !st.empty() && st.peek() == OPEN ) {
                st.pop();
                max = Math.max(max,addClose(st,tok));
            }
            // else invalid input
            // ignore
        }
        return max;
    }

    // helper function for longestValidParentheses3
    static int addOpenClose(Stack<Token> st){
        if ( st.empty() || st.peek() == OPEN ){
            st.push( new Token(2) );
            return 2;
        }
        else {
            return st.peek().val+=2;
        }
    }

    // helper function for longestValidParentheses3
    // ( num ) -> num + 2
    static int addClose(Stack<Token> st, Token tok ){
        tok.val+=2;
        if ( st.empty() || st.peek() == OPEN ){
            st.push( tok );
            return tok.val;
        }
        else {
            return st.peek().val += tok.val;
        }
    }

    public static void main(String[] args){
        String s = "()()(())";
//        String s = "()(())";
//        String s = "(()";
        // ( 2
//        String s = ")()())";
        //) 2 2 )
        //) 4 )
//        String s = "()(())";
//        String s = "(((((()))))(())))))))";
//        String s = "((())))))))";
        // 2(2
//        String s = "((())())(()))(()()(()(()))(()((((()))))))((()())()))()()(()(((((()()()())))()())(()()))((((((())))((()))()()))))(()))())))()))()())((()()))))(()(((((())))))()((()(()(())((((())(())((()()(()())))())(()(())()()))())(()()()))()(((()())(((()()())))(((()()()))(()()))()))()))))))())()()((()(())(()))()((()()()((())))()(((()())(()))())())))(((()))))())))()(())))()())))())()((()))((()))()))(((())((()()()(()((()((())))((()()))())(()()(()))))())((())))(()))()))))))()(()))())(()())))))(()))((())(()((())(((((()()()(()()())))(()())()((()(()()))(()(())((()((()))))))))(()(())()())()(()(()(()))()()()(()()())))(())(()((((()()))())))(())((()(())())))))())()()))(((())))())((()(()))(()()))((())(())))))(()(()((()((()()))))))(()()()(()()()(()(())()))()))(((()(())()())(()))())))(((()))())(()((()))(()((()()()(())()(()())()(())(()(()((((())()))(((()()(((()())(()()()(())()())())(()(()()((()))))()(()))))(((())))()()))(()))((()))))()()))))((((()(())()()()((()))((()))())())(()((()()())))))))()))(((()))))))(()())))(((()))((()))())))(((()(((())))())(()))))(((()(((((((((((((())(((()))((((())())()))())((((())(((())))())(((()))))()())()(())())(()))))()))()()()))(((((())()()((()))())(()))()()(()()))(())(()()))()))))(((())))))((()()(()()()()((())((((())())))))((((((()((()((())())(()((()))(()())())())(()(())(())(()((())((())))(())())))(()()())((((()))))((()(())(()(()())))))))))((()())()()))((()(((()((()))(((((()()()()()(()(()((()(()))(()(()((()()))))()(()()((((((()((()())()))((())()()(((((()(()))))()()((()())((()())()(())((()))()()(()))";
        System.out.println(longestValidParentheses1(s));
        System.out.println(longestValidParentheses2(s));
        System.out.println(longestValidParentheses3(s));
    }
    //  (((((()))))(())))))))
    //  (((((()
    //  (((((2
    //     adding )
    //  ((((4
    //     adding (
    //  ((((4(
    //     adding )
    //  ((((4()
    //  ((((4 2
    //  ((((6

    //  (((((2)

    //  (((((2))))(())))))))
    //  ((((4)))(())))))))
    //  (((6))(())))))))
    //  ((8)(())))))))
    //  (10(())))))))
    //  (10(2)))))))
    //  (10 4 ))))))
    //  (14 ))))))
    //  16)))))

}
