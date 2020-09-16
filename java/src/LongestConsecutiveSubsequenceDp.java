import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSubsequenceDp {

    static Integer max(Integer a, Integer b){
        if ( a == null ) return b;
        if ( b == null ) return a;
        return Math.max(a,b);
    }

    public static int find(int[] arr, int idx, Integer current, Map<String,Integer> mem){
        if ( idx == arr.length ) return 0;
        String key = current + ":" + idx;
        if ( mem.containsKey(key)) return mem.get(key);
        // exclude
        Integer val = find(arr,idx+1,current,mem);
        if ( current == null || arr[idx] == current + 1 ){
            val = max( val , 1 + find(arr,idx+1,arr[idx],mem ));
        }
        return mem.merge(key,val, (prev,curr)->curr);
    }

    public static int find(int[] arr) {
        return find(arr,0,null,new HashMap<>());
    }

    public static void main(String[] args){
        int[] arr = {0,1,2,5,4,8,3,9,10,4,1,2,5,6,7};
//        int[] arr = {};
        System.out.println(find(arr));
    }
}
