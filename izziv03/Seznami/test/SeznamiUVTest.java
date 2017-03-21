import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by amon on 3/21/17.
 */



public class SeznamiUVTest {

    SeznamiUV uv;

    @Before
    public void setUp() {
        uv = new SeznamiUV();
    }

    @Test
    public void testPushBasic() {
        System.out.println("testPushBasic");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
    }

    @Test
    public void testPushMultipleWords() {
        System.out.println("testPushMultipleWords");
        assertEquals("OK", uv.processInput("push \"Test with multiple words\""));
        assertEquals("1", uv.processInput("count"));
        assertEquals("OK", uv.processInput("push \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("count"));
    }

    @Test
    public void testPushNothing() {
        SeznamiUV uv = new SeznamiUV();
        System.out.println("testPushNothing");
        assertEquals("Error: please specify a string", uv.processInput("push"));
    }

    @Test
    public void testPopBasic() {
        System.out.println("testPopBasic");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
        assertEquals("Test2", uv.processInput("pop"));
        assertEquals("Test1", uv.processInput("pop"));
    }


    @Test
    public void testPopMultipleWords() {
        System.out.println("testPopMultipleWords");
        assertEquals("OK", uv.processInput("push \"Test with multiple words\""));
        assertEquals("OK", uv.processInput("push \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("count"));
        assertEquals("Another test with multiple words", uv.processInput("pop"));
        assertEquals("1", uv.processInput("count"));
        assertEquals("Test with multiple words", uv.processInput("pop"));
        assertEquals("0", uv.processInput("count"));
    }

    @Test
    public void testPopNothing() {
        System.out.println("testPopNothing");
        assertEquals("Error: stack is empty", uv.processInput("pop"));
        assertEquals("Error: please specify a string", uv.processInput("push"));
        assertEquals("Error: stack is empty", uv.processInput("pop"));
    }

    @Test
    public void testResetOnEmpty() {
        System.out.println("testResetOnEmpty");
        assertEquals("OK", uv.processInput("reset"));
    }

    @Test public void testResetOnFull() {
        System.out.println("testResetOnFull");
        assertEquals("OK", uv.processInput("push Test"));
        assertEquals("OK", uv.processInput("reset"));
        assertEquals("Error: stack is empty", uv.processInput("pop"));
        assertEquals("0", uv.processInput("count"));
    }

    @Test(timeout=100)
    public void testCountOnEmpty() {
        System.out.println("testCountOnEmpty");
        assertEquals("0", uv.processInput("count"));
    }

    @Test(timeout=100)
    public void testCountOne() {
        System.out.println("testCountOne");
        assertEquals("OK", uv.processInput("push Test"));
        assertEquals("1", uv.processInput("count"));
    }

    @Test(timeout=100)
    public void testCountTwo() {
        System.out.println("testCountTwo");
        assertEquals("OK", uv.processInput("push Test1"));
        assertEquals("OK", uv.processInput("push Test2"));
        assertEquals("2", uv.processInput("count"));
    }

    @Test
    public void testTop() {
        uv.processInput("push a");
        assertEquals("OK", uv.processInput("top a"));
    }

    @Test
    public void testTopWrongElement() {
        uv.processInput("push a");
        assertEquals("Error: wrong element", uv.processInput("top b"));
    }

    @Test
    public void testTopEmptyStack() {
        assertEquals("Error: stack is empty", uv.processInput("top a"));

    }

    @Test
    public void testTopNullInput() {
        assertEquals("Error: please specify a string", uv.processInput("top"));
    }

    @Test
    public void testSearch() {
        uv.processInput("push a");
        assertEquals("0", uv.processInput("search a"));

        uv.processInput("push b");
        assertEquals("1", uv.processInput("search a"));
    }

    @Test
    public void testSearchNonExistingElement() {
        uv.processInput("push a");
        uv.processInput("push b");
        assertEquals("-1", uv.processInput("search c"));
    }

    @Test
    public void testSearchEmptyStack() {
        assertEquals("-1", uv.processInput("search c"));
    }


    @Test
    public void testSearchNullInput() {
        assertEquals("Error: please specify a string", uv.processInput("search"));
    }

}