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

public class MaxRopeProductDp {

    public static int find(int len, Integer[] mem) {
        if ( len == 0 ) return 0;
        if ( len == 1 ) return 1;
        if ( mem[len] != null ) return mem[len];
        // initialize to the default len
        int max = len;
        for ( int i = 1; i < len; ++i ){
            max = Math.max(max, find(i,mem) * find(len-i,mem));
        }
        return mem[len] = max;
    }

    public static int find(int len) {
        return find(len,new Integer[len+1] );
    }

    public static void main( String[] args ) {
        //int len = Integer.parseInt(args[0]);
        int len = 10;
        System.out.println(find(len));
    }
}