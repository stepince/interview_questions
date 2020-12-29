import java.util.Deque;
import java.util.LinkedList;

public class WindowAvg {
    Deque<Integer> deq = new LinkedList<>();
    int size=0;
    int sum=0;

    public void setSize(int size){
        this.size = size;
    }

    public float add(int num){
        if ( size == 0 ) return 0;
        if ( deq.size() > 0  && deq.size() == size ){
            sum -= deq.peek();
            deq.remove();
        }
        sum+=num;
        deq.add(num);
        return (float)sum/deq.size();
    }

    public float remove(){
        if ( size == 0 ) return 0;
        if ( deq.size() > 0 ){
            sum-=deq.peek();
            deq.remove();
        }
        if ( deq.size() == 0 ) return 0;
        return (float)sum/deq.size();
    }

    public static void main(String[] args){
        WindowAvg windowAvg = new WindowAvg();
        windowAvg.setSize(5);
        System.out.println(windowAvg.add(1));
        System.out.println(windowAvg.add(2));
        System.out.println(windowAvg.add(3));
        System.out.println(windowAvg.add(4));
        System.out.println(windowAvg.add(5));
        System.out.println(windowAvg.add(10));
        System.out.println(windowAvg.remove());
        System.out.println(windowAvg.remove());
        System.out.println(windowAvg.remove());
        System.out.println(windowAvg.remove());
        System.out.println(windowAvg.remove());
        System.out.println(windowAvg.remove());
    }
}
