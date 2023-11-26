/*



 */

public class Multiply {

    public static int multiply(int a, int b){
        if ( a == 0 || b == 0 ) return 0;
        int absA =  Math.abs(a);
        int absB =  Math.abs(b);
        int sign = (a > 0) == (b > 0) ? 1 : -1;
        int ans = 0;
        // 4 * 5
        // 4 * 4 + 4 * 1
        while ( absB > 0 ) {
            int multiply = 1;
            while( multiply << 1 <= absB ) multiply <<= 1;
            ans += multiply * absA;
            absB -= multiply;
        }
        return ans * sign;
    }

    public static void main(String[] args){
        int a = 9;
        int b = 9;
        System.out.println("ans: " + multiply(a, b));
    }
}
