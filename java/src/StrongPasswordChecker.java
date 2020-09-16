/*

https://leetcode.com/problems/strong-password-checker/
Hard problem
-------------------
A password is considered strong if below conditions are all met:

It has at least 6 characters and at most 20 characters.
It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).

Note: extremely hard solution.  1 ms runtime on leetcode
 */

import java.util.*;

public class StrongPasswordChecker {

    static boolean isUpper(char ch){
        return ch >= 'A' && ch <= 'Z';
    }

    static boolean isLower(char ch){
        return ch >= 'a' && ch <= 'z';
    }

    static boolean isDigit(char ch){
        return ch >= '0' && ch <= '9';
    }

    static int max(int a, int b, int c){
        if ( a >= b && a >= c ) return a;
        if ( b >= a && b >= c ) return b;
        return c;
    }

    static int find(String s) {
        final char[] arry = s.toCharArray();
        final int strlen = arry.length;
        if ( strlen < 4 ) return 6 - strlen;

        int partitionSize = 1;
        char ch = arry[0];
        boolean upper = isUpper(ch);
        boolean lower = isLower(ch);
        boolean digit = isDigit(ch);

        int delete = Math.max(0,strlen - 20);
        int insert = Math.max(0,6 - strlen);
        int replace = 0;
        int flip = 0;

        // give a priority based on the mod and then the size
        PriorityQueue<Integer> repeatPartitions = new PriorityQueue<>( (a,b) -> {
            int mod = a%3 - b%3;
            return mod == 0 ? a - b : mod;
        });

        for( int i = 1; i < strlen; ++i ){
            ch = arry[i];
            if ( !upper && isUpper(ch) ) {
                upper = true;
            }
            else if ( !lower && isLower(ch) ) {
                lower = true;
            }
            else if ( !digit && isDigit(ch) ) {
                digit = true;
            }

            if ( ch == arry[i-1] ){
                ++partitionSize;
            }
            else {
                if ( partitionSize >= 3 ) repeatPartitions.add(partitionSize);
                partitionSize = 1;
            }
        }
        if ( partitionSize > 2 ) repeatPartitions.add(partitionSize);
        if ( !lower ) ++replace;
        if ( !digit ) ++replace;
        if ( !upper ) ++replace;

        if ( insert > 0 ){
            int size = repeatPartitions.isEmpty() ? 0 : repeatPartitions.peek();
            return max( size/3, insert, replace );
        }

        int d = delete;
        while( d-- > 0  && !repeatPartitions.isEmpty() ){
            int item = repeatPartitions.poll() - 1;
            if ( item > 2 ) repeatPartitions.add(item);
        }

        int r = replace;
        while( r-- > 0  && !repeatPartitions.isEmpty() ) {
            int item = repeatPartitions.poll() - 3;
            while( item > 2 && r-- > 0 ) item -= 3;
            if ( item > 2 ) repeatPartitions.add(item);
        }

        if ( repeatPartitions.size() == 0 ){
            return replace + delete;
        }

        for( int partition : repeatPartitions) {
            flip += partition/3;
        }
        return flip + replace + delete;
    }

    public static void main(String[] args){
//      String s = "1Abababcaaaabababababa";
//        String s = "abababababababababaaa";
//        String s = args[0];
//        String s = "hello2";

        String s = "ABABABABABABABABABAB1";
//        String s = "1111111";
//      String s = "aaa123";
//        String s = "1234567890123456Baaaaa";
//      String s = "QQQQQ";
//        String s = "aaaaaaaaaaaaaaaaaaaaa";
//        String s = "1234567890123456Baaaaa";
//        String s ="aaaabbaaabbaaa123456A";

//        String s = "aaaaabbbb1234567890ABA";
//        String s = "..................!!!";
        System.out.println(find(s));
    }

}
