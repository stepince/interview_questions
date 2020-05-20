import java.util.Arrays;
import java.util.stream.Stream;
/*

  Merge two sorted arrays

 */
public class MergeArrays {

    static void swap( int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static void swap( int[] arr1, int[] arr2, int a1, int a2){
        int temp = arr1[a1];
        arr1[a1] = arr2[a2];
        arr2[a2] = temp;
    }

    static void shift(int[] arr, int idx ){
        for ( int i = idx+1; i < arr.length; ++i ){
            if ( arr[i-1] <= arr[i] ) break;
            swap(arr,i-1,i);
        }
    }

    // not too sure if this an optimal solution
    // it seems lik you should insert using binary search
    public static void merge2(int[] left, int[] right){
        for ( int lIdx = 0, rIdx = 0; lIdx < left.length && rIdx < right.length; ++lIdx ){
            if ( left[lIdx] == right[rIdx] ){
                ++rIdx;
            }
            else if ( left[lIdx] > right[rIdx] ){
                swap(left,right,lIdx,rIdx);
                shift(right,rIdx);
            }
        }
    }

    public static int[] merge(int[] arr1, int[] arr2){
        if ( arr1 == null || arr2 == null ) return new int[0];
        int[] result = new int[arr1.length + arr2.length];
        int idx1 = 0, idx2 = 0, i = 0;

        for ( ; idx1 < arr1.length && idx2 < arr2.length; ++i){
            if ( arr1[idx1] < arr2[idx2] ){
                result[i] = arr1[idx1++];
            }
            else {
                result[i] = arr2[idx2++];
            }
        }

        for ( ; idx1 < arr1.length; ++i){
            result[i] = arr1[idx1++];
        }

        for ( ; idx2 < arr2.length; ++i){
            result[i] = arr2[idx2++];
        }
        return result;
    }

    public static void main(String[] args){
//        int[] arr1 = Stream.of(args[0].split("\\s*[,]\\s*")).mapToInt(Integer::parseInt).toArray();
//        int[] arr2 = Stream.of(args[1].split("\\s*[,]\\s*")).mapToInt(Integer::parseInt).toArray();
//        System.out.println(Arrays.toString(merge(arr1,arr2)));

//        int[] arr1 = { 1, 3, 4, 5};
//        int[] arr2 = { 2, 4, 6, 8};

//        int[] arr1 = { 7};
//        int[] arr2 = { 1,2, 3,4};

        int[] arr1 = { 7,8,10,11};
        int[] arr2 = { 1};
        merge2(arr1, arr2);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }
}
