import java.util.Arrays;

public class PrimeNumberGenerator {

    public static void generate(int n){
        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime,true);
        prime[0] = false;
        // the outer loop is never more that i*i
        for (int i = 2; i*i <= n; ++i){
            if ( prime[i] ){
                // following a prime number pattern
                // (p+1), p(p+2), p(p+3)
                for (int j = i * 2; j <= n; j+=i ) {
                    prime[j] = false;
                }
            }
        }

        String comma = "";
        for ( int i= 0; i < n; ++i ){
            if( prime[i] ){
                System.out.print( comma + i );
                comma = ",";
            }
        }
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        generate(n);

    }
}