import java.util.*;

/*
  * a pair of number closest to a number.

 */
public class Packages {

    static class Package {
        final int id;
        final int space;
        Package(int id, int space){
            this.id = id;
            this.space = space;
        }
    }

    public static List<Integer> find(int[] arr, int total){
        List<Package> packages = new ArrayList<>();
        for( int i = 0; i < arr.length; ++i ){
            packages.add( new Package(i,arr[i]));
        }
        //packages.sort((a, b) -> a.space - b.space);
        packages.sort(Comparator.comparingInt(a -> a.space));
        int currentDiff = Integer.MAX_VALUE;
        int left =0;
        int right=arr.length-1;
        int l = 0;
        int r = arr.length-1;
        while ( l < r ){
            int pairTotal = packages.get(l).space + packages.get(r).space;
            if ( pairTotal == total ){
                return Arrays.asList(packages.get(l).id,packages.get(r).id);
            }
            else if ( Math.abs(pairTotal-total) < currentDiff ){
                currentDiff =  Math.abs(pairTotal-total);
                left = packages.get(l).id;
                right = packages.get(r).id;
            }
            else if ( pairTotal > total ){
               --r;
            }
            else {
                --l;
            }
        }
        return Arrays.asList(left,right);
    }

    public static void main(String[] args){
        int[] arr =   Arrays.stream(args[0].split("[,\\s]+")).mapToInt(Integer::parseInt).toArray();
        int total = Integer.parseInt(args[1]);
        System.out.println(find(arr,total));
    }
}