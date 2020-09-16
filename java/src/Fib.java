/*
   fib
   0 1 1 2 3 5 8 13
   java Fib 4 
   output: 2

Time:
   O(2*n)
*/

public class Fib {

   public static int fib(int n) {
       if ( n <= 1 ) return 0;
       if ( n == 2 ) return 1;
       return fib(n-1) + fib(n-2);
   }
   
   public static void main(String[] args){
      int num = Integer.parseInt(args[0]);
      System.out.println(fib(num));
   }

}