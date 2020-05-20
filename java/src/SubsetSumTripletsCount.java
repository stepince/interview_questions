import java.util.*;

/*
* Problem: count the the number of triplets that equal a number
*
* input: array = {12, 3, 6, 1, 6, 9} sum = 24
* output 2
* [3, 9, 12], [6, 6, 12]
*
* O(N^2)
 */
public class SubsetSumTripletsCount {

    public static int find2( int[] arr, int sum ){
        int count = 0;
        for ( int i = 0; i < arr.length-2; ++i ){
            Set<Integer> s = new HashSet<>();
            int subtotal1 = sum - arr[i];
            for ( int j = i + 1; j < arr.length; ++j ){
                int subtotal2 = subtotal1 - arr[j];
                if ( s.contains(subtotal2) ) {
                    System.out.printf("Triplet is %d, %d, %d\n", arr[i], arr[j], subtotal2);
                    ++count;
                }
                s.add(arr[j]);
            }
        }
        return count;
    }

    public static int find1(int[] arr, int sum){
        if( arr.length < 3 ) return 0;
        int count = 0;
        Arrays.sort(arr);
        for( int i =0; i < arr.length-2;++i){
            int subtotal = sum - arr[i];
            int left = i+1;
            int right = arr.length-1;
            while ( left<right ) {
                if ( arr[left] + arr[right] == subtotal ){
                    ++count;
                    ++left;
                    --right;
                }
                else if ( arr[left] + arr[right] > subtotal ){
                    --right;
                }
                else {
                    ++left;
                }
            }
        }
        return count;
    }

    static public void main(String[] args){
        //int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        //int sum = Integer.parseInt(args[1]);

//        int[] arr = {5, 5, 5};
//        int sum =  15;

//        int[] arr = {12, 3, 6, 1, 6, 9};
//        int sum =  24;

        int[] arr = {5, 7, 13};
        int sum =  25;
        System.out.println(find1(arr,sum));
        System.out.println(find2(arr,sum));
    }
}