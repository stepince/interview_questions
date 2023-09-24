/*

https://leetcode.com/problems/string-to-integer-atoi/submissions/
 */

public class Atoi {

    public static int myAtoi(String str) {
        char[] chars = s.trim().toCharArray();
        long base = 1;
        long val = 0;
        long sign = 1;
        int start = 0;
         
        if (start == chars.length) return 0;
        if ( chars[start] == '-') {
           ++start;
           sign = -1;   
        } else if ( chars[start] == '+' ) {
           ++start;
        }
        for(int i = chars.length -1; i >=start ; --i ) {
           long num = chars[i] - '0';
           if ( num >= 0 && num <= 9 ){
              val += base * num;
              base = Math.min(Integer.MAX_VALUE, base *= 10);
           } else {
               base = 1;
               val = 0;
           }
        }
        return sign == 1 ? (int)Math.min(Integer.MAX_VALUE,sign * val) : (int)Math.max(Integer.MIN_VALUE,sign * val);
    }

    public static void main(String[] args){
        String s = "    -42";
        System.out.println(myAtoi(s));
    }

}
