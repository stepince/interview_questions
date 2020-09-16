import java.util.Arrays;

/*

Given a set of distinct integers, and a value m, determine if
there is a subset of the given set with sum a m.

Input : arr[] = {3, 1, 7, 5};
        m = 11;
Output : YES

Input : arr[] = {1, 6};
        m = 5;
Output : NO


                                    3,1,7,5/11
                           1,7,5/8                   1,7,5/11
                    7,5/7        7,5/8           7,5/10          7,5/11
                5/0   5/7     5/1    5/8     5/3     5/10     5/5    5/11
            /-5  /0  /2 /7  /-4 /1 /3 /8  /-2 /3  /5  /10   /0 /5  /6  /11

 */

public class SubsetSum {

    private static boolean find(int[] arr, int idx, int sum ){
        if ( sum == 0 ) return true;
        if ( idx == arr.length ) return false;
        if ( arr[idx] > sum ) return false;
        return  find(arr, idx+1, sum - arr[idx]) || find(arr, idx+1, sum);
    }

    public static boolean find(int[] arr, int sum) {
        Arrays.sort(arr);
        return find(arr,0,sum);
    }

    public static void main(String[] args){
//        int[] arr = Stream.of(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
//        int m = Integer.parseInt(args[1]);
        int[] arr = {8,2,1,2};
        int sum = 5;
        System.out.println(find(arr,sum));
    }

}