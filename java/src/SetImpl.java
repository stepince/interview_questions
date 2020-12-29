
public class SetImpl<T> {
    private Object[] elems;
    private int size = 0;
    public SetImpl(int capacity ){
        elems = new Object[capacity];
    }

    public SetImpl(){
        elems = new Object[10];
    }

    private void grow(){
        if ( size == elems.length ){
            Object[] newElems = new Object[elems.length*2];
            System.arraycopy(elems,0,newElems,0,elems.length);
            elems= newElems;
        }
    }
    public int size(){
        return this.size;
    }

    public boolean empty(){
        return size == 0;
    }

    public void delete( T obj ) {
        for ( int i = 0; i < elems.length; ++i ){
            if ( obj.equals(elems[i]) ) {
                elems[i] = null;
                --size;
            }
        }
    }

    public void add( T obj ) {
        if ( contains(obj) ) {
            return;
        }
        grow();
        for ( int i = 0; i < elems.length; ++i ){
            if ( elems[i] == null ) {
                elems[i] = obj;
                ++size;
                return;
            }
        }
    }

    public boolean contains(T obj){
        if ( this.size == 0 )  {
            return false;
        }
        for (Object elem : elems) {
            if (obj.equals(elem)) return true;
        }
        return false;
    }

    public static void main(String[] args ){
        SetImpl<String> s = new SetImpl<>();
        System.out.println(s.empty());
        System.out.println(s.contains("test"));
        s.add("test");
        System.out.println(s.contains("test"));
        s.delete("test");
        System.out.println(s.contains("test"));
    }
}
