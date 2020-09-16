
import java.util.HashMap;
import java.util.Map;
/*

https://leetcode.com/problems/decoded-string-at-index/

An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:

If the character read is a letter, that letter is written onto the tape.
If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.

 */
public class DecodeAtIndex {

    public static String find(StringBuilder sb, long K, Map<Integer,Long> repeatMap ) {
        long len = 0;
        for ( int i = 0 ; i < sb.length() ; ){
            ++len;
            long lenHigh = len*repeatMap.getOrDefault(i, 1L);
            if ( K == 1 || len == K || lenHigh == K ) return String.valueOf(sb.charAt(i));
            if ( lenHigh > K ){
                // this is the trick, just keep reducing K
                K-=len;
                len = i = 0;
            }
            else {
                len = lenHigh;
                ++i;
            }
        }
        return null;
    }

    public static String find(String S, int K) {
        StringBuilder sb = new StringBuilder(S);
        Map<Integer,Long> repeatMap = new HashMap<>();
        Integer prevIdx = null;
        long prevNum = 1;
        for( int i = 0; i < sb.length() ;){
            char ch = sb.charAt(i);
            if ( Character.isDigit(ch) ){
                long num = (ch - '0');
                prevNum *= num;
                if ( prevIdx == null ) prevIdx = i;
                sb.deleteCharAt(i);
            }
            else {
                if (prevIdx != null) {
                    repeatMap.put(prevIdx - 1, prevNum);
                }
                prevNum = 1;
                prevIdx = null;
                ++i;
            }
            if ( prevNum > K ) break;
        }
        if ( prevIdx != null ) repeatMap.put(prevIdx-1,prevNum);
        return find(sb, K, repeatMap );
    }


    public static void main(String[] args){
//        String s = "a23";
//        int K = 6;//
//        String s = "leet2code3";
//        int K = 10;
        //hahahaha
//        String s = "ha22";
//        int K = 5;

//        String s = "a2b3c4d5e6f7g8h9";
//        int K = 10;

//        String s = "gl2sld3935dz5wx5r64x";

//String s = "a2345678999999999999999";
//int K = 1;
//        String s =        "a2b3c4d5e6f7g8h9";
//        int K = 3;
//        String s = "y959q969u3hb22odq595";
//        int K = 407;

        String s = "cpmxv8ewnfk3xxcilcmm68d2ygc88daomywc3imncfjgtwj8nrxjtwhiem5nzqnicxzo248g52y72v3yujqpvqcssrofd99lkovg";
        int K = 480551547;
        System.out.println(find(s,K));


        // a2b3c4d5e6f7g8h9


    }
}
