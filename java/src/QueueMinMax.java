import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueMinMax extends LinkedList<Integer> {
    Queue<Integer> queueMin = new PriorityQueue<>();
    Queue<Integer> queueMax = new PriorityQueue<>( (a,b)->b-a);

    public Integer getMin(){
        return queueMin.peek();
    }

    public Integer getMax(){
        return queueMax.peek();
    }

    public boolean add(Integer item){
        if ( !super.add(item) ) return false;
        queueMin.add(item);
        queueMax.add(item);
        return true;
    }

    public Integer remove(){
        Integer item = super.remove();
        queueMax.remove(item);
        queueMin.remove(item);
        return item;
    }
}
