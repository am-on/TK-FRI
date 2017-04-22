import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Drevo23Test {
    private Drevo23<String> instance;

    @Before
    public void setUp() {
        instance = new Drevo23<>();
    }

    private void addOne() {
        instance.add("Test");
    }

    private void addTwo() {
        instance.add("Test1");
        instance.add("Test2");
    }

    private void addMany() {
        instance.add("Test19");
        instance.add("Test4");
        instance.add("Test20");
        instance.add("Test17");
        instance.add("Test12");
        instance.add("Test15");
        instance.add("Test13");
        instance.add("Test5");
    }

    private void containsOne() {
        assertTrue(instance.exists("Test"));
    }

    private void containsTwo() {
        assertTrue(instance.exists("Test1"));
        assertTrue(instance.exists("Test2"));
    }

    private void containsMany() {
        assertTrue(instance.exists("Test4"));
        assertTrue(instance.exists("Test5"));
        assertTrue(instance.exists("Test12"));
        assertTrue(instance.exists("Test13"));
        assertTrue(instance.exists("Test15"));
        assertTrue(instance.exists("Test17"));
        assertTrue(instance.exists("Test19"));
        assertTrue(instance.exists("Test20"));
    }

    private void sameMany() {
        assertEquals(8, instance.size());
        containsMany();
        assertEquals("Test4", instance.removeFirst());
        assertEquals("Test5", instance.removeFirst());
        assertEquals("Test12", instance.removeFirst());
        assertEquals("Test13", instance.removeFirst());
        assertEquals("Test15", instance.removeFirst());
        assertEquals("Test17", instance.removeFirst());
        assertEquals("Test19", instance.removeFirst());
        assertEquals("Test20", instance.removeFirst());
        assertEquals(0, instance.size());
    }

    // ADD tests

    @Test
    public void testAdd() {
        addOne();
        containsOne();
    }

    @Test
    public void testAddTwo() {
        addTwo();
        containsTwo();
    }

    @Test
    public void testAddMany() {
        addMany();
        containsMany();
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testAddSameElements() {
        testAddMany();
        testAddMany();
    }


    // SIZE test

    @Test
    public void testSizeEmpty() {
        assertEquals(0, instance.size());
    }

    @Test
    public void testSize() {
        addOne();
        assertEquals(1, instance.size());
    }

    @Test
    public void testSizeTwo() {
        addTwo();
        assertEquals(2, instance.size());
    }

    @Test
    public void testSizeMany() {
        addMany();
        assertEquals(8, instance.size());
    }

    @Test
    public void testSizeSame() {
        addMany();
        instance.size();
        sameMany();
    }


    // DEPTH test

    @Test
    public void testDepthEmpty() {
        assertEquals(0, instance.depth());
    }

    @Test
    public void testDepth() {
        addOne();
        assertEquals(1, instance.depth());
    }

    @Test
    public void testDepthTwo() {
        addTwo();
        assertEquals(1, instance.depth());
    }

    @Test
    public void testDepthMany() {
        addMany();
        assertEquals(4, instance.depth());
    }

    @Test
    public void testDepthSame() {
        addMany();
        instance.depth();
        sameMany();
    }


    // IS_EMPTY tests

    @Test
    public void testIsEmptyEmpty() {
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        addOne();
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testIsEmptyTwo() {
        addTwo();
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testIsEmptyMany() {
        addMany();
        assertFalse(instance.isEmpty());
    }

    @Test
    public void testIsEmptySame() {
        addMany();
        instance.isEmpty();
        sameMany();
    }


    // GET_FIRST test

    @Test(expected =  java.util.NoSuchElementException.class)
    public void testGetFirstEmpty() {
        instance.getFirst();
    }

    @Test
    public void testGetFirst() {
        addOne();
        assertEquals("Test", instance.getFirst());
    }

    @Test
    public void testGetFirstTwo() {
        addTwo();
        assertEquals("Test1", instance.getFirst());
    }

    @Test
    public void testGetFirstMany() {
        addMany();
        assertEquals("Test4", instance.getFirst());
    }

    @Test
    public void testGetFirstSame() {
        addMany();
        instance.getFirst();
        sameMany();
    }


    // REMOVE tests

    @Test(expected =  java.util.NoSuchElementException.class)
    public void testRemoveEmpty() {
        instance.remove("Test");
    }

    @Test
    public void testRemove() {
        addOne();
        assertEquals("Test", instance.remove("Test"));
        assertEquals(0, instance.size());
    }

    @Test
    public void testRemoveTwo() {
        addTwo();
        assertEquals("Test2", instance.remove("Test2"));
        assertFalse(instance.exists("Test2"));

        assertEquals("Test1", instance.remove("Test1"));
        assertFalse(instance.exists("Test1"));

        assertEquals(0, instance.size());
    }

    @Test
    public void testRemoveMany() {
        addMany();

        assertEquals("Test4", instance.remove("Test4"));
        assertFalse(instance.exists("Test4"));

        assertEquals("Test5", instance.remove("Test5"));
        assertFalse(instance.exists("Test5"));

        assertEquals("Test12", instance.remove("Test12"));
        assertFalse(instance.exists("Test12"));

        assertEquals("Test13", instance.remove("Test13"));
        assertFalse(instance.exists("Test13"));

        assertEquals("Test15", instance.remove("Test15"));
        assertFalse(instance.exists("Test15"));

        assertEquals("Test17", instance.remove("Test17"));
        assertFalse(instance.exists("Test17"));

        assertEquals("Test19", instance.remove("Test19"));
        assertFalse(instance.exists("Test19"));

        assertEquals("Test20", instance.remove("Test20"));
        assertFalse(instance.exists("Test20"));

        assertEquals(0, instance.size());
    }

    @Test(expected =  java.util.NoSuchElementException.class)
    public void testRemoveUnexistent () {
        addMany();
        instance.remove("Test");
    }


    // REMOVE_FIRST tests

    @Test
    public void testRemoveFirst(){
        addOne();
        assertEquals("Test", instance.removeFirst());
        assertFalse(instance.exists("Test1"));
    }

    @Test(expected =  java.util.NoSuchElementException.class)
    public void testRemoveFirstEmpty() {
        instance.removeFirst();
    }

    @Test
    public void testRemoveFirstTwo() {
        addTwo();
        assertEquals("Test1", instance.removeFirst());
        assertFalse(instance.exists("Test1"));

        assertEquals("Test2", instance.removeFirst());
        assertFalse(instance.exists("Test2"));

        assertEquals(0, instance.size());
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testRemoveFirstMany() {
        addMany();

        assertEquals("Test4", instance.removeFirst());
        assertFalse(instance.exists("Test4"));

        assertEquals("Test5", instance.removeFirst());
        assertFalse(instance.exists("Test5"));

        assertEquals("Test12", instance.removeFirst());
        assertFalse(instance.exists("Test12"));

        assertEquals("Test13", instance.removeFirst());
        assertFalse(instance.exists("Test13"));

        assertEquals("Test15", instance.removeFirst());
        assertFalse(instance.exists("Test15"));

        assertEquals("Test17", instance.removeFirst());
        assertFalse(instance.exists("Test17"));

        assertEquals("Test19", instance.removeFirst());
        assertFalse(instance.exists("Test19"));

        assertEquals("Test20", instance.removeFirst());
        assertFalse(instance.exists("Test20"));
    }


    // AS_LIST tests

    @Test
    public void testAsListEmpty() {
        assertEquals("[ ]", instance.asList());
    }

    @Test
    public void testAsListOne() {
        addOne();
        assertEquals("[\"Test\", ]", instance.asList());
    }

    @Test
    public void testAsListTwo() {
        addTwo();
        assertEquals("[\"Test1\", \"Test2\"]", instance.asList());
    }

    @Test
    public void testAsListMany() {
        addMany();
        assertEquals("[\"Test4\", \"Test5\", \"Test12\", \"Test13\", "
                            + "\"Test15\", \"Test17\", \"Test19\", \"Test20\"]",
                            instance.asList());
    }

    @Test
    public void testAsListSame() {
        addMany();
        instance.asList();
        sameMany();
    }

}
