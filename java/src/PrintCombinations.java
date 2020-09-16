import java.util.ArrayList;

import java.util.List;

/*
Runtime
O(N!)
*/

public class PrintCombinations {

    private static void find(int size, int max, List<Integer> l){
        if ( l.size() == max) {
            System.out.println(l);
            return;
        }

        for ( int i = 0; i <= size; ++i ) {
            var newList = new ArrayList<>(l);
            newList.add(i);
            find(size-i, max, newList );
        }
    }

    public static void main(String[] args){
        find(5, 3, new ArrayList<>());
    }
}