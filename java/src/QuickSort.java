import java.util.Arrays;

/**
 *
 * average 0(nlgn)
 * worst case O(n^2)
 *
 * note:  most implementations use Hoare with 2 indices
 * unstable sort
 */
public class QuickSort {

    private static void swap(int[] arr, int a, int b){
        if ( a != b ){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }

    private static int partition(int[] arr, int begin, int end){
        int pivotIdx = begin;
        int pivot = arr[end];
        for( int i = begin; i < end; ++i) {
            if ( arr[i] < pivot ){
                swap(arr,pivotIdx++,i);
            }
        }
        swap(arr,pivotIdx,end);
        return pivotIdx;
    }

    private static void sort(int[] arr, int begin, int end){
        if ( begin < end ) {
            int pivotIdx = partition(arr,begin,end);
            sort(arr,begin,pivotIdx-1);
            sort(arr,pivotIdx+1,end);
        }
    }

    public static void sort(int[] arr){
        sort(arr,0,arr.length-1);
    }

    static public void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}