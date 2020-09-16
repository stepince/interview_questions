/*

https://leetcode.com/problems/wildcard-matching
 */

public class StringWildcard {

    public static boolean isMatch(char[] s, int sIdx, char[] p, int pIdx , Boolean[][] mem) {
        if ( sIdx == s.length && pIdx == p.length ) return true;
        if ( sIdx == s.length ) {
            return (p[pIdx] == '*') && isMatch(s, sIdx, p, pIdx + 1, mem);
        }
        if ( pIdx == p.length ) return false;
        if ( mem[sIdx][pIdx] != null ) return mem[sIdx][pIdx];

        boolean ans;
        if ( p[pIdx] == '?' ) {
            ans = isMatch(s,sIdx+1,p,pIdx+1,mem);
        }
        else if ( p[pIdx] == '*' ) {
            // note: this case is covered
            // isMatch(s,sIdx+1,p,pIdx+1,mem) == skip character, skip pattern
            //            skip pattern                    skip character
            ans = isMatch(s,sIdx,p,pIdx+1,mem) || isMatch(s,sIdx+1,p,pIdx,mem);
        }
        else {
            ans = (s[sIdx] == p[pIdx] && isMatch(s,sIdx+1,p,pIdx+1,mem));
        }
        return mem[sIdx][pIdx] = ans;
    }

    public static boolean isMatch(String s, String p) {
        return isMatch(s.toCharArray(),0,p.toCharArray(),0, new Boolean[s.length()][p.length()]  );
    }

    public static void main(String[] args){
        String s = "hello";
        String p = "h*";
        System.out.println(isMatch(s,p));
    }
}
