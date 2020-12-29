import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/*

https://leetcode.com/problems/smallest-integer-divisible-by-k
 */
public class SmallestIntDivByK {


    /* very efficient, this is a little gimmicky  */
    public static int smallestRepunitDivByK1(int K) {
        if (K % 2 == 0 || K % 5 == 0) return -1;
        for (int length_N = 1, mod = 0; length_N <= K; ++length_N) {
            mod = (mod * 10 + 1) % K;
            if (mod == 0) return length_N;
        }
        return -1;
    }

    static int len( int num ){
        int count = 0;
        for ( ; num > 0 ; ++count ){
            num/=10;
        }
        return count;
    }

    final static BigInteger ten = BigInteger.valueOf(10);
    final static BigInteger zero = BigInteger.valueOf(0);
    final static Map<Integer,BigInteger> tenPowerCache  = new HashMap<>();
    static private BigInteger tenPow(int i){
        return tenPowerCache.computeIfAbsent(i,(key)->ten.pow(i));
    }
    // extremely slow but intuitive
    // exceeds time limit
    // shows how to use BigInteger
    public static int smallestRepunitDivByK2(int K) {
        if ( K%2 == 0 || K%5 == 0 ) return  -1;
        String strK= String.valueOf(K);
        if ( strK.matches("1+")  ) return strK.length();
        int len = len(K)+1;
        final BigInteger k = BigInteger.valueOf(K);

        BigInteger N = new BigInteger("1".repeat(len));
        for ( int i=len; i <= K ; ++i ){
            N = N.add( tenPow(i) );
            if ( N.mod(k).equals(zero) ) return i+1;
        }
        return -1;
    }

    public static void main( String[] args ){
        long t = System.currentTimeMillis();
        int num = 19927;
        System.out.println(smallestRepunitDivByK1(num));
        System.out.println("time 1: " + (System.currentTimeMillis() - t) );
        t = System.currentTimeMillis();
        System.out.println(smallestRepunitDivByK2(num));
        System.out.println("time 2: " + (System.currentTimeMillis() - t) );
    }
}
