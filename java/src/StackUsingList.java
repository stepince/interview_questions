import java.util.*;

public class StackUsingList<T>  {

   final private List<T> l = new ArrayList<>();

   public void push(T t){
       l.add(t);
   }

   public T pop(){
       if ( l.size() == 0 ) throw new EmptyStackException();
       int idx = l.size()-1;
       T t = l.get(idx);
       l.remove(idx);
       return t;
   }

   public boolean isEmpty(){
      return l.isEmpty();
   }

   public static void main(String[] args){
       int[] arr = Arrays.stream(args[0].split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
       StackUsingList<Integer> stack = new StackUsingList<>();
       for ( int elem : arr ) {
           stack.push(elem);
       }
       while( !stack.isEmpty() ) {
           System.out.println(stack.pop());
       }
   }

}