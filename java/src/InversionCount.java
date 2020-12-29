
//import java.util.stream.Stream;
/*
 * https://www.geeksforgeeks.org/counting-inversions/
 *  Count the number of inversions
 *
 *
 * Input: arr[] = {8, 4, 2, 1}
 * Output: 6
 * Explanation: Given array has six inversions:
 * (8,4), (4,2),(8,2), (8,1), (4,1), (2,1).
 *
 *
 * 4,8  1,2  2
 * 1,2 , 4, 8
 *
 * Input: arr[] = {3, 1, 2}
 * Output: 2
 *
 *
 * Input: arr[] = {1, 9, 6, 4, 5}
 * Output: 5
 */
public class InversionCount {

    static int merge(int[] arr, int begin, int mid, int end, int[] temp){
        int i;
        int leftIdx = begin;
        int rightIdx = mid;
        int count = 0;

        // process merge into temp result
        for ( i = begin ; leftIdx < mid && rightIdx < end; ++i ){
            if ( arr[rightIdx] < arr[leftIdx] ){
                // increment by the diff of current leftIdx and middle
                // note: the only line that has the additional functionality from mergesort
                count += (mid-leftIdx);
                temp[i] = arr[rightIdx++];
            }
            else {
                temp[i] = arr[leftIdx++];
            }
        }
        // add what is left in left half
        for ( ; leftIdx < mid ; ++i ){
            temp[i] = arr[leftIdx++];
        }
        // add what is left in right half
        for ( ; rightIdx < end ; ++i ){
            temp[i] = arr[rightIdx++];
        }
        // copy temp values to arr array
        System.arraycopy(temp,begin,arr,begin,end-begin);
        return count;
    }

    static int mergeCount(int[] arr, int begin, int end, int[] temp){
        // if 2 or more
        if ( begin + 1 < end ) {
            int m = (begin + end) / 2;
            int leftCount = mergeCount(arr, begin, m, temp);
            int rightCount = mergeCount(arr, m, end, temp);
            return leftCount + rightCount + merge(arr, begin, m, end, temp);
        }
        else {
            return 0;
        }
    }

    public static int mergeCount(int[] arr){
        int[] temp = new int[arr.length];
        return mergeCount(arr,0,arr.length,temp );
    }

    public static void main(String[] args){
//        int[] arr = Stream.of(args[0].split("[, ]+")).mapToInt(Integer::parseInt).toArray();
//         int[] arr = new int[]{3,1,2};


//        int[] nums = new int[]{1, 9, 6, 4, 5};
        int[] nums = { 1, 20, 6, 4, 5 };
        System.out.println(mergeCount(nums));
    }
}