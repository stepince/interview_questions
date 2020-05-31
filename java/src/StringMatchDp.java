/*
This dp solution is a fast as you can get. leetcode says 100% faster than the other solutions.

Time:
    O(n*m)
    where n is the length of the string and m is the length of the pattern
 */

public class StringMatchDp {

    private static boolean find(String str, String pattern, int sIdx, int pIdx, Boolean[][] dp ){
        final int strLen = str.length();
        final int patternLen = pattern.length();
        if ( sIdx < strLen && pIdx == patternLen ) return false;
        if ( sIdx == strLen && pIdx == patternLen ) return true;

        if ( dp[sIdx][pIdx] != null ) return dp[sIdx][pIdx];

        boolean result = false;
        final boolean starPattern = patternLen - pIdx > 1 && pattern.charAt(pIdx+1) == '*';
        // case of more pattern but no char to consume
        if ( sIdx == str.length() ) {
            result = starPattern && find(str, pattern, sIdx, pIdx + 2, dp);
            dp[sIdx][pIdx]=result;
            return result;
        }

        final boolean hasMatch = str.charAt(sIdx) == pattern.charAt(pIdx) || pattern.charAt(pIdx) == '.';
        final boolean repeatPattern = patternLen - pIdx > 1 && pattern.charAt(pIdx+1) == '+';

        // repeat match
        if ( repeatPattern && hasMatch ) {
            // move to the next char || move to the subPattern
            result = find( str, pattern, sIdx+1, pIdx, dp ) || find( str, pattern, sIdx+1, pIdx+2, dp );
        }
        // star pattern match e.g.  str=ssss pattern=s*
        else if ( starPattern && hasMatch ) {
            // move to the next char || skip this expr
            result = find( str, pattern, sIdx+1, pIdx, dp ) || find( str, pattern, sIdx, pIdx+2, dp );
        }
        // skip, star pattern non-match str=d pattern=s*
        else if ( starPattern ) {
            result = find( str, pattern, sIdx, pIdx+2, dp );
        }
        else if ( hasMatch ) {
            result = find( str, pattern, sIdx+1, pIdx+1, dp );
        }

        dp[sIdx][pIdx]=result;
        return result;
    }

    public static boolean find(String str, String pattern){
        return find(str,pattern,0,0, new Boolean[str.length()+1][pattern.length()+1]);
    }

    public static void main(String[] args){
//        String str = args[0].trim();
//        String pattern = args[1].trim();
//        String str = "abbbbbbbbbbc";
//        String pattern = "ab+c*";
//        String str = "ad";
//        String pattern = "a.";
//        String str = "mississippi";
//        String pattern = "mis*is*p*";
        String str = "aasdfasdfasdfasdfas";
        String pattern = "aasdf.*asdf.*asdf.*asdf.*s";
//        String str = "mississippi";
//        String pattern = "mis*is*ip*.";
//        String pattern = "mis*is*p*.";
//        String pattern = "mis*is*ip*.";
//        String str = "aaaaaaaaaaaaac";
//        String pattern = "a*a*a*a*a*a*a*a*a*a*c";
        long time = System.currentTimeMillis();
//        String str = "aaaaaaaaaaaaab";
//        String pattern = "a*a*a*a*a*a*a*a*a*a*b";
        System.out.println(find(str,pattern));
        System.out.println("time: " +  (System.currentTimeMillis() -  time));
    }
}