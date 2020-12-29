import org.junit.Before;
import org.junit.Test;

public class SetImpl2Test {

    SetImpl2<String> set;

    @Before
    public void tearDown() {
        set = new SetImpl2<>();
    }

    @Test
    public void size() {
        set.add("test11");
        assert(set.size() == 1);
    }

    @Test
    public void empty() {
        assert(set.empty());
    }

    @Test
    public void delete() {
        set.add("test11");
        set.delete("test11");
        assert(set.size() == 0);
    }

    @Test
    public void add() {
        set.add("test11");
        assert(set.size() == 1);
    }

    @Test
    public void contains() {
        set.add("test11");
        assert(set.contains("test1"));
    }
}