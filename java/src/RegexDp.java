/*

  Google interview question
  handle *
  handle .

  "*" star remember you have to look ahead
   ".*" you have two choices

   [a]* two choice but you can check for char equality
   e.g aa a* skip char
       ab a* skip pattern
 */

public class RegexDp {

    static boolean regex(char[] chars, int sIdx, char[] p , int pIdx, Boolean[][] mem ){

        boolean star = ( pIdx + 1 < p.length && p[pIdx+1] == '*' );
        if ( chars.length == sIdx && p.length == pIdx ){
            return true;
        }
        else if ( p.length == pIdx ){
            return false;
        }
        else if ( chars.length == sIdx ) {
            return star && regex(chars, sIdx , p, pIdx+2, mem);
        }
        else if ( mem[sIdx][pIdx] != null ) {
            return mem[sIdx][pIdx];
        }
        else if ( p[pIdx] == '*' && pIdx == 0 ) {
            return mem[sIdx][pIdx] = regex(chars, sIdx, p, pIdx+1, mem );
        }
        else if ( star && p[pIdx] == '.'  ) {
            return mem[sIdx][pIdx] = (regex(chars, sIdx , p, pIdx+2, mem) || regex(chars, sIdx+1 , p, pIdx, mem));
        }
        else if ( star && chars[sIdx] == p[pIdx] ) {
            return mem[sIdx][pIdx] = (regex(chars, sIdx , p, pIdx+2, mem) || regex(chars, sIdx+1 , p, pIdx, mem));
        }
        else if ( star && chars[sIdx] != p[pIdx] ) {
            return mem[sIdx][pIdx] = regex(chars, sIdx, p, pIdx+2, mem) ;
        }
        else if ( p[pIdx] == '.' ) {
            return mem[sIdx][pIdx] = regex(chars, sIdx+1 , p, pIdx+1, mem );
        }
        else {
            return mem[sIdx][pIdx] = (chars[sIdx] == p[pIdx] && regex(chars, sIdx+1 , p, pIdx+1, mem ));
        }
    }

    public static boolean regex(String str, String p ){
        return regex(str.toCharArray(), 0 , p.toCharArray(), 0, new Boolean[str.length()][p.length()] );
    }

    public static void main(String[] args){
        String str = "afdddddddddddddfas";
        String pattern = "afd*.*";
        System.out.println(regex(str,pattern));
    }
}
