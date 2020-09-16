public class PrimePalindrome {

    static boolean isPrime( int x ){
        if ( x <= 1 ) return false;
        for ( int i = 2; i*i <= x; ++i ){
            if ( x%i == 0 ) return false;
        }
        return true;
    }

    static String reverse(String str) {
        char[] chars = new char[str.length()];
        for ( int i = 0; i < chars.length; ++i ){
            chars[i] = str.charAt(chars.length-1-i);
        }
        return new String(chars);
    }

    static public int primePalindrome(int N) {
        String str = String.valueOf(N);
        int len = str.length();
        if ( len == 1 ){
            for ( int i = N; i <= 9; ++i ){
                if ( isPrime(i) ) return i;
            }
            return 11;
        }

        int max = Integer.parseInt("9".repeat(len));
        int root = Integer.parseInt(str.substring(0,len/2));
        if ( root%10 != 0 ) --root;
        int maxRoot = Integer.parseInt("9".repeat( String.valueOf(root).length()));

        if ( str.length()%2 == 0 ){
            for ( int i = root; i <= maxRoot; ++i ){
                String numStr = String.valueOf(i);
                numStr = numStr + reverse(numStr);
                int num = Integer.parseInt(numStr);
                if ( num >= N && isPrime(num) ) return num;
            }
        }
        else {
            for ( int i = root; i <= maxRoot; ++i ){
                for ( int centerNum = 0; centerNum < 10; ++centerNum ){
                    String numStr = String.valueOf(i);
                    numStr = numStr + centerNum + reverse(numStr);
                    int num = Integer.parseInt(numStr);
                    if ( num >= N && isPrime(num) ) return num;
                }
            }
        }
        return primePalindrome(max+1);
    }

    public static void main(String[] args ){
//        int val  = 9989900;
        int val =  102;
        long t = System.currentTimeMillis();
        System.out.println(primePalindrome(val));
        System.out.println("time = " + ( System.currentTimeMillis() - t ) );
    }
}
