import java.util.*;
/*
  Time:
     O(nlgk)
 */
public class KLargest {

    public static List<Integer> find(int[] arr, int k){
        if ( k <= 0 ) return null;
        Queue<Integer> queue = new PriorityQueue<>();
        for ( Integer num: arr){
            queue.add(num);
            if ( queue.size() > k ) queue.remove();
        }
        return new ArrayList<>(queue);
    }
    public static void main(String[] args){
        int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(args[1]);
        System.out.println(find(arr,k));
    }
}
