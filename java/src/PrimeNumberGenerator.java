import java.util.Arrays;

public class PrimeNumberGenerator {

    public static void generate(int n){
        if ( n < 2 ) return;
        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime,true);
        prime[0] = false;
        prime[1] = false;
        // the outer loop is never more that i*i
        for (int i = 2; i*i <= n; ++i){
            if ( prime[i] ){
                // following a prime number pattern
                //  i^2, i^2+i, i^2+2i, i^2+3i, ..., not exceeding n do (all multiples of i are not prime)
                for (int j = i*i; j <= n; j+=i ) {
                    prime[j] = false;
                }
            }
        }
        String comma = "";
        for ( int i= 0; i <= n; ++i ){
            if( prime[i] ){
                System.out.print( comma + i );
                comma = ",";
            }
        }
    }

    public static void main(String[] args){
//        int n = Integer.parseInt(args[0]);
        int n  = 11;
        generate(n);
    }
}