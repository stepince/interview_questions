
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

    private static void quickSelect(int[] nums, int begin, int end, int k){
        if ( begin < end ) {
            int pivotIdx = partition(nums,begin,end);
            if ( k < pivotIdx  ) {
                quickSelect(nums, begin, pivotIdx - 1, k);
            }
            else if ( k > pivotIdx ){
                quickSelect(nums, pivotIdx + 1, end, k);
            }
        }
    }

    public static int find(int[] nums, int k){
        if ( k > nums.length ) return -1;
        quickSelect(nums,0, nums.length-1, k-1);
        return nums[k-1];
    }

    public static void main(String[] args){
        int[] nums = new int[]{1, 9, 6, 4, 5};
        int k = 5;
        System.out.println(find(nums, k));
    }
}