import java.util.*;

/*
*
*  Max meetings
*  input: [6, 3, 1, 5, 2], 8
*  output: 3
*
*
*
 */
public class MeetingsDp {


    static int find( int[] arr, int hours, int idx,  Map<String,Integer> mem){
        if ( hours <= 0 || idx == arr.length ) return 0;
        String key = hours + ":" + idx;
        if ( mem.containsKey(key) ) return mem.get(key);
        int include = 0;
        if ( hours >= arr[idx] ) {
            include = 1 + find(arr, hours - arr[idx], idx + 1,mem);
        }
        int exclude = find(arr, hours, idx+1,mem);
        int result = Math.max(include,exclude);
        mem.put(key,result);
        return result;
    }

    static int find( int[] arr, int hours){
        return find(arr,hours,0, new HashMap<>() );
    }

    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]")).mapToInt(Integer::parseInt).toArray();
        int hours =  Integer.parseInt(args[1]);
        System.out.println(hours);
        System.out.println(Arrays.toString(arr));
        System.out.println(find(arr,hours));
    }
}