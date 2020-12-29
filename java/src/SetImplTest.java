import org.junit.Before;
import org.junit.Test;


public class SetImplTest {

    SetImpl<String> set;

    @Before
    public void tearDown() {
        set = new SetImpl<>();
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
        set.add("test1");
        assert(set.contains("test1"));
    }
}