/*

  Google interview question
  handle *
  handle .

  "*" star remember you have to look ahead
   ".*" you have two choices

   [a]* two choice you can check for char equality
   e.g aa a* skip char
       ab a* skip pattern
 */

public class Regex {

    static boolean regex(char[] chars, int sIdx, char[] p , int pIdx ){
        boolean star = ( pIdx + 1 < p.length && p[pIdx+1] == '*' );
        if ( chars.length == sIdx && p.length == pIdx ) {
            return true;
        }
        else if ( p.length == pIdx ){
            return false;
        }
        else if ( chars.length == sIdx ) {
            return star && regex(chars, sIdx , p, pIdx+2);
        }
        else if ( p[pIdx] == '*' && pIdx == 0 ) {
            return regex(chars, sIdx, p, pIdx+1 );
        }
        else if ( star && p[pIdx] == '.'  ) {
            return regex(chars, sIdx , p, pIdx+2) || regex(chars, sIdx+1 , p, pIdx);
        }
        else if ( star && chars[sIdx] == p[pIdx] ) {
            return regex(chars, sIdx , p, pIdx+2) || regex(chars, sIdx+1 , p, pIdx );
        }
        else if ( star && chars[sIdx] != p[pIdx] ) {
            return regex(chars, sIdx, p, pIdx+2) ;
        }
        else if ( p[pIdx] == '.' ) {
            return regex(chars, sIdx+1 , p, pIdx+1 );
        }
        else {
            return chars[sIdx] == p[pIdx] && regex(chars, sIdx+1 , p, pIdx+1 );
        }
    }

    public static boolean regex(String str, String p ){
        return regex(str.toCharArray(), 0 , p.toCharArray(), 0 );
    }

    public static void main(String[] args){
        String str = "aaa";
        String pattern = "a*.";
        System.out.println(regex(str,pattern));
    }
}
