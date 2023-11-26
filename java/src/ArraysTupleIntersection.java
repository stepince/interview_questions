import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class ArraysTupleIntersection {

    public static class Tuple {
        int start;
        int end;
        public Tuple(int start, int end){
            this.start = start;
            this.end = end;
        }
        public Tuple(){}
        public String toString(){
            return this.start +"," + this.end;
        }
    }

    public static List<Tuple> find1(int[][] arr1, int[][] arr2 ){
        boolean[] result1 = new boolean[ arr1[arr1.length-1][1]+1 ];
        boolean[] result2 = new boolean[ arr2[arr2.length-1][1]+1 ];
        boolean[] result3 = new boolean[ Math.min(result1.length, result2.length) ];
        List<Tuple> l = new ArrayList<>();
        for (int[] ints : arr1) {
            Arrays.fill(result1, ints[0], ints[1] + 1, true);
        }
        for (int[] ints : arr2) {
            Arrays.fill(result2, ints[0], ints[1] + 1, true);
        }
        for ( int i = 0; i < result3.length; ++i ){
            result3[i] = result1[i] && result2[i] ;
        }
        for ( int i = 0; i < result3.length; ){
            if ( !result3[i] ) {
                ++i;
                continue;
            }
            Tuple tuple = new Tuple();
            tuple.start = i;
            while( i < result3.length && result3[i] ) tuple.end=i++;
            l.add(tuple);
        }
        return l;
    }

    public static List<Tuple> find2(int[][] arr1, int[][] arr2 ){
        BitSet set1 = new BitSet();
        BitSet set2 = new BitSet();
        List<Tuple> l = new ArrayList<>();
        for (int[] ints : arr1) {
            set1.set(ints[0], ints[1] + 1, true);
        }
        for (int[] ints : arr2) {
            set2.set(ints[0], ints[1] + 1, true);
        }
        set1.and(set2);

        int start = 0;
        while ( (start = set1.nextSetBit(start)) > -1 ){
            int end = set1.nextClearBit(start);
            l.add(new Tuple(start,end-1));
            start = end;
        }
        return l;
    }

    public static void main(String[] args){
        int[][] arr1 = {{0,4},{7,8},{10,11}};
        int[][] arr2 = {{1,5},{7,8},{10,11}};
        System.out.println(find1(arr1,arr2));
        System.out.println(find2(arr1,arr2));
    }
}
