import java.util.Arrays;
import java.util.concurrent.*;

public class MergeSortParallel extends RecursiveAction  {

    final int[] arr;
    final int[] temp;
    final int begin;
    final int end;

    MergeSortParallel(int[] arr, int begin, int end, int[] temp){
        this.arr = arr;
        this.temp = temp;
        this.begin = begin;
        this.end = end;
    }

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

    @Override
    // mergeSort function
    protected void compute() {
        if ( begin + 1 < end ) {
            int m = (begin + end) / 2;
            ForkJoinTask<?> left = new MergeSortParallel(arr, begin, m, temp);
            ForkJoinTask<?> right = new MergeSortParallel(arr, m, end, temp);
            invokeAll(left,right);
            merge(arr, begin, m, end, temp);
        }
    }

    public static void mergeSort(int[] arr) {
        // create a thread pool
        ForkJoinPool executorService = new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);
        executorService.invoke( new MergeSortParallel(arr,0,arr.length,new int[arr.length] ) );
//        mergeSort( arr,0,arr.length, new int[arr.length]);
    }

    public static void main(String[] args){
        long t = System.currentTimeMillis();
        int[] arr = {2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22,
                2,8,3,1,15,13,4,6,10,11,5,22,23,6,7,8,91,1,0,666,223,123,12,3,5,6,5,77,8,8,8,3,1,3,22,3,7,8,912,33,44,1,2,4,5,6,71,78,88,2,4,5,6,6,7,8,33,22};
//        int[] arr = Stream.of(args[0].split("[, ]+")).mapToInt(Integer::parseInt).toArray();
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("time: " + (System.currentTimeMillis() - t));
    }

}