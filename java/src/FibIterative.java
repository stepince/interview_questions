/*
   fib
   0 1 1 2 3 5 8 13
   java FibIterative 9
   output: 13

Time:
    O(N)

Space:
    O(1)
*/

public class FibIterative {

   public static int fib(int n) {
       if ( n == 1 ) return 0;
       if ( n == 2 ) return 1;
       int n1 = 0;
       int n2 = 1;
       int val = 0;
       for ( int i = 2; i < n;++i){
           val = n1 + n2;
           n2 = n1;
           n1 = val;
       }
       return val;
   }
   
   public static void main(String[] args){
      int num = Integer.parseInt(args[0]);
      System.out.println(fib(num));
   }

}