import java.util.HashMap;
import java.util.Map;

public class StringCompareDistanceDp {
    private static int count;

    private static boolean find(String str1, String str2, int n, int idx1, int idx2, Map<String,Boolean> mem) {

        if ( idx1 == 0 || idx2 == 0 ) return ( Math.abs(idx1 - idx2) <= n );
        if ( n < 0 ) return false;
        ++count;
        String key = idx1 + ":" + idx2 + ":" + n;
        if ( mem.containsKey(key) ) return mem.get(key);
        if ( Character.toUpperCase(str1.charAt(idx1-1) ) == Character.toUpperCase(str2.charAt(idx2-1)) ) {
            return find(str1, str2, n, idx1-1, idx2-1,mem );
        }
        boolean result = find(str1, str2, n-1, idx1, idx2-1,mem) || find(str1, str2, n-1, idx1-1, idx2, mem );
        mem.put(key,result);
        return result;
    }

    public static boolean find(String str1, String str2, int n ) {
        int len1 = str1.length();
        int len2 = str2.length();
        // no change to get there
        if ( Math.abs(len1 - len2) > n ) return false;
        return find(str1, str2, n, len1, len2, new HashMap<>() );
    }

    public static void main(String[] args){
        String str1 = args[0];
        String str2 = args[1];
        int n = Integer.parseInt(args[2]);
        System.out.println(find(str1,str2,n));
        System.out.println(count);
    }
}