/*
Given a rod of length n inches and an array of prices that contains prices of all pieces
of size smaller than n. Determine the maximum value obtainable by cutting up the rod 
and selling the pieces. For example, if length of the rod is 8 and the values of 
different pieces are given as following, then the maximum obtainable value is 
22 (by cutting in two pieces of lengths 2 and 6)


length   | 1   2   3   4   5   6   7   8  

price    | 1   5   8   9  10  17  17  20


And if the prices are as following, then the maximum obtainable value is 24 
(by cutting in eight pieces of length 1)

length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 3   5   8   9  10  17  17  20

*/


public class RodCuttingDp {

    private static int find(int len, int[] prices, Integer[] mem){
        if ( len == 0 ) return 0;

        // initialize to the default price for len else 0
        if ( mem[len] != null ) return mem[len];

        int max = ( len <= prices.length ) ? prices[len-1] : 0;
       
        for ( int i = 1; i < len; ++i ) {
            max = Math.max(max,find(len-i,prices,mem) + find(i,prices,mem));
        }
        return mem[len] = max;
    }

    public static int find(int len, int[] prices){
        return find(len,prices, new Integer[len+1]);
    }

    public static void main(String[] args) {
//        int len = Integer.parseInt(args[0]);
//        int[] prices = Arrays.stream(args[1].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int[] prices = {3, 5,   8 ,  9,  10,  17,  17,  20};
        int len = 8;
        System.out.println(find(len,prices));
    }
}