/*
  Number of Ways to Split a String
   https://leetcode.com/problems/number-of-ways-to-split-a-string/

Given a binary string s (a string consisting only of '0's and '1's), we can split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).

Return the number of ways s can be split such that the number of characters '1' is the same in s1, s2, and s3.

Since the answer may be too large, return it modulo 10^9 + 7.

 */

public class SplitStringNumWays {
    final static long MOD = 1000000007;
    static int countAt( char[] chars, int idx, int count){
        int num = 0;
        for ( int i = idx; i < chars.length; ++i ){
            if ( chars[i] == '1') ++num;
            if ( num == count ) return i;
        }
        return chars.length;
    }

    public static int numWays(String s) {
        int ones = 0;

        char[] chars = s.toCharArray();
        for ( char ch : chars ){
            if ( ch == '1') ++ones;
        }

        /* if zero ones then split zeros */
        if ( ones == 0 ) {
            // formula is summation e.g for a string len of 5 =  3 + 2 + 1
            long n = chars.length;
            return (int)((n-1)*(n-2)/2 % MOD);
        }
        if ( ones%3 != 0 ) return 0;
        int count = ones/3;

        int idx1End = countAt(chars,0,count);
        int idx2Start = countAt(chars,idx1End+1,1);

        int idx2End = countAt(chars,idx1End+1,count);
        int idx3Start = countAt(chars,idx2End+1,1);

        long part1 = idx2Start - idx1End;
        long part2 = idx3Start -  idx2End;

        return (int)(part1 * part2 % MOD);
    }

    public static void main(String[] main ){
        System.out.println(numWays("0000000"));
        System.out.println(numWays("010010"));
    }
}
