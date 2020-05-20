import java.util.Arrays;
import java.util.stream.Stream;

public class BinarySearch {

    public static int find(int[] arr, int num){
        int begin = 0;
        int end = arr.length;
        while( begin < end ){
            int mid = (begin + end)/2;
            if ( arr[mid] == num ) return mid;
            if ( arr[mid] > num ){
                end = mid;
            }
            else {
                begin = mid +1;
            }           
        }
        return -1;
    }

    public static void main(String[] args){
       int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       int num = Integer.parseInt(args[1]);
       Arrays.sort(arr);
       System.out.println(Arrays.toString(arr));
       System.out.println(find(arr,num));
    }
}