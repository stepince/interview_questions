import java.util.ArrayList;
import java.util.List;


/*

https://leetcode.com/problems/generate-parentheses/
This solution uses backtracking.

Time and Space Complexity : O(4^n/n^1/2)
The mathematical analysis is pretty complex, using recursion formula
I would just say at least O(2^n)

 */
public class GenerateValidParentheses {

    public static List<String> generate(int n, int open, int close, StringBuilder sb, List<String> results){
        if ( open == n && close == n ){
            results.add(sb.toString());
            return results;
        }
        if ( open < n ){
            sb.append("(");
            generate(n, open+1, close, sb, results);
            sb.deleteCharAt( sb.length() - 1 );
        }

        // match the open
        if ( close < open ){
            sb.append(")");
            generate(n, open, close+1, sb, results);
            sb.deleteCharAt( sb.length() - 1 );
        }
        return results;
    }

    public static List<String> generate(int n){
        List<String> results = new ArrayList<>();
        if ( n == 0 ) return results;
        return generate(n, 0, 0, new StringBuilder() ,results);
    }

    public static void main(String[] args){
        int n = 4;
        System.out.println(generate(n));
    }
}
