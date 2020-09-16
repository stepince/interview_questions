import java.util.HashSet;
import java.util.Set;
/*
  first duplicate and the the values have to be within 1 <= n <= arr.length

  find1 is optimal
    O(N) for space
 */
public class FirstDuplicate {

    static int find2(int[] arr){
        Set<Integer> set = new HashSet<>();
        for (int value : arr) {
            if (set.contains(value)) return value;
            set.add(value);
        }
        return -1;
    }

    static int find1(int[] arr){
        for( int i = 0; i < arr.length; ++i ){
            int pos = Math.abs(arr[i])-1;
            // if we have been here already
            if ( arr[pos] < 0 ) {
                return Math.abs( arr[pos] );
            }
            else {
                // mark that we have been here
                arr[pos] = -arr[pos];
            }
        }
        return -1;
    }

    public static void main(String[] args){
//        int[] arr = { 1,2,1,2,3,3 };
//        int[] arr = { 2,1,3,5,3,2};
        int[] arr = { 1,2,3,2,5,6};
        System.out.println(find2(arr));
        System.out.println(find1(arr));
    }
}
