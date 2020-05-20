import java.util.*;

public class TopK {

   private final static int K = 3;

   public static void main(String[] args){

       Map<String,Integer> m = new HashMap<>();

       //Queue<String> q = new PriorityQueue<>( (a,b)->m.get(a)-m.get(b));
       Queue<String> q = new PriorityQueue<>(Comparator.comparingInt(m::get));

       Scanner s = new Scanner(args[0]);
       while( s.hasNext() ){
           String str = s.next();
           m.put(str,m.getOrDefault(str,0)+1);
       }

       Set<String> keys = m.keySet();
       List<String> l = new ArrayList<>();
       for(String k: keys) {
           q.add(k);
           if ( q.size() > K ) {
               String evict = q.remove();
               String peek = q.peek();
               if ( m.get(evict).equals(m.get(peek)) ){
                   l.add(evict);   
               }
               else {
                   l.clear();
               }
           }
       }    
       for(String k: q) {
           System.out.println( k + " => " + m.get(k));
       } 
       for(String k: l) {
           System.out.println(  k + " => " + m.get(k));
       } 
   }

}




