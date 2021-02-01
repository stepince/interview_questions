
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianQueue extends LinkedList<Integer> {

    Queue<Integer> smallQ = new PriorityQueue<>( (a,b)->b-a );
    Queue<Integer> largeQ = new PriorityQueue<>();

    public boolean add(Integer item){
        if ( smallQ.size() > largeQ.size() ){
            largeQ.add(item);
        }
        else {
            smallQ.add(item);
        }
        adjust();
        return super.add(item);
    }

    public Integer remove(){
        Integer item = super.remove();
        if (largeQ.contains(item)){
            largeQ.remove(item);
        }
        else {
            smallQ.remove(item);
        }
        while( largeQ.size() + 1 > smallQ.size() ) smallQ.add( largeQ.remove() );
        while( smallQ.size() + 1 > largeQ.size() ) largeQ.add( smallQ.remove() );
        adjust();
        return item;
    }

    void adjust(){
        while ( smallQ.size() > 0 && largeQ.size() > 0  && smallQ.peek() > largeQ.peek() ) {
            smallQ.add(largeQ.remove());
            largeQ.add(smallQ.remove());
        }
    }
    public double median(){
        if ( smallQ.size() == 0 && largeQ.size() == 0 ) {
            return 0;
        }
        else if (smallQ.size() > largeQ.size()) {
            return smallQ.peek();
        }
        else if (largeQ.size() > smallQ.size()) {
            return largeQ.peek();
        }
        else {
            return (float)(smallQ.peek() + largeQ.peek()) / 2;
        }
    }


    public boolean isEmpty(){
        return smallQ.isEmpty() && largeQ.isEmpty();
    }

    public int size(){
        return smallQ.size() + largeQ.size();
    }

    public static void main(String[] args){
        MedianQueue queue = new MedianQueue();
        queue.add( 13);
        queue.add( 7);
        queue.add( 9);
        queue.add( 11);
        System.out.println(queue.median());
        queue.remove();
        System.out.println(queue.median());
        queue.remove();
        System.out.println(queue.median());
    }
}
