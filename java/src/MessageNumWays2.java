/*
https://leetcode.com/problems/decode-ways-ii/

   number of ways to decode message
   a = "1"
   b = "2"
   c = "3"

   l = "12"
   z 26

   find("12")
   ab
   l

   total = 2

Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
Also, since the answer may be very large, you should return the output mod 109 + 7.
   note: 0 is an illegal character

 */
public class MessageNumWays2 {

    static int numDecodings(char[] chars, int idx, Integer[] mem){
        if ( idx == chars.length ) return 1;
        // '0' is illegal char
        if ( chars[idx] == '0' ) return 0;

        if ( mem[idx] != null ) return mem[idx];
        long result = 0;
        // one path
        if ( chars[idx] == '*' ){
            // 1-9 all
            for ( int i = 0; i < 9 ; ++i ){
                result += numDecodings(chars,idx+1,mem);
            }
        }
        else {
            // 1-9 any
            result += numDecodings(chars,idx+1,mem);
        }
        // if at end of string just return result;
        if ( idx == chars.length -1 ) return (int)result;
        if ( chars[idx] == '*' && chars[idx+1] == '*' ) {
            // 11-19 all
            for ( int i = 0; i < 9 ; ++i ){
                result += numDecodings(chars,idx+2,mem);
            }
            // 21-26 all
            for ( int i = 0; i < 6 ; ++i ){
                result += numDecodings(chars,idx+2,mem);
            }
        }
        else if ( chars[idx] == '*' && chars[idx+1] == '0' ) {
            // 10 any
            result += numDecodings(chars,idx+2,mem);
            // 20 any
            result += numDecodings(chars,idx+2,mem);
        }
        else if ( chars[idx] == '*' && chars[idx+1] >= '1' && chars[idx+1] <= '6' ) {
            // 11-16 any
            result += numDecodings(chars,idx+2,mem);
            // 21-26 any
            result += numDecodings(chars,idx+2,mem);
        }
        else if ( chars[idx] == '*' && chars[idx+1] >= '7' && chars[idx+1] <= '9' ) {
            // 17-19 any
            result += numDecodings(chars,idx+2,mem);
        }
        else if ( chars[idx] == '2' && chars[idx+1] == '*' ) {
            // 21-26 all
            for ( int i = 0; i < 6 ; ++i ){
                result += numDecodings(chars,idx+2,mem);
            }
        }
        else if ( chars[idx] == '2' && chars[idx+1] <= '6' && chars[idx+1] >= '0' ) {
            // 21-26 any
            result += numDecodings(chars,idx+2,mem);
        }
        else if ( chars[idx] == '1' && chars[idx+1] == '*' ) {
            // 11-19 all
            for ( int i = 0; i < 9 ; ++i ){
                result += numDecodings(chars,idx+2,mem);
            }
        }
        else if ( chars[idx] == '1' ) {
            // 11-19 any
            result += numDecodings(chars,idx+2,mem);
        }
        return mem[idx] = (int)result;
    }

    public static int numDecodings(String s) {
        char[] chars = s.toCharArray();
        int result = numDecodings(chars,0 , new Integer[chars.length+1]);
        // mod 1000000007 is just a requirement for large values
        return result % 1000000007;
    }

    public static void main(String[] args){
        String str = "100";
        System.out.println(numDecodings(str));
    }
}
