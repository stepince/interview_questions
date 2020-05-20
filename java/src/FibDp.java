/*
   fib
   0 1 1 2 3 5 8 13
   java Fib 7
   output: 8

   Time:
      O(N)

    T(n) = T(n-1) + T(n-2) + c

   Space:
      O(N)
*/
import java.util.Map;
import java.util.HashMap;

public class FibDp {

    static int fib(int n, Map<Integer,Integer> mem) {
        if ( n == 1 ) return 0;
        if ( n == 2 ) return 1;
        if ( mem.containsKey(n) ) return mem.get(n);
        int val = fib(n-1,mem) + fib(n-2,mem);
        mem.put(n,val);
        return val;
    }

    static int fib2(int n, Map<Integer,Integer> mem) {
        mem.put(1,0);
        mem.put(2,1);
        return mem.computeIfAbsent(n, (key)->fib2(n-1)+fib2(n-2));
    }

    public static int fib2(int n ) {
        return fib2(n, new HashMap<>());
    }

    public static int fib(int n ) {
        return fib(n, new HashMap<>());
    }

    public static void main(String[] args){
        //int num = Integer.parseInt(args[0]);
        int num = 7;
        System.out.println(fib(num));
        System.out.println(fib2(num));
    }

}