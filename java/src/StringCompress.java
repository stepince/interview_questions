/*


    https://leetcode.com/problems/string-compression/

Given an array of characters, compress it in-place.
The length after compression must always be smaller than or equal to the original array.
Every element of the array should be a character (not int) of length 1.
After you are done modifying the input array in-place, return the new length of the array.


 */

import java.util.Arrays;

public class StringCompress {

    public static int compress(char[] chars) {
        int count = 1;
        int len = 0;
        for ( int i = 1; i < chars.length; ++i){
            if ( chars[i-1] == chars[i] ) {
                ++count;
            }
            else if ( count == 1 ) {
                chars[len++] = chars[i-1];
            }
            else {
                chars[len++] = chars[i-1];
                char[] countStr = String.valueOf(count).toCharArray();
                System.arraycopy(countStr,0,chars,len,countStr.length);
                len += countStr.length;
                count = 1;
            }
        }
        chars[len++] = chars[chars.length-1];
        if ( count > 1 ) {
            char[] countStr = String.valueOf(count).toCharArray();
            System.arraycopy(countStr,0, chars,len,countStr.length);
            len += countStr.length;
        }
        return len;
    }

    public static void main(String[] args){
        char[] chars =  {'a','a','b','b','c','c','c'};
        int len = compress(chars);
        System.out.println(Arrays.copyOfRange(chars,0,len));
    }
}
