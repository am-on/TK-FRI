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

    private void addTwoReversed() {
        instance.add("Test2");
        instance.add("Test1");
    }

    private void addMany() {
        instance.add("Test8");
        instance.add("Test0");
        instance.add("Test9");
        instance.add("Test7");
        instance.add("Test3");
        instance.add("Test6");
        instance.add("Test5");
        instance.add("Test1");
    }

    private void containsOne() {
        assertTrue(instance.exists("Test"));
    }

    private void containsTwo() {
        assertTrue(instance.exists("Test1"));
        assertTrue(instance.exists("Test2"));
    }

    private void containsMany() {
        assertTrue(instance.exists("Test0"));
        assertTrue(instance.exists("Test1"));
        assertTrue(instance.exists("Test3"));
        assertTrue(instance.exists("Test5"));
        assertTrue(instance.exists("Test6"));
        assertTrue(instance.exists("Test7"));
        assertTrue(instance.exists("Test8"));
        assertTrue(instance.exists("Test9"));
    }

    private void sameMany() {
        assertEquals(8, instance.size());
        containsMany();
        assertEquals("Test0", instance.removeFirst());
        assertEquals("Test1", instance.removeFirst());
        assertEquals("Test3", instance.removeFirst());
        assertEquals("Test5", instance.removeFirst());
        assertEquals("Test6", instance.removeFirst());
        assertEquals("Test7", instance.removeFirst());
        assertEquals("Test8", instance.removeFirst());
        assertEquals("Test9", instance.removeFirst());
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
    public void testAddThree() {

        addTwo();
        instance.add("Test3");

        containsTwo();
        assertTrue(instance.exists("Test3"));
    }

    @Test
    public void testAddTwoReversed() {
        addTwoReversed();
        containsTwo();
    }

    @Test
    public void testAddMany() {
        addMany();
        containsMany();
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testAddSameElements() {
        addOne();
        addOne();
    }


    // EXISTS test

    @Test
    public void testExists() {
        addOne();
        assertTrue(instance.exists("Test"));
        assertFalse(instance.exists("Foo"));
    }

    @Test
    public void testExistsEmpty() {
        assertFalse(instance.exists("Foo"));
    }

    @Test
    public void testExistsTwo() {
        addTwo();
        assertTrue(instance.exists("Test2"));
        assertTrue(instance.exists("Test1"));
        assertFalse(instance.exists("Foo"));
    }

    @Test
    public void testExistsMany() {
        addMany();
        containsMany();
    }

    @Test
    public void testExistsSame() {
        addMany();
        instance.exists("Foo");
        instance.exists("Test0");
        instance.exists("Test9");
        sameMany();
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
        assertEquals(2, instance.depth());
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
    public void testGetFirstTwoReversed() {
        addTwoReversed();
        assertEquals("Test1", instance.getFirst());
    }

    @Test
    public void testGetFirstMany() {
        addMany();
        assertEquals("Test0", instance.getFirst());
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
    public void testRemoveTwoReversed() {
        addTwoReversed();
        assertEquals("Test1", instance.remove("Test1"));
        assertFalse(instance.exists("Test1"));

        assertEquals("Test2", instance.remove("Test2"));
        assertFalse(instance.exists("Test2"));

        assertEquals(0, instance.size());
    }

    @Test
    public void testRemoveMany() {
        addMany();

        assertEquals("Test0", instance.remove("Test0"));
        assertFalse(instance.exists("Test0"));

        assertEquals("Test1", instance.remove("Test1"));
        assertFalse(instance.exists("Test1"));

        assertEquals("Test3", instance.remove("Test3"));
        assertFalse(instance.exists("Test3"));

        assertEquals("Test5", instance.remove("Test5"));
        assertFalse(instance.exists("Test5"));

        assertEquals("Test6", instance.remove("Test6"));
        assertFalse(instance.exists("Test6"));

        assertEquals("Test7", instance.remove("Test7"));
        assertFalse(instance.exists("Test7"));

        assertEquals("Test8", instance.remove("Test8"));
        assertFalse(instance.exists("Test8"));

        assertEquals("Test9", instance.remove("Test9"));
        assertFalse(instance.exists("Test9"));

        assertEquals(0, instance.size());
    }

    @Test(expected =  java.util.NoSuchElementException.class)
    public void testRemoveUnexistent () {
        addTwo();
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

        assertEquals("Test0", instance.removeFirst());
        assertFalse(instance.exists("Test0"));

        assertEquals("Test1", instance.removeFirst());
        assertFalse(instance.exists("Test1"));

        assertEquals("Test3", instance.removeFirst());
        assertFalse(instance.exists("Test3"));

        assertEquals("Test5", instance.removeFirst());
        assertFalse(instance.exists("Test5"));

        assertEquals("Test6", instance.removeFirst());
        assertFalse(instance.exists("Test6"));

        assertEquals("Test7", instance.removeFirst());
        assertFalse(instance.exists("Test7"));

        assertEquals("Test8", instance.removeFirst());
        assertFalse(instance.exists("Test8"));

        assertEquals("Test9", instance.removeFirst());
        assertFalse(instance.exists("Test9"));
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
        assertEquals("[\"Test0\", \"Test1\", \"Test3\", \"Test5\", "
                            + "\"Test6\", \"Test7\", \"Test8\", \"Test9\"]",
                            instance.asList());
    }

    @Test
    public void testAsListSame() {
        addMany();
        instance.asList();
        sameMany();
    }

}
