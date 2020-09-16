
import java.util.TreeMap;

public class ReversePair {
    static void merge(int[] arr, int begin, int mid, int end, int[] temp){
        int i;
        int leftIdx = begin;
        int rightIdx = mid;
        // process merge into temp result
        for ( i = begin ; leftIdx < mid && rightIdx < end; ++i ){
            if ( arr[rightIdx] < arr[leftIdx] ){
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
    }

    static int inversionCount(int[] nums, int begin, int mid, int end){
        int count = 0;
        int j = mid;
        for (int i = begin; i < mid; ++i) {
            while (j < end && nums[i] > nums[j] * 2L)  ++j;
            count += j - mid;
        }
        return count;
    }

    static int mergeCount(int[] nums, int begin, int end, int[] temp){
        // if 2 or more
        if ( begin + 1 < end ) {
            int mid = (begin + end) / 2;
            int leftCount = mergeCount(nums, begin, mid, temp);
            int rightCount = mergeCount(nums, mid, end, temp);
            int count = inversionCount(nums,begin,mid,end);
            merge(nums, begin, mid, end, temp);
            return count + leftCount + rightCount ;
        }
        else {
            return 0;
        }
    }

    public static int mergeCount(int[] nums){
        int[] temp = new int[nums.length];
        return mergeCount(nums,0,nums.length,temp );
    }

    public static int find2(int[] nums) {
        return mergeCount(nums);
    }

    public static int find1(int[] nums) {
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        int sum = 0;
        for ( int i = nums.length-1; i >= 0 ; --i ){
            int floor = (int)Math.floor((float)nums[i]/(float)2) - (nums[i]%2 == 0 ? 1 : 0);
            sum += treeMap.headMap(floor,true).values().stream().mapToInt(Integer::intValue).sum();
            treeMap.merge(nums[i],1,Integer::sum);
        }
        return sum;
    }

    static public void main(String[] args){
        //int[] arr = Arrays.stream(args[0].split("[\\s,]")).mapToInt(Integer::parseInt).toArray();
        //int sum = Integer.parseInt(args[1]);

//        int[] nums = {6,1,3,2,3,1};
        int[] nums = {1,3,2,3,1};
        System.out.println(find1(nums));
        System.out.println(find2(nums));
    }
}
