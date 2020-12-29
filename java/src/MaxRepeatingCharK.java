/*

Given a string of consecutive repeated characters and max value,
return another string of max repeated characters :
   for ex: aaaaabbbbbcccdd and max is 2 should be aabbccdd


 */

public class MaxRepeatingCharK {

    public static String find( String str, int K ){
        if ( str == null || str.length() <= K ) return str;
        char[] chars = str.toCharArray();
        int count = 1;
        StringBuilder result = new StringBuilder(String.valueOf(chars[0]));
        for ( int i = 1; i < chars.length; ++i ){
            if ( chars[i] != chars[i-1] ){
                result.append(chars[i]);
                count=1;
            }
            else if ( ++count <= K ){
                result.append(chars[i]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args){
        String str  = "aaaaabbbbbccdd";
        int K = 2;
        System.out.println(find(str,K));
    }
}
