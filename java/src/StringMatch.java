public class StringMatch {

    private static boolean find(String str, String pattern, int sIdx, int pIdx ){
        if ( sIdx == str.length() && pIdx == pattern.length() ) return true;
        boolean wildCard = (pIdx < pattern.length() -1) && (pattern.charAt(pIdx+1) == '*');
        if ( sIdx == str.length() && wildCard ) {
            return find(str,pattern,sIdx,pIdx+2);
        }
        if ( pIdx == pattern.length() ) return false;
        boolean dot = (pattern.charAt(pIdx) == '.');
        boolean isEqualsChar = str.charAt(sIdx) == pattern.charAt(pIdx);
        if ( dot && wildCard ) {
            // 0 case, 1 case, repeat case
            return find(str,pattern,sIdx,pIdx+2) || find(str,pattern,sIdx+1,pIdx+2) || find(str,pattern,sIdx+1,pIdx);
        }
        else if ( isEqualsChar && wildCard ) {
            // 1 case, repeat case
            return find(str,pattern,sIdx+1,pIdx+2) || find(str,pattern,sIdx+1,pIdx);
        }
        // 0 case and no match
        else if ( wildCard ){
            // 0 case, skip
            return find(str,pattern,sIdx,pIdx+2);
        }
        else if ( dot ){
            return find(str,pattern,sIdx+1,pIdx+1);
        }
        // simple case of matching character
        else if ( isEqualsChar ){
            return find(str,pattern,sIdx+1,pIdx+1);
        }
        return false;
    }

    public static boolean find(String str, String pattern){
        return find(str,pattern,0,0);
    }

    public static void main(String[] args){
        System.out.println("test");
        String str = args[0].trim();
        String pattern = args[1].trim();
        System.out.println(find(str,pattern));
    }
}