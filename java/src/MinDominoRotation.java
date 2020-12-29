/*

https://leetcode.com/problems/minimum-domino-rotations-for-equal-row

In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the ith domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the ith domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.
 */

public class MinDominoRotation {
    public int minDominoRotations(int[] A, int[] B) {
        int[] arrA = new int[7];
        int[] arrB = new int[7];
        int maxA = 0;
        int maxAFreq = 0;
        for ( int num: A ){
            if ( ++arrA[num] > maxAFreq ){
                maxAFreq = arrA[num];
                maxA=num;
            }
        }

        int maxB = 0;
        int maxBFreq = 0;
        for ( int num: B ){
            if ( ++arrB[num] > maxBFreq ){
                maxBFreq = arrB[num];
                maxB=num;
            }
        }

        int rotate = 0;
        if ( maxAFreq > maxBFreq ){
            for ( int i = 0; i < A.length; ++i ){
                rotate = ( A[i] != maxA && B[i] == maxA ) ? (rotate+1) : rotate ;
            }
            return A.length == (rotate+maxAFreq) ? rotate : -1;
        }
        else {
            for ( int i = 0; i < A.length; ++i ){
                rotate = ( B[i] != maxB && A[i] == maxB ) ? (rotate+1) : rotate ;
            }
            return B.length == (rotate+maxBFreq) ? rotate : -1;
        }
    }
}
