import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/*
find the number of sub-arrays of having negative sums.
input: 1 -2 4 -5 1

output: 9
1 -2 4 -5 1
2
-1 -1 1
2
-2 1
1
-1
1

 */
public class SubArrayNegativeCount {


    private static int find(int[] arr, int idx, int total, int startIdx, Set<String> mem){
        String key = startIdx + ":" + idx;
        if ( mem.contains(key) || idx >= arr.length ) return 0;
        mem.add(key);
        total += arr[idx];

        if ( total == 0 ) {
            return find(arr,idx+1, total, startIdx,mem);
        }
        int incr = total < 0 ? 1 : 0;

        return incr +
                find(arr,idx+1, total, startIdx,mem) +
                find(arr,idx+1, 0, idx+1,mem);
    }
    public static int find(int[] arr){
        return find(arr,0, 0,0, new HashSet<>()) ;
    }

    private static int find2(int[] arr, int length){
        int count = 0;
        if ( length == 0 ) return 0;
        if ( length == 1 ) return arr[0] < 0 ? 2 : 0;
        for ( int i = 0; i < length; ++i){
            if ( arr[i] < 0 ) ++count;
        }

        for ( int i = 0, j = 0; i < length; ++i, ++j){
            if ( i < arr.length -1 ) {
                arr[j] = arr[i] + arr[++i];
                if ( arr[j] < 0) ++count;
            }
            else {
                arr[j] = arr[i];
            }
        }
        count += find2( arr, (int)Math.ceil(length/2));
        return count;
    }

    public static int find2(int[] arr){
        return find2(arr,arr.length) ;
    }

    public static void main(String[] args){
       int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       System.out.println(find(arr));
       System.out.println(find2(arr));
    }
}