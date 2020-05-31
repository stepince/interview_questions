/*

   Find the median of two sorted arrays.
   This solution uses recursion. Recursion allows
   for you to visualize the algorithm
   I think it is easier to memorize the recursive solutions
Time:
O( lg(m+n))
 */

public class MedianSortedArray {

    static class Partition {
        final int x;
        final int y;
        Partition(int x , int y ){
            this.x = x;
            this.y = y;
        }
    }

    private static double avg( int x, int y ){
        return (double)(x+y)/2;
    }

    private static int safeGet(int[] arr, int idx){
        if ( idx == -1 ) {
            return Integer.MIN_VALUE;
        }
        else if ( idx == arr.length ){
            return  Integer.MAX_VALUE;
        }
        else {
            return arr[idx];
        }
    }

    private static int min( int[] x, int[] y, int idxX, int idxY ){
        return Math.min( safeGet(x,idxX), safeGet(y,idxY));
    }

    private static int max( int[] x, int[] y, int idxX, int idxY ){
        return Math.max( safeGet(x,idxX), safeGet(y,idxY));
    }

    public static double median( int[] x ){
        final int mod = x.length % 2 ;
        return ( mod == 1 ) ? x[x.length/2]  : avg( x[x.length/2-1], x[x.length/2] );
    }

    private static double median( Partition p, int[] x, int[] y){
        final int mod = (x.length + y.length) % 2 ;
        return ( mod == 1 ) ? max( x, y, p.x-1, p.y-1) : avg( max( x, y, p.x-1, p.y-1) , min( x, y, p.x, p.y) );
    }

    private static Partition findPartition(int[] x, int[] y, int low, int high){
        final int midX = (low + high)/2;
        final int midY = (x.length + y.length + 1)/2 - midX;

        // partition the array so that following criteria is met
        //  x1, x2 | x3, x4
        //  y1, y2 | y3, y4
        //   x2 <= y3
        //   y2 <= x3
        final int xLeft = safeGet(x,midX-1);
        final int xRight = safeGet(x,midX);
        final int yLeft = safeGet(y,midY-1);
        final int yRight = safeGet(y,midY);

        if ( xLeft > yRight ){
            return findPartition(x, y, low, midX);
        }
        else if ( yLeft > xRight ){
            // check for edge case, if at the end
            // if at end don't recurse, just mark the x array end with the size of the array
            return midX == x.length -1 ? new Partition(x.length, midY-1 ) : findPartition(x, y, midX, high);
        }
        else {
            return new Partition(midX,midY);
        }
    }

    public static double findMedianSortedArrays(int[] arr1, int[] arr2) {
        // invalid????
        assert !(arr1.length == 0 && arr2.length == 0);
        if ( arr1.length == 0 ) return median(arr2);
        if ( arr2.length == 0 ) return median(arr1);
        return arr1.length <= arr2.length
                ? median( findPartition(arr1,arr2,0,arr1.length), arr1, arr2)
                : median( findPartition(arr2,arr1,0,arr2.length), arr2, arr1);
    }

    public static void main(String[] args){
        int[] arr1 = {2};
        int[] arr2 = {5,6,8};
//        int[] arr1 = {3};
//        int[] arr2 = {1,2};
        System.out.println(findMedianSortedArrays(arr1, arr2));
//        arr1 = new int[]{1,3,4,5,6};
//        arr2 = new int[]{};
//        System.out.println(findMedianSortedArrays(arr1,arr2));
//        arr1 = new int[]{};
//        arr2 = new int[]{1,3,4,5,6};
//        System.out.println(findMedianSortedArrays(arr1,arr2));
    }

}
