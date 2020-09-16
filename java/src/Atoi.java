/*

https://leetcode.com/problems/string-to-integer-atoi/submissions/
 */

public class Atoi {

    public static int myAtoi(String str) {
        int start = 0;
        int base = 0;
        int neg = 1;

        char[] arry = str.toCharArray();

        for ( ; start < arry.length; ++start ){
            if (arry[start] == ' ') continue;
            break;
        }
        if ( start == arry.length ) return 0;
        if ( arry[start] == '-' ){
            ++start;
            neg=-1;
        }
        else if ( arry[start] == '+' ){
            ++start;
        }
        if ( start == arry.length || arry[start] > '9' || arry[start] < '0' ) return 0;

        int end = arry.length-1;
        for ( ; end >= start; --end ){
            if ( arry[end] > '9' || arry[end] < '0' ) continue;
            break;
        }
        long val = 0;
        for ( int i = end; i >= start; --i ){
            if ( arry[i] > '9' || arry[i] < '0' ) {
                base = 0;
                val = 0;
                continue;
            }
            int num = arry[i] - '0';
            val += Math.pow(10,base++)*num;
            if ( neg == 1 && val >= Integer.MAX_VALUE ) return Integer.MAX_VALUE;
            else if ( neg == -1 && val >= Integer.MAX_VALUE + (long)1) return Integer.MIN_VALUE;
        }
        return neg*(int)val;
    }

    public static void main(String[] args){
        String s = "    -42";
        System.out.println(myAtoi(s));
    }

}
