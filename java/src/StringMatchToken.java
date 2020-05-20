import java.util.*;

public class StringMatchToken {

    private static boolean find(String str, List<String> patterns, int sIdx, int pIdx){
        if ( sIdx == str.length() && pIdx == patterns.size() ) return true;
        if ( sIdx == str.length() && patterns.get(pIdx).endsWith("*") ) {
            return find(str, patterns, sIdx, pIdx+1);
        }
        if ( pIdx == patterns.size() ) return false;

        String pattern = patterns.get(pIdx);
        if ( pattern.equals(".*") ){
            return find(str, patterns, sIdx+1, pIdx) || find(str, patterns, sIdx+1, pIdx+1) || find(str, patterns, sIdx, pIdx+1);
        }
        else if ( pattern.endsWith("*") && patterns.get(pIdx).charAt(0) == str.charAt(sIdx) ){
            return find(str, patterns, sIdx+1, pIdx+1) || find(str, patterns, sIdx+1, pIdx);
        }
        // zero case * (ignore skip this pattern)
        else if ( pattern.endsWith("*") ){
            return find(str, patterns, sIdx, pIdx+1);
        }
        else if ( pattern.equals(".") ){
            return find(str, patterns, sIdx+1, pIdx+1);
        }
        else if ( str.substring(sIdx).startsWith(pattern) ){
            return find(str, patterns, sIdx+pattern.length(), pIdx+1);
        }
        return false;
    }

    private static List<String> parseTokens(String str){
        LinkedList<String> tokens = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(str,".*",true);
        String prev = "";
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if ( token.equals("*") && prev.equals(".") ){
                tokens.remove();
                tokens.add(".*");
            }
            else if ( token.equals("*") ){
                tokens.remove();
                tokens.add(prev.substring(0,prev.length()-1));
                tokens.add(prev.charAt(prev.length()-1)+"*");
            }
            else {
                tokens.add(token);
            }
            prev = token;
        }
        return tokens;
    }

    public static boolean find(String str, List<String> patterns) {
        return find(str,patterns,0,0);
    }

    public static void main(String[] args){
        String str = args[0].trim();
        String pattern = args[1].trim();
/*
 *       String tokenAny = "((?<=.\\*)|(?=.\\*))";
 *       String tokenOne = "(((?<=\\.)(?!\\*))|((?=\\.)(?!\\*)))";
*        String tokenPattern = "("  + tokenOne + ")|(" + tokenAny + ")";
 */
        // could use this to break up the pattern, very hairy
        // under time constraints it is better to programmatically
        // parse or just use the indices directly instead of breaking the words up
        //String[] tokens = pattern.split(tokenPattern);
        System.out.println(find(str,parseTokens(pattern)));
    }
}