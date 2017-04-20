
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
    public void testNoCommand() {
        assertEquals("Error: enter command", uv.processInput(""));
    }

    @Test
    public void testUse() {
        assertEquals("OK", uv.processInput("use pv"));
        assertEquals("OK", uv.processInput("use sk"));
        assertEquals("OK", uv.processInput("use bst"));
    }

    @Test
    public void testNoUse() {
        assertEquals("Error: please specify a data structure (use {pv|sk|bst})", uv.processInput("comm"));
    }

    @Test
    public void testUseEmpty() {
        assertEquals("Error: please specify a data structure type {pv|sk|bst}", uv.processInput("use"));
    }

    @Test
    public void testUseUnexisting() {
        assertEquals("Error: please specify a correct data structure type {pv|sk|bst}", uv.processInput("use test"));
    }


    // TO DO
    // napišite teste za sklad, prioritetno vrsto in BS drevo
    // testi kličejo (zaporedoma) vse operacije nad določeno strukturo 
    // glej POMOZNE METODE


    private void setUpBst() {
        setUp();

    }

    @Test
    public void testUseSklad() {
        assertEquals("OK", uv.processInput("use sk"));
        tests();
        testSklad(true);
        testAsListMany();
    }

    @Test
    public void testUsePrioritetnaVrsta() {
        assertEquals("OK", uv.processInput("use pv"));
        tests();
        testPrioritetnaVrsta(true);
        testAsListManyPV();
    }

    @Test
    public void testUseBst() {
        assertEquals("OK", uv.processInput("use bst"));
        tests();
        testBst(true);
        testBstDuplicateEntry();
        testAsListMany();
    }


    private void testBstDuplicateEntry() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("Error: Duplicated entry", uv.processInput("add Test1"));
    }

    // *****************
    // POMOZNE METODE
    // *****************

    private void tests() {

        testAddNothing();
        testRemoveFirstNothing();
        testGetFirstNothing();
        testDepthOnEmpty();
        testIsEmptyEmpty();
        testResetOnEmpty();
        testSizeOnEmpty();

        reset();
        testInvalidCommand();

        reset();
        testSizeOne();

        reset();
        testDepthOne();

        reset();
        testGetFirst();

        reset();
        testRemoveFirst();

        reset();
        testSizeTwo();

        reset();
        testDepthTwo();

        reset();
        testIsEmptyNotEmpty();

        reset();
        testResetOnFull();

        reset();
        testExists();

        reset();
        testExistsTwo();

        reset();
        testExistsNoString();

        reset();
        testExistsOnEmpty();

        reset();
        testExistsNonExistentOne();

        reset();
        testExistsNonExistent();

        reset();
        testRemove();

        reset();
        testRemoveNoString();

        reset();
        testRemoveTwo();

        reset();
        testRemoveEmpty();

        reset();
        testRemoveNonExistent();

        reset();
        testRemoveTwoNonExistent();

        reset();
        testAsListEmpty();

        reset();
        testAsListOne();

        reset();
    }

    public void testInvalidCommand() {
        testAdd();
        assertEquals("Error: invalid command", uv.processInput("qwe"));
    }

    public void reset() {
        uv.processInput("reset");
    }

    public void testAdd() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
    }

    public void testAddNothing() {
        assertEquals("Error: please specify a string", uv.processInput("add"));
    }

    public void testRemoveFirst() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Test", uv.processInput("remove_first"));
    }

    public void testRemoveFirstNothing() {
        assertEquals("Error: data structure is empty", uv.processInput("remove_first"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Error: data structure is empty", uv.processInput("remove_first"));
    }

    public void testGetFirst() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Test", uv.processInput("get_first"));
    }

    public void testGetFirstNothing() {
        assertEquals("Error: data structure is empty", uv.processInput("get_first"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Error: data structure is empty", uv.processInput("get_first"));
    }

    public void testSizeOnEmpty() {
        assertEquals("0", uv.processInput("size"));
    }

    public void testSizeOne() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("1", uv.processInput("size"));
    }

    public void testSizeTwo() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("2", uv.processInput("size"));
    }

    public void testDepthOnEmpty() {
        assertEquals("0", uv.processInput("depth"));
    }

    public void testDepthOne() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("1", uv.processInput("depth"));
    }

    public void testDepthTwo() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("2", uv.processInput("depth"));
    }

    public void testIsEmptyEmpty() {
        assertEquals("Data structure is empty.", uv.processInput("is_empty"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Data structure is empty.", uv.processInput("is_empty"));
    }

    public void testIsEmptyNotEmpty() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("Data structure is not empty.", uv.processInput("is_empty"));
    }

    public void testResetOnEmpty() {
        assertEquals("OK", uv.processInput("reset"));
    }

    public void testResetOnFull() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("OK", uv.processInput("reset"));
        assertEquals("Error: data structure is empty", uv.processInput("remove_first"));
        assertEquals("0", uv.processInput("size"));
    }

    // TO DO
    public void testRemove() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("Test1", uv.processInput("remove Test1"));
        assertEquals("0", uv.processInput("size"));
    }

    public void testRemoveNoString() {
        testAdd();
        assertEquals("Error: please specify a string", uv.processInput("remove"));
    }

    public void testRemoveTwo() {
        testAdd();
        assertEquals("Test2", uv.processInput("remove Test2"));
        assertEquals("Test1", uv.processInput("remove Test1"));
        assertEquals("0", uv.processInput("size"));
    }

    public void testRemoveEmpty() {
        assertEquals("Error: can't remove element that doesn't exist in data structure.", uv.processInput("remove Test2"));
    }

    public void testRemoveNonExistent() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("Error: can't remove element that doesn't exist in data structure.", uv.processInput("remove Test2"));
        assertEquals("1", uv.processInput("size"));
    }

    public void testRemoveTwoNonExistent() {
        testAdd();
        assertEquals("Error: can't remove element that doesn't exist in data structure.", uv.processInput("remove Test3"));
    }

    public void testExists() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("Element exists in data structure.", uv.processInput("exists Test1"));
    }

    public void testExistsOnEmpty() {
        assertEquals("Element doesn't exist in data structure.", uv.processInput("exists Test"));
    }


    public void testExistsTwo() {
        testAdd();
        assertEquals("Element exists in data structure.", uv.processInput("exists Test1"));
        assertEquals("Element exists in data structure.", uv.processInput("exists Test2"));
    }

    public void testExistsNoString() {
        testAdd();
        assertEquals("Error: please specify a string", uv.processInput("exists"));
    }


    public void testExistsNonExistentOne() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("Element doesn't exist in data structure.", uv.processInput("exists Test2"));
    }


    public void testExistsNonExistent() {
        testAdd();
        assertEquals("Element doesn't exist in data structure.", uv.processInput("exists Test3"));
    }


    public void testAsListEmpty() {
        assertEquals("[ ]", uv.processInput("asList"));
    }

    public void testAsListOne() {
        uv.processInput("add Test");
        assertEquals("[\"Test\", ]", uv.processInput("asList"));
    }

    public void testAsListMany() {
        uv.processInput("add Test3");
        uv.processInput("add Test2");
        uv.processInput("add Test1");

        assertEquals("[\"Test1\", \"Test2\", \"Test3\"]", uv.processInput("asList"));
    }

    public void testAsListManyPV() {
        uv.processInput("add Test3");
        uv.processInput("add Test2");
        uv.processInput("add Test1");

        assertEquals("[\"Test3\", \"Test2\", \"Test1\"]", uv.processInput("asList"));
    }


    public void testAddTestSequence() {
        assertEquals("OK", uv.processInput("add Test4"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test5"));
    }

    public void testSklad(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test1", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test4", uv.processInput("remove_first"));
    }

    public void testPrioritetnaVrsta(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test4", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test1", uv.processInput("remove_first"));
    }

    public void testBst(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("Test4", uv.processInput("remove_first"));
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test1", uv.processInput("remove_first"));
    }

}
