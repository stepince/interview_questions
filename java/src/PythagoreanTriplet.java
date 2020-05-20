import java.util.*;

/*
  find1
  Time:
     O(N^2+ NlgN )
  Space: O(1)

  find2
  Time:
     O(N^2)
  Space: O(N)
 */
public class PythagoreanTriplet {

    public static boolean find1(int[] arr){
        Arrays.sort(arr);
        for ( int i = arr.length-1; i>= 2 ; --i ){
            int left = 0;
            int right = i-1;
            int cSquare = arr[i]*arr[i];
            while( left < right ){
                int aSquare = arr[left]*arr[left];
                int bSquare = arr[right]*arr[right];
                if ( (aSquare + bSquare) == cSquare ){
                    return true;
                }
                else if ( (aSquare + bSquare) > cSquare ){
                    --right;
                }
                else {
                    ++left;
                }
            }
        }
        return false;
    }

    public static boolean find2(int[] arr){
        for ( int i = 0; i < arr.length-2 ; ++i ){
            Set<Integer> s = new HashSet<>();
            int subtotal1 = arr[i]*arr[i];
            for (int j = i + 1; j < arr.length; ++j ){
                int subtotal2 = Math.abs(subtotal1 - arr[j]*arr[j]);
                if ( s.contains( subtotal2 ) ) return true;
                s.add(arr[j]*arr[j]);
            }
        }
        return false;
    }

    public static void main(String[] args){
        //int[] arr = Stream.of(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] arr = {1, 6, 5,9,16,11,3,25,12,4};
        System.out.println(find1(arr));
        System.out.println(find2(arr));
    }
}
