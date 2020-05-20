/*
Given a rope of length n meters, cut the rope in different parts of integer lengths 
in a way that maximizes product of lengths of all parts. 
You must make at least one cut. Assume that the length of rope is more than 2 meters.

Examples:
Input: n = 2
Output: 1 (Maximum obtainable product is 1*1)

Input: n = 10
Output: 36 (Maximum obtainable product is 3*3*4)

Time:
   O(N)
*/

public class MaxRopeProduct {

    public static int find(int len) {
        if ( len == 0 ) return 0;
        if ( len == 1 ) return 1;
        int currentMax = len;
        for ( int i = 1; i < len; ++i ){
            currentMax = Math.max(currentMax, find(i) * find(len-i));
        }
        return currentMax;
    }

    public static void main( String[] args ) {
       //int len = Integer.parseInt(args[0]);
        int len = 10;
        System.out.println(find(len));
    }
}