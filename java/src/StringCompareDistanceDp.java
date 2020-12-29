import java.util.HashMap;
import java.util.Map;

public class StringCompareDistanceDp {

    private static boolean find(String str1, String str2, int n, int idx1, int idx2, Map<String,Boolean> mem) {

        if ( idx1 == str1.length() || idx2 == str2.length() ) return ( Math.abs((str1.length()-idx1) - (str2.length()-idx2)) <= n );

        if ( n < 0 ) return false;
        String key = idx1 + ":" + idx2 + ":" + n;
        if ( mem.containsKey(key) ) return mem.get(key);
        if ( Character.toUpperCase(str1.charAt(idx1) ) == Character.toUpperCase(str2.charAt(idx2)) ) {
            return find(str1, str2, n, idx1+1, idx2+1,mem );
        }
        boolean result = find(str1, str2, n-1, idx1+1, idx2+1,mem) || find(str1, str2, n-1, idx1, idx2+1,mem) || find(str1, str2, n-1, idx1+1, idx2, mem );
        mem.put(key,result);
        return result;
    }

    public static boolean find(String str1, String str2, int n ) {
        return find(str1, str2, n, 0, 0, new HashMap<>() );
    }

    public static void main(String[] args){
//        String str1 = args[0];
//        String str2 = args[1];
//        int n = Integer.parseInt(args[2]);
        int n = 1;
        String str1 = "hello";
        String str2 = "heeello";
        System.out.println(find(str1,str2,n));
    }
}