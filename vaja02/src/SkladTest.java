import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by amon on 3/14/17.
 */
public class SkladTest {
    @Test
    public void testPush() throws Exception {
        Sklad<String> s = new Sklad();
        s.push("element");
    }

    @Test
    public void testPop() throws Exception {
        Sklad<String> s = new Sklad();
        s.push("element");
        String b = s.pop();
        assertEquals("element", b);

    }

    @Test
    public void popOnTwoElements() {
        Sklad<String> s = new Sklad();
        s.push("a");
        s.push("b");
        String b = s.pop();
        assertEquals("b", b);
        String a = s.pop();
        assertEquals("a", a);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testPopOnEmptyStack() {
        Sklad<String> s = new Sklad();
        s.pop();
    }

    @Test
    public void testIsEmptyOnEmpty() {
        Sklad<String> s = new Sklad();
        Boolean empty = s.isEmpty();
        assertTrue(empty);
    }

    @Test
    public void testIsEmptyOnFull() {
        Sklad<String> s = new Sklad();
        s.push("element");
        Boolean empty = s.isEmpty();
        assertFalse(empty);
    }

    @Test
    public void testPeek() throws Exception {
        Sklad<String> s = new Sklad();
        s.push("element");
        String b = s.peek();
        assertEquals("element", b);

    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testPeekOnEmptyStack() {
        Sklad<String> s = new Sklad();
        s.peek();
    }

    @Test
    public void testPeekMoreElements() throws Exception {
        Sklad<String> s = new Sklad();
        s.push("a");
        s.push("b");
        s.push("c");
        s.push("d");
        s.push("e");
        String b = s.peek();
        assertEquals("e", b);
    }

    @Test
    public void testPeekAndPeek() throws Exception {
        Sklad<String> s = new Sklad();
        s.push("element");

        String b = s.peek();
        assertEquals("element", b);

        b = s.peek();
        assertEquals("element", b);
    }

    @Test
    public void testPeekThenPop() throws Exception {
        Sklad<String> s = new Sklad();
        s.push("element");
        s.peek();
        String b = s.pop();
        assertEquals("element", b);

    }

    @Test
    public void testCountEmpty() throws Exception {
        Sklad<String> s = new Sklad();
        int n = s.count();
        assertEquals(n, 0);
    }

    @Test
    public void testCount() throws Exception {
        Sklad<String> s = new Sklad();
        s.push("element");
        int n = s.count();
        assertEquals(n, 1);
    }

    @Test
    public void testCountMoreElements() throws Exception {
        Sklad<String> s = new Sklad();

        for (int i = 0; i < 10; i++) {
            s.push("element");
        }

        int n = s.count();
        assertEquals(n, 10);
    }

    @Test
    public void testPopAfterCount() throws Exception {
        Sklad<String> s = new Sklad();
        s.push("a");
        s.push("b");
        s.push("c");

        s.count();
        s.pop();
        String b = s.pop();

        assertEquals(b, "b");
    }

    @Test
    public void testPeekAfterCount() throws Exception {
        Sklad<String> s = new Sklad();
        s.push("a");
        s.push("b");
        s.push("c");

        s.count();
        String c = s.peek();

        assertEquals(c, "c");
    }

}