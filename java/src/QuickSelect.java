import java.util.Arrays;

public class QuickSelect {

    private static void swap( int[] arr, int a, int b){
        final int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static int partition(int[] arr, int begin, int end){
        int pivotIdx = begin;
        int pivot = arr[end];
        for ( int i = begin; i < end; ++i ){
            if ( arr[i] < pivot ){
                swap(arr, pivotIdx++, i );
            }
        }
        swap(arr,pivotIdx,end);
        return pivotIdx;
    }

    private static void quickSelect(int[] arr, int begin, int end, int k){
        if ( begin < end ) {
            int pivotIdx = partition(arr,begin,end);
            if ( pivotIdx < k ) {
                quickSelect(arr, begin, pivotIdx - 1, k);
            }
            quickSelect(arr,pivotIdx+1, end, k);
        }
    }

    public static int[] find(int[] arr, int k){
        quickSelect(arr,0, arr.length-1, k);
        return Arrays.copyOfRange(arr, Math.max(0,arr.length - k),arr.length);
    }

    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(args[1]);
        System.out.println(Arrays.toString(find(arr, k)));
    }
}