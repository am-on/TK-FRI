
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SkladTest {

    static Sklad<String> instance;

    public SkladTest() {
    }

    @BeforeClass
    static public void predVsemiEnkrat() {
        instance = new Sklad<>();
    }

    @Before
    public void predVsakim() {
        while(!instance.isEmpty()) {
            instance.pop();
        }
    }

    @Test
    public void testPush() {
        String a = "Test";
        instance.push(a);
    }

    @Test
    public void testPop() {
        String a = "Test";
        instance.push(a);
        String b = instance.pop();
        assertEquals("Test", b);
    }

    @Test
    public void testWithTwoElements() {
        String a = "Prvi test";
        String b = "Drugi test";
        instance.push(a);
        instance.push(b);
        assertEquals(b, instance.pop());
        assertEquals(a, instance.pop());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testPopOnEmptyStack() {
        String a = instance.pop();
    }

    @Test
    public void testIsEmptyOnEmpty() {
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyOnFull() {
        instance.push("Test");
        assertFalse(instance.isEmpty());
    }

    @Test(timeout=100)
    public void testCountEmpy() {
        assertEquals(0, instance.count());
    }


    @Test(timeout=100)
    public void testCountOne() {
        instance.push("a");
        assertEquals(1, instance.count());
    }


    @Test(timeout=100)
    public void testCountThree() {
        instance.push("a");
        instance.push("b");
        instance.push("c");
        assertEquals(3, instance.count());
    }

    // top 4
    @Test
    public void testTop() {
        instance.push("a");
        assertTrue(instance.top("a"));
    }

    @Test
    public void testTopNotEquals() {
        instance.push("b");
        assertFalse(instance.top("a"));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testTopNoSuchElement() {
        instance.top("a");
    }

    @Test
    public void testTopUnchangedStack() {
        instance.push("a");
        instance.push("b");
        instance.top("b");

        assertEquals("b", instance.pop());
        assertEquals("a", instance.pop());
    }

    @Test
    public void testSearch(){
        instance.push("a");
        instance.push("b");
        assertEquals(0, instance.search("b"));
        assertEquals(1, instance.search("a"));
    }

    @Test
    public void testSearchNoElement(){
        instance.push("a");
        assertEquals(-1, instance.search("b"));
    }

    @Test
    public void testSearchEmptyStack(){
        assertEquals(-1, instance.search("a"));
    }

    @Test
    public void testSearchUnchangedStack() {
        instance.push("a");
        instance.push("b");
        instance.search("b");

        assertEquals("b", instance.pop());
        assertEquals("a", instance.pop());
    }
}
