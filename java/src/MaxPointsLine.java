/*

https://leetcode.com/problems/max-points-on-a-line/

 */

public class MaxPointsLine {

    static boolean sameLine( int[][] points, int idx1, Integer idx2, Integer idx3 ){
        if ( idx2 == null || idx3 == null  ) return true;

        int[] a = points[idx1];
        int[] b = points[idx2];
        int[] c = points[idx3];
        //[ Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By) ] / 2
//        long total = ((long)a[0] * (long)( b[1] - c[1] )) +
//                ((long)b[0] * (long)( c[1] - a[1] )) +
//                ((long)c[0] * (long)( a[1] - b[1] ));
//        return total == 0;
        long x1 = a[0] - b[0];
        long x2 = a[1] - b[1];
        long y1 = b[0] - c[0];
        long y2 = b[1] - c[1];
        return (x1 * y2) == (x2 * y1);

//        return (x1 * y2) == (x2 * y1);
//        float slope1 = (float)(b[1] - a[1])/(b[0] - a[0]);
//        float slope2 = (float)(c[1] - a[1])/(c[0] - a[0]);
//        return slope1 == slope2;
    }

    static int find( int[][] points, int idx1, Integer idx2, Integer idx3, int count){
        if ( idx1 == points.length ) return count;
        boolean same = sameLine(points,idx1, idx2, idx3);
        int inclCount = same ? count+1 : count;

        if ( idx2 != null && idx3 != null ) return find( points, idx1+1, idx2, idx3, inclCount);
        Integer inclIdx2 = idx2;
        Integer inclIdx3 = idx3;
        if ( inclIdx2 == null ) inclIdx2 = idx1;
        // inclIdx2 and inclIdx3 should be unique
        else if ( inclIdx2 != idx1 && !(points[inclIdx2][0] == points[idx1][0] && points[inclIdx2][1] == points[idx1][1]) ) inclIdx3 = idx1;
        return Math.max(find( points, idx1+1, inclIdx2, inclIdx3, inclCount), find( points, idx1+1,idx2, idx3, count) );
    }

    public static int maxPoints(int[][] points) {
        if ( points.length < 3 ) return points.length;
        return find(points,0, null, null, 0);
    }

    public static void main(String[] args){

//        int[][] points = {{1,1},{2,1},{2,2},{1,4},{3,3}};
//        int[][] points = {{0,0},{1,1},{0,0}};
//        int[][] points = { {0,0},{1,65536},{65536,0}};
//        int[][] points = {{1,1},{1,1},{0,0},{3,4},{4,5},{5,6},{7,8},{8,9}};
        int[][] points = {{1,1},{1,1},{0,0},{3,4},{4,5},{5,6},{7,8},{8,9}, {11,12},{11,12},{11,12},{11,12},{11,12},{11,12},{10,10},{10,10},{10,10},{10,10},{10,10},{10,10},{10,10},{10,10}};
//        int[][] points = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        long t = System.currentTimeMillis();
        System.out.println(maxPoints(points));
        System.out.println("time: " + ( System.currentTimeMillis() - t));
//        t = System.currentTimeMillis();
//        System.out.println(maxPoints2(points));
//        System.out.println("time 2: " + ( System.currentTimeMillis() - t));
    }
}
