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

    @Test
    public void testAddFour() {
        instance.add("Test8");
        instance.add("Test0");
        instance.add("Test9");
        instance.add("Test7");
        assertEquals("[\"Test0\", \"Test7\", \"Test8\", \"Test9\"]", instance.asList());
    }

    @Test
    public void testAddSix() {
        instance.add("Test8");
        instance.add("Test0");
        instance.add("Test9");
        instance.add("Test7");
        instance.add("Test3");
        instance.add("Test6");

        instance.asList();

        assertEquals("[\"Test0\", \"Test3\", \"Test6\", \"Test7\", \"Test8\", \"Test9\"]", instance.asList());
    }

    @Test
    public void testAddManyReversed() {
        instance.add("l");
        instance.add("k");
        instance.add("j");
        instance.add("i");
        instance.add("h");
        instance.add("g");
        instance.add("f");
        instance.add("e");
        instance.add("d");
        instance.add("c");
        instance.add("b");
        instance.add("a");
        assertEquals("[\"a\", \"b\", \"c\", \"d\", \"e\", \"f\", \"g\", \"h\", \"i\", \"j\", \"k\", \"l\"]", instance.asList());
    }

    @Test
    public void testAddManyMiddle() {
        instance.add("l");
        instance.add("k");
        instance.add("j");
        instance.add("i");
        instance.add("h");
        instance.add("g");
        instance.add("f");
        instance.add("e");
        instance.add("d");
        instance.add("c");
        instance.add("b");
        instance.add("a");
        instance.add("f0");
        instance.add("g0");
        instance.add("g1");
        instance.add("g2");
        instance.add("g3");
        instance.add("f3");
        instance.add("f4");
        assertEquals("[\"a\", \"b\", \"c\", \"d\", \"e\", \"f\", \"f0\", \"f3\", \"f4\", \"g\", \"g0\", \"g1\", \"g2\", \"g3\", \"h\", \"i\", \"j\", \"k\", \"l\"]", instance.asList());
    }

    @Test
    public void testAddM() {
        instance.add("d0xa");
        instance.add("e0xa");
        instance.add("m0xa");
        instance.add("n0xa");
        instance.add("a");
        instance.add("b");
        instance.add("c");
        instance.add("d");
        instance.add("e");
        instance.add("m");
        instance.add("a0xa");
        instance.add("b0xa");
        instance.add("c0xa");
        instance.add("o0xa");
        instance.add("p0xa");
        instance.add("r0xa");
        instance.add("s0xa");
        instance.add("t0xa");
        instance.add("u0xa");
        instance.add("f0xa");
        instance.add("g0xa");
        instance.add("h0xa");
        instance.add("i0xa");
        instance.add("j0xa");
        instance.add("k0xa");
        instance.add("l0xa");
        instance.add("00xa");
        instance.add("70xa");
        instance.add("80xa");
        instance.add("90xa");
        instance.add("n");
        instance.add("o");
        instance.add("p");
        instance.add("r");
        instance.add("s");
        instance.add("10xa");
        instance.add("20xa");
        instance.add("30xa");
        instance.add("40xa");
        instance.add("50xa");
        instance.add("60xa");
        instance.add("t");
        instance.add("u");
        instance.add("a0");
        instance.add("b0");
        instance.add("c0");
        instance.add("d0");
        instance.add("e0");
        instance.add("m0");
        instance.add("n0");
        instance.add("o0");
        instance.add("p0");
        instance.add("a0x");
        instance.add("b0x");
        instance.add("c0x");
        instance.add("d0x");
        instance.add("e0x");
        instance.add("m0x");
        instance.add("n0x");
        instance.add("o0x");
        instance.add("p0x");
        instance.add("r0x");
        instance.add("s0x");
        instance.add("t0x");
        instance.add("u0x");
        instance.add("f0x");
        instance.add("g0x");
        instance.add("h0x");
        instance.add("i0x");
        instance.add("j0x");
        instance.add("k0x");
        instance.add("l0x");
        instance.add("00x");
        instance.add("10x");
        instance.add("1");
        instance.add("2");
        instance.add("3");
        instance.add("4");
        instance.add("5");
        instance.add("20x");
        instance.add("30x");
        instance.add("40x");
        instance.add("50x");
        instance.add("60x");
        instance.add("70x");
        instance.add("80x");
        instance.add("90x");
        instance.add("r0");
        instance.add("s0");
        instance.add("t0");
        instance.add("u0");
        instance.add("f0");
        instance.add("g0");
        instance.add("h0");
        instance.add("i0");
        instance.add("j0");
        instance.add("k0");
        instance.add("l0");
        instance.add("00");
        instance.add("10");
        instance.add("20");
        instance.add("30");
        instance.add("40");
        instance.add("50");
        instance.add("60");
        instance.add("70");
        instance.add("80");
        instance.add("90");
        instance.add("f");
        instance.add("g");
        instance.add("h");
        instance.add("i");
        instance.add("j");
        instance.add("k");
        instance.add("l");
        instance.add("0");
        instance.add("6");
        instance.add("7");
        instance.add("8");
        instance.add("9");

        assertEquals("[\"0\", \"00\", \"00x\", \"00xa\", \"1\", \"10\", \"10x\", \"10xa\", \"2\", \"20\", " +
                        "\"20x\", \"20xa\", \"3\", \"30\", \"30x\", \"30xa\", \"4\", \"40\", \"40x\", \"40xa\", " +
                        "\"5\", \"50\", \"50x\", \"50xa\", \"6\", \"60\", \"60x\", \"60xa\", \"7\", \"70\", \"70x\", " +
                        "\"70xa\", \"8\", \"80\", \"80x\", \"80xa\", \"9\", \"90\", \"90x\", \"90xa\", \"a\", " +
                        "\"a0\", \"a0x\", \"a0xa\", \"b\", \"b0\", \"b0x\", \"b0xa\", \"c\", \"c0\", \"c0x\", " +
                        "\"c0xa\", \"d\", \"d0\", \"d0x\", \"d0xa\", \"e\", \"e0\", \"e0x\", \"e0xa\", \"f\", " +
                        "\"f0\", \"f0x\", \"f0xa\", \"g\", \"g0\", \"g0x\", \"g0xa\", \"h\", \"h0\", \"h0x\", " +
                        "\"h0xa\", \"i\", \"i0\", \"i0x\", \"i0xa\", \"j\", \"j0\", \"j0x\", \"j0xa\", \"k\", " +
                        "\"k0\", \"k0x\", \"k0xa\", \"l\", \"l0\", \"l0x\", \"l0xa\", \"m\", \"m0\", \"m0x\", " +
                        "\"m0xa\", \"n\", \"n0\", \"n0x\", \"n0xa\", \"o\", \"o0\", \"o0x\", \"o0xa\", \"p\", " +
                        "\"p0\", \"p0x\", \"p0xa\", \"r\", \"r0\", \"r0x\", \"r0xa\", \"s\", \"s0\", \"s0x\", " +
                        "\"s0xa\", \"t\", \"t0\", \"t0x\", \"t0xa\", \"u\", \"u0\", \"u0x\", \"u0xa\"]",
                    instance.asList());

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
