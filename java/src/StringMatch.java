public class StringMatch {

    private static boolean find(String str, String pattern, int sIdx, int pIdx ){

        if ( sIdx < str.length() && pIdx == pattern.length() ) return false;

        if ( sIdx == str.length() && pIdx == pattern.length() ) return true;

        final boolean starPattern = pattern.length() - pIdx > 1 && pattern.charAt(pIdx+1) == '*';
        if ( sIdx == str.length() ) return starPattern && find(str, pattern, sIdx, pIdx + 2);

        final char ch = str.charAt(sIdx);
        final String subPattern = starPattern ? pattern.substring(pIdx, pIdx+2) : null;
        // repeat match
        if ( (ch + "+").equals(subPattern) || (".+").equals(subPattern)) {
            // move to the next char || move to the subPattern
            return find( str, pattern, sIdx+1, pIdx ) || find( str, pattern, sIdx+1, pIdx+2 );
        }
        // star pattern match
        else if ( (ch + "*").equals(subPattern) || (".*").equals(subPattern) ) {
            // move to the next char || skip this expr
            return find( str, pattern, sIdx+1, pIdx ) || find( str, pattern, sIdx, pIdx+2 );
        }
        // skip, 0 part of star pattern
        else if ( starPattern ) {
            return find( str, pattern, sIdx, pIdx+2 );
        }
        else if ( pattern.charAt(pIdx) == '.' || ch == pattern.charAt(pIdx) ) {
            return find( str, pattern, sIdx+1, pIdx+1 );
        }
        else {
            return false;
        }


    }

    public static boolean find(String str, String pattern){
        return find(str,pattern,0,0);
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
//        String str = "aaaaaaaaaaaaab";
//        String pattern = "a*a*a*a*a*a*a*a*a*a*c";
        long time = System.currentTimeMillis();
//        String str = "aaaaaaaaaaaaab";
//        String pattern = "a*a*a*a*a*a*a*a*a*a*c";
        System.out.println(find(str,pattern));
        System.out.println("time: " +  (System.currentTimeMillis() -  time));
    }
}