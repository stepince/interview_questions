/*
https://www.interviewcake.com/question/python/product-of-other-numbers
You have a list of integers, and for each index you want to find the product of every integer except the integer at that index.
Here's the catch: You can't use division in your solution!

example:

input:
  [1, 7, 3, 4]

output:
   [84, 12, 28, 21]
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProductIntegers {

    public static int[] find(int[] arr){
        int[] result = new int[arr.length];
        Map<Integer,Integer> forwardMap = new HashMap<>();
        Map<Integer,Integer> reverseMap = new HashMap<>();
        int prod = 1;
        for ( int i = 0 ; i < arr.length; ++i){
            prod*=arr[i];
            forwardMap.put(i,prod);
        }
        prod = 1;
        for ( int i = arr.length - 1 ; i >= 0 ; --i){
            prod*=arr[i];
            reverseMap.put(i,prod);
        }
        for ( int i = 0;i < result.length; ++i){
            result[i] = forwardMap.getOrDefault(i-1,1) * reverseMap.getOrDefault(i+1,1);
        }
        return result;
    }

    public static void main(String[] args){
        int[] arr = {1, 7, 3, 4};
        System.out.println(Arrays.toString(find(arr)));
    }
}
