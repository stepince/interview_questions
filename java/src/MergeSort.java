import java.util.Arrays;
import java.util.stream.Stream;
/*

Merge sort

Runtime: O(nlgn)
Auxiliary space: O(N)
stable
 **/
public class MergeSort {

    private static void merge(int[] arr, int begin, int mid, int end, int[] temp){
        int i;
        int leftIdx = begin;
        int rightIdx = mid;

        // process merge into temp result
        for ( i = begin; leftIdx < mid && rightIdx < end; ++i){
            if ( arr[rightIdx] < arr[leftIdx] ){
                temp[i] = arr[rightIdx++];
            }
            else {
                temp[i] = arr[leftIdx++];
            }
        }
        // add what is left in left half
        for ( ; leftIdx < mid; ++i){
            temp[i] = arr[leftIdx++];
        }
        // add what is left in right half
        for ( ; rightIdx < end; ++i){
            temp[i] = arr[rightIdx++];
        }
        // copy temp values to seq array
        for ( i = begin; i < end; ++i){
            arr[i] = temp[i];
        }
    }

    private static void mergeSort(int[] arr, int begin, int end, int[] temp ){
        if ( end - begin <= 1 ) return;
        int m = (begin + end)/2;
        mergeSort(arr, begin, m, temp);
        mergeSort(arr, m, end, temp );
        merge( arr, begin, m, end, temp );
    }

    public static void mergeSort(int[] arr) {
        mergeSort( arr,  0,arr.length, new int[arr.length]);
    }

    public static void main(String[] args){
        int[] arr = Stream.of(args[0].split("[, ]+")).mapToInt(Integer::parseInt).toArray();
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}