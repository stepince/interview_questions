import java.util.Arrays;

public class MountainArray {

    public static boolean find(int[] arr){
        if( arr == null || arr.length < 3) return false;

        int i = 2;
        if ( arr[0] >= arr[1] ) return false;

        // as few comparisons in a lop
        while  ( (i < arr.length) && (arr[i] > arr[i-1]) ) ++i;

        if ( i == arr.length || (arr[i] == arr[i-1]) ) return false;
        while  ( (i < arr.length) && (arr[i] < arr[i-1]) ) ++i;

        return ( i == arr.length );
    }

    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr));
    }
}