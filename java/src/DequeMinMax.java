import java.util.ArrayDeque;
import java.util.Deque;

public class DequeMinMax extends ArrayDeque<Integer> {

    Deque<Integer> deqMin = new ArrayDeque<>();
    Deque<Integer> deqMax = new ArrayDeque<>();
    final Integer getMax(){
        if ( isEmpty() ) return Integer.MIN_VALUE;
        return deqMax.isEmpty() ? deqMin.getLast() : deqMax.peek();
    }

    final Integer getMin(){
        if ( isEmpty() ) return Integer.MAX_VALUE;
        return deqMin.isEmpty() ?  deqMax.getLast() : deqMin.peek();
    }

    public boolean add(Integer item){
        if ( !super.add(item) ) return false;
        if ( !deqMin.isEmpty() && item <= deqMin.getLast() ){
            while ( !deqMin.isEmpty() && item < deqMin.getLast() ) {
                deqMax.add(deqMin.removeLast());
            }
            deqMin.add(item);
        }
        else {
            while ( !deqMax.isEmpty() && item > deqMax.getLast() ) {
                deqMin.add( deqMax.removeLast());
            }
            deqMax.add(item);
        }
        return true;
    }

    @Override
    final public Integer remove() {
        Integer item = super.remove();
        if ( !deqMin.isEmpty() && item <= deqMin.getLast() ){
            while ( item < deqMin.getLast() ) {
                deqMax.add(deqMin.removeLast());
            }
            deqMin.removeLast();
        }
        else {
            while ( !deqMax.isEmpty() && item > deqMax.getLast() ) {
                deqMin.add(deqMax.removeLast());
            }
            deqMax.removeLast();
        }
        return item;
    }

    public static void main(String[] args){
        DequeMinMax myDeque = new DequeMinMax();
        myDeque.add(1);
        System.out.println( "min: " + myDeque.getMin());
        System.out.println( "max: " + myDeque.getMax());

        myDeque.add(2);
        myDeque.add(4);
        myDeque.add(5);
        System.out.println( "min: " + myDeque.getMin());
        System.out.println( "max: " + myDeque.getMax());

        myDeque.remove();
        System.out.println( "min: " + myDeque.getMin());
        System.out.println( "max: " + myDeque.getMax());

        myDeque.remove();
        System.out.println( "min: " + myDeque.getMin());
        System.out.println( "max: " + myDeque.getMax());
    }
}
