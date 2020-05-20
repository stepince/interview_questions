import java.util.HashSet;
import java.util.Set;
/*
   Runtime time
   O(N^2);
   Space O(N)
 */

public class PalindromicSubstrings {

    public static void findPalindromes(String str, int idx1, int idx2, Set<String> s) {
        //check from center
        for ( int i = 0; (idx1-i) >= 0 && (idx2 + i) < str.length() && str.charAt(idx1-i) == str.charAt(idx2+i); ++i  ){
            s.add(str.substring(idx1-i,idx2+i+1));
        }
    }

    public static void findSubstrings(String str, Set<String> s ) {

        // edge cases
        s.add("");
        if ( str.length() == 0 ) return;
        s.add(String.valueOf(str.charAt(0)));
        if ( str.length() == 1) return;
        s.add(String.valueOf(str.charAt(str.length()-1)));

        for ( int i = 1; i < str.length() -1; ++i ){
            findPalindromes(str,i,i,s);
            findPalindromes(str,i,i+1,s);
        }
    }

    public static Set<String> find(String str ) {
        Set<String> s = new HashSet<>();
        findSubstrings(str,s);
        return s;
    }

    public static void main(String[] args){
        String str = args[0];
        System.out.println(find(str));
//        System.out.println(find("a"));
    }
}