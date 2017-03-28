
import org.junit.*;
import static org.junit.Assert.*;

public class SeznamiUVTest {

    private SeznamiUV uv;

    public SeznamiUVTest() {
    }

    @Before
    public void setUp() {
        uv = new SeznamiUV();
    }

    @Test
    public void testPushBasic() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
    }

    // @Ignore("To be implemented at a later stage...")
    @Test
    public void testPushMultipleWords() {
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("1", uv.processInput("s_size"));
        assertEquals("OK", uv.processInput("s_add \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("s_size"));
    }

    @Test
    public void testPushNothing() {
        assertEquals("Error: please specify a string", uv.processInput("s_add"));
    }

    @Test
    public void testPopBasic() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("Test2", uv.processInput("s_remove_first"));
        assertEquals("Test1", uv.processInput("s_remove_first"));
    }

    @Test
    public void testPopMultipleWords() {
        assertEquals("OK", uv.processInput("s_add \"Test with multiple words\""));
        assertEquals("OK", uv.processInput("s_add \"Another test with multiple words\""));
        assertEquals("2", uv.processInput("s_size"));
        assertEquals("Another test with multiple words", uv.processInput("s_remove_first"));
        assertEquals("1", uv.processInput("s_size"));
        assertEquals("Test with multiple words", uv.processInput("s_remove_first"));
        assertEquals("0", uv.processInput("s_size"));
    }

    @Test
    public void testPopNothing() {
        assertEquals("Error: stack is empty", uv.processInput("s_remove_first"));
        assertEquals("Error: please specify a string", uv.processInput("s_add"));
        assertEquals("Error: stack is empty", uv.processInput("s_remove_first"));
    }

    @Test
    public void testResetOnEmpty() {
        assertEquals("OK", uv.processInput("s_reset"));
    }

    @Test
    public void testResetOnFull() {
        assertEquals("OK", uv.processInput("s_add Test"));
        assertEquals("OK", uv.processInput("s_reset"));
        assertEquals("Error: stack is empty", uv.processInput("s_remove_first"));
        assertEquals("0", uv.processInput("s_size"));
    }

    @Test
    public void testCountOnEmpty() {
        assertEquals("0", uv.processInput("s_size"));
    }

    @Test(timeout = 100)
    public void testCountOne() {
        assertEquals("OK", uv.processInput("s_add Test"));
        assertEquals("1", uv.processInput("s_size"));
    }

    @Test(timeout = 100)
    public void testCountTwo() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("2", uv.processInput("s_size"));
    }

    @Test
    public void testTopBasic() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("OK", uv.processInput("s_get_first Test2"));
    }

    @Test
    public void testTopNotEqual() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("2", uv.processInput("s_size"));
        assertEquals("Error: wrong element", uv.processInput("s_get_first Test"));
        assertEquals("2", uv.processInput("s_size"));
    }

    @Test
    public void testTopEmpty() {
        assertEquals("Error: stack is empty", uv.processInput("s_get_first Test"));
        assertEquals("Error: please specify a string", uv.processInput("s_add"));
        assertEquals("Error: stack is empty", uv.processInput("s_get_first Test"));
    }

    @Test
    public void testTopNothing() {
        assertEquals("Error: please specify a string", uv.processInput("s_get_first"));
    }

    @Test
    public void testSearchFound() {
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("0", uv.processInput("s_search Test2"));
        assertEquals("OK", uv.processInput("s_add Test3"));
        assertEquals("2", uv.processInput("s_search Test1"));
    }

    @Test
    public void testTopNotFound() {
        assertEquals("-1", uv.processInput("s_search Test1"));
        assertEquals("OK", uv.processInput("s_add Test1"));
        assertEquals("OK", uv.processInput("s_add Test2"));
        assertEquals("-1", uv.processInput("s_search Test"));
    }

    @Test
    public void testSearchNothing() {
        assertEquals("Error: please specify a string", uv.processInput("s_search"));
    }

    /**
     *
     *  get first - 2
     *  size 1 - 2
     *  depth - 1 -2
     *  is empty 2
     * **/

    @Test
    public void testPqInsert() {
        assertEquals("OK", uv.processInput("pq_add \"To je nek String\""));
    }

    @Test
    public void testPqInsertEmptyString() {
        assertEquals("Error: please specify a string",uv.processInput("pq_add"));
    }

    @Test
    public void testPqRemoveFirst() {
        uv.processInput("pq_add \"text\"");
        assertEquals("\"text\"", uv.processInput("pq_remove_first"));
    }

    @Test
    public void testPqRemoveFirstOnEmpty() {
        assertEquals("Error: priority queue is empty", uv.processInput("pq_remove_first"));
    }

    @Test
    public void testPqGetFirst() {
        uv.processInput("pq_add \"text1\"");
        uv.processInput("pq_add \"text5\"");
        assertEquals("\"text5\"", uv.processInput("pq_get_first"));
    }

    @Test
    public void testPqGetFirstOnEmpty() {
        assertEquals("Error: priority queue is empty", uv.processInput("pq_get_first"));
    }

    @Test
    public void testPqGetSize() {
        uv.processInput("pq_add \"text1\"");
        uv.processInput("pq_add \"text5\"");
        assertEquals("2", uv.processInput("pq_size"));
    }

    @Test
    public void testPqGetSizeOnEmpty() {
        assertEquals("0", uv.processInput("pq_size"));
    }

    @Test
    public void testPqGetDepth() {
        uv.processInput("pq_add \"text1\"");
        uv.processInput("pq_add \"text5\"");
        uv.processInput("pq_add \"text6\"");
        uv.processInput("pq_add \"text7\"");
        assertEquals("3", uv.processInput("pq_depth"));
    }

    @Test
    public void testPqGetDepthOnEmpty() {
        assertEquals("0", uv.processInput("pq_depth"));
    }

    @Test
    public void testPqIsEmpty() {
        assertEquals("Priority queue is empty", uv.processInput("pq_isEmpty"));
    }

    @Test
    public void testPqIsNotEmpty() {
        uv.processInput("pq_add \"text1\"");
        assertEquals("Priority queue is not empty", uv.processInput("pq_isEmpty"));
    }
}
