public class StringCompareDistance {

    private static boolean find(String str1, String str2, int n, int idx1, int idx2 ) {
        if ( idx1 == str1.length() || idx2 == str2.length() ) return ( Math.abs((str1.length()-idx1) - (str2.length()-idx2)) <= n );
        if ( n < 0 ) return false;
        if ( Character.toUpperCase(str1.charAt(idx1) ) == Character.toUpperCase(str2.charAt(idx2)) ) {
            return find(str1, str2, n, idx1+1, idx2+1 );
        }
        return find(str1, str2, n-1, idx1, idx2+1) || find(str1, str2, n-1, idx1+1, idx2) || find(str1, str2, n-1, idx1+1, idx2+1 );
    }

    public static boolean find(String str1, String str2, int n ) {
        return find(str1, str2, n, 0, 0 );
    }

    public static void main(String[] args){
//        String str1 = args[0];
//        String str2 = args[1];
//        int n = Integer.parseInt(args[2]);
        int n = 1;
        String str1 = "hello";
        String str2 = "heello";
        System.out.println(find(str1,str2,n));
    }
}