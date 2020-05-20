import java.util.*;

/*
 *
 *  find the maximum amount of meetings
 *  input:  [8,3,1], 8
 *  output: 2
 *
 *
 */
public class Meetings {

    static int find( int[] arr, int hours, int idx){
       if ( hours <= 0 || idx == arr.length ) return 0;
       int include = 0;
       if ( hours >= arr[idx] ) {
           include = 1 + find(arr, hours - arr[idx], idx + 1);
       }
       int exclude = find(arr, hours, idx+1);
       return Math.max(include,exclude);
    }

    static int find( int[] arr, int hours){
        return find(arr,hours,0);
    }

    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]")).mapToInt(Integer::parseInt).toArray();
        int hours =  Integer.parseInt(args[1]);
        System.out.println(hours);
        System.out.println(Arrays.toString(arr));
        System.out.println(find(arr,hours));
    }
}