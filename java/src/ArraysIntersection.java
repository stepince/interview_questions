import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
  * find the arrays intersection
  *  what is common between 3 arrays
  *
  *  algorithm:
  *   sort all three and sync all three arrays
  *   if all three indices are equal add
  *   logic for incrementing x, y , z
  *
  *
  * input: "3,6,5,5,9" "5,4,1,6" "5,8,6,1"
  * output: [5, 6]
  *
  * O(nlgn)
  *
  *
 */
public class ArraysIntersection {

     public static List<Integer> find(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> l = new ArrayList<>();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        Arrays.sort(arr3);
        for ( int x = 0, y = 0, z = 0; x < arr1.length && y < arr2.length && z < arr3.length; ) {
            if ( arr1[x] == arr2[y] && arr2[y] == arr3[z] ){
                l.add(arr1[x]);
                ++x;
                ++y;
                ++z;
            }
            else if ( arr1[x] < arr2[y] ) {
               ++x;
            }
            else if ( arr2[y] < arr3[z] ){
                ++y;
            }
            else {
                ++z;
            }
         }
         return l;
    }

    public static void main(String[] args){
        int[] arr1 = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] arr2 = Arrays.stream(args[1].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int[] arr3 = Arrays.stream(args[2].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(find(arr1,arr2,arr3));
    }
}