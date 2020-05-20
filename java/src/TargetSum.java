import java.util.*;

/*
Output the combination of the array that total to a given sum.
the combinations can not be duplicated

input: [10,1,2,7,6,5] target, 8

output: [1,7]
        [1,2,5]
        [2,6]
        [1,1,6]

 */
public class TargetSum {

    public static void findUtil(int[] arr, int sum, int idx, Deque<Integer> l,  Set<List<Integer>> set){
        if ( sum < 0 ) return;
        if ( sum == 0 && idx == arr.length) {
            set.add(new ArrayList<>(l));
            return;
        }
        if ( idx == arr.length ) return;
        findUtil(arr,sum,idx+1, l,set );
        l.add( arr[idx] );
        findUtil(arr,sum-arr[idx],idx+1, l, set);
        l.removeLast();
    }

    public static List<List<Integer>> find(int[] arr, int sum ){
        Set<List<Integer>> set = new HashSet<>();
        findUtil( arr, sum, 0, new LinkedList<>(),set );
        return new ArrayList<>(set);
    }

    static public void main(String[] args){
        //int[] arr = Arrays.stream(args[0].split("[\\s,]")).mapToInt(Integer::parseInt).toArray();
        //int sum = Integer.parseInt(args[1]);

        int[] arr = {10,1,2,7,6,1,5};
        int sum = 8;
        Arrays.sort(arr);
        System.out.println(find(arr,sum));
    }
}
