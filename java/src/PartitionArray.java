/*
* Youtube question
* Partition array so that sums are equal
*
* */

import java.util.*;

public class PartitionArray {

    public static List<Integer[]> partitionArray1(int[] nums){
        List<Integer[]> result = new ArrayList<>();
        if ( nums.length <= 1 ) return  result;
        int idx = -1;
        Map<Integer,Integer> prefixMap = new HashMap<>();
        int sum = 0;
        for( int i = nums.length-1; i >= 0; --i ){
            sum+=nums[i];
            prefixMap.put(i,sum);
        }
        sum=0;
        for( int i = 0 ; i < nums.length-2; ++i ){
            sum+=nums[i];
            if ( prefixMap.get(i+1) == sum  ) {
                idx = i+1;
                break;
            }
        }
        if ( idx == -1 ) return result;
        Integer[] part1 = Arrays.stream(Arrays.copyOfRange(nums, 0, idx)).boxed().toArray(Integer[]::new);
        Integer[] part2 = Arrays.stream(Arrays.copyOfRange(nums, idx, nums.length)).boxed().toArray(Integer[]::new);
        result.add(part1);
        result.add(part2);
        return result;
    }

    public static List<Integer[]> partitionArray2(int[] nums){
        List<Integer[]> result = new ArrayList<>();
        if ( nums.length <= 1 ) return  result;
        int idx = -1;
        for( int i = nums.length-1; i > 0; --i ){
            nums[i-1]+=nums[i];
        }

        int sum=0;
        for( int i = 0 ; i < nums.length-2; ++i ){
            int diff = nums[i] - nums[i+1];
            sum+=diff;
            if ( sum == nums[i+1] ) {
                idx = i+1;
                break;
            }
        }
        if ( idx == -1 ) return result;

        Integer[] part1 = Arrays.stream(Arrays.copyOfRange(nums, 0, idx)).boxed().toArray(Integer[]::new);
        Integer[] part2 = Arrays.stream(Arrays.copyOfRange(nums, idx, nums.length)).boxed().toArray(Integer[]::new);
        result.add(part1);
        result.add(part2);
        return result;
    }

    public static void main(String[] args){
        int[] nums = {3,5,1,2,5};
        List<Integer[]> result = partitionArray1(nums);
        if ( result.size() == 2 ){
            System.out.println( Arrays.toString(result.get(0)));
            System.out.println( Arrays.toString(result.get(1)));
        }
        result = partitionArray2(nums);
        if ( result.size() == 2 ){
            System.out.println( Arrays.toString(result.get(0)));
            System.out.println( Arrays.toString(result.get(1)));
        }
    }
}
