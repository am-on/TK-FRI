
import static org.junit.Assert.*;
import org.junit.*;

public class BstTest {

    private Bst<String> bst;

    public BstTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        bst = new Bst<>();
    }

    @After
    public void tearDown() {
    }

    // Praviloma bi morali testirati vsako funkcijo v razredu
    // torej tudi: member, insert, delete, deleteMin, getDepth, countNodes
    // Glede na to, da teste zasnujemo pred poznavanjem podrobnosti implementacije
    // zasnujemo teste za metode vmesnika: 
    // add, removeFirst, getFirst, size,depth, isEmpty, remove, exists
    @Test
    public void testAddOne() {
        bst.add("Test");
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
    }

    @Test
    public void testRemoveMultiple() {
        bst.add("Test4");
        bst.add("Test2");
        bst.add("Test1");
        bst.add("Test3");
        bst.add("Test5");
        bst.remove("Test2");
        assertEquals(4, bst.size());
        assertEquals(3, bst.depth());
        bst.remove("Test5");
        assertEquals(3, bst.size());
        assertEquals(3, bst.depth());
    }

    @Test
    public void testGetFirstOne() {
        bst.add("Test");
        assertEquals("Test", bst.getFirst());
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
    }

    @Test
    public void testGetFirstMultiple() {
        bst.add("Test2");
        assertEquals("Test2", bst.getFirst());
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
        bst.add("Test3");
        bst.add("Test1");
        assertEquals("Test2", bst.getFirst());
        assertEquals("Test2", bst.getFirst());
        assertEquals(3, bst.size());
        assertEquals(2, bst.depth());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testGetFirstOnEmpty() {
        bst.getFirst();
    }

    @Test
    public void testSizeOnEmpty() {
        assertEquals(0, bst.size());
    }

    @Test
    public void testSize() {
        bst.add("Test");
        assertEquals(1, bst.size());
        bst.add("Test1");
        assertEquals(2, bst.size());
    }

    @Test
    public void testDepthOnEmpty() {
        assertEquals(0, bst.depth());
    }

    @Test
    public void testDepth() {
        bst.add("Test");
        assertEquals(1, bst.depth());
        bst.add("Test1");
        assertEquals(2, bst.depth());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(bst.isEmpty());
        bst.add("Test");
        assertFalse(bst.isEmpty());
    }

    @Test
    public void testRemove() {
        bst.add("Test");
        assertTrue(bst.exists("Test"));
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
        bst.remove("Test");
        assertFalse(bst.exists("Test"));
        assertEquals(0, bst.size());
        assertEquals(0, bst.depth());
    }

    @Test
    public void testExists() {
        bst.add("Test");
        assertTrue(bst.exists("Test"));
        assertEquals(1, bst.size());
        assertEquals(1, bst.depth());
        bst.removeFirst();
        assertFalse(bst.exists("Test"));
        assertEquals(0, bst.size());
        assertEquals(0, bst.depth());
    }

}
