
public class SetImpl2<T> {
    static class Node {
        Node next;
        Object obj;
        Node(Object obj){
            this.obj = obj;
        }
    }

    private Node[] elems;
    private int size = 0;
//    public Set2(int capacity ){
//        elems = new Node[capacity];
//    }

    public SetImpl2(){
        elems = new Node[10];
    }

    public int size(){
        return this.size;
    }
    void grow(){
        if ( size == elems.length ){
            Node[] newElems = new Node[elems.length*2];
            for (Node elem : elems) {
                Node node = elem;
                while (node != null) {
                    addNode(newElems, node.obj);
                    node = node.next;
                }
            }
            elems= newElems;
        }
    }

    public boolean empty(){
        return size == 0;
    }

    public void delete( T obj ) {
        int bucket = Math.abs(obj.hashCode()) % elems.length;
        Node node = elems[bucket];
        Node prev = null;
        while( node != null ) {
            if ( node.obj.equals(obj) ) {
                --size;
                if ( prev == null ) {
                    elems[bucket] = node.next;
                }
                else {
                    prev.next = node.next;
                }
                return;
            }
            prev = node;
            node = node.next;
        }
    }

    void addNode( Node[] nodeElems, Object obj ) {
        int bucket = Math.abs(obj.hashCode()) % nodeElems.length;
        if ( nodeElems[bucket] == null ) {
            nodeElems[bucket] = new Node(obj);
            return;
        }
        Node node = nodeElems[bucket];
        while( node.next != null ) {
            node = node.next;
        }
        node.next = new Node(obj);
    }

    public void add( T obj ) {
        if ( contains(obj) ) {
            return;
        }
        grow();
        ++size;
        addNode( elems, obj );
    }

    public boolean contains(T obj){
        if ( this.size == 0 )  {
            return false;
        }
        int bucket = Math.abs(obj.hashCode()) % elems.length;
        Node node = elems[bucket];
        while( node != null ) {
            if ( node.obj.equals(obj) ) return true;
            node = node.next;
        }
        return false;
    }

    public static void main(String[] args ){
        SetImpl2<String> s = new SetImpl2<>();
        System.out.println(s.empty());
        System.out.println(s.contains("test"));
        s.add("test");
        System.out.println(s.contains("test"));
    }
}
