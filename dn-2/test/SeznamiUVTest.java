
import org.junit.*;

import java.text.DecimalFormat;
import java.util.Random;

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
        assertEquals("Invalid command", uv.processInput(""));
    }

    @Test
    public void testAll() {
        tests();
    }

    @Test
    public void testAddStudent() {
        addStudent(5);
        assertEquals("No. of students: 1", uv.processInput("count"));
        count(1);
    }

    @Test
    public void testAddFiveStudents() {
        for (int i = 0; i < 5; i++) {
            addStudent(5+i);
            assertEquals("No. of students: " + (i+1), uv.processInput("count"));
        }
        count(5);
    }

    @Test
    public void testDuplicateEntryId() {
        String[] data = addStudent(5);
        data[1] = "ImeStudentaX";
        addStudent(data, "Student already exists");
        count(1);
        reset();
    }


    @Test
    public void testDuplicateEntryNames() {
        String[] data = addStudent(5);
        data[0] = "6315";
        addStudent(data, "Student already exists");
        count(1);
        reset();
    }

    @Test
    public void testDuplicateEntryIdAndName() {
        addStudent(5);
        addStudent(5, "Student already exists");
        count(1);
        reset();
    }

    @Test
    public void testWrongId() {
        String[] data = randomData(5);
        data[0] = "-6315";
        addStudent(data, "Invalid input data");
        count(0);
        reset();
    }

    @Test
    public void testWrongIdString() {
        String[] data = randomData(5);
        data[0] = "wrongID";
        addStudent(data, "Invalid input data");
        count(0);
        reset();
    }

    @Test
    public void testWrongAvgScoreUnderZero() {
        String[] data = randomData(5);
        data[0] = "-1";
        addStudent(data, "Invalid input data");
        count(0);
        reset();
    }

    @Test
    public void testWrongAvgScoreOverTen() {
        String[] data = randomData(5);
        data[0] = "10.1";
        addStudent(data, "Invalid input data");
        count(0);
        reset();
    }

    @Test
    public void testNoId() {
        String[] data = randomData(5);
        data[0] = "";
        addStudent(data, "Invalid input data");
        count(0);
        reset();
    }

    @Test
    public void testNoName() {
        String[] data = randomData(5);
        data[1] = "";
        addStudent(data, "Invalid input data");
        count(0);
        reset();
    }

    @Test
    public void testNoLastName() {
        String[] data = randomData(5);
        data[2] = "";
        addStudent(data, "Invalid input data");
        count(0);
        reset();
    }

    @Test
    public void testNoAvgScore() {
        String[] data = randomData(5);
        data[3] = "";
        addStudent(data, "Invalid input data");
        count(0);
        reset();
    }

    // remove

    @Test
    public void testRemoveOnEmptyByID() {
        assertEquals("Student does not exists", uv.processInput("remove " + 000));
        reset();
    }

    @Test
    public void testRemoveOnEmptyByName() {
        String[] data = randomData(5);
        removeByName(data, "Student does not exists");
        reset();
    }

    @Test
    public void testRemoveOneByID() {
        String[] data = addStudent(5);
        removeById(data);
        count(0);
        reset();
    }

    @Test
    public void testRemoveOneByName() {
        String[] data = addStudent(5);
        removeByName(data);
        count(0);
        reset();
    }

    @Test
    public void testRemoveOneByIDNonExistent() {
        String[] data = addStudent(5);
        data[0] = "123";
        removeById(data, "Student does not exists");
        count(1);
        reset();
    }

    @Test
    public void testRemoveOneByNameNonExistent() {
        String[] data = addStudent(5);
        data[1] = "ABC";
        removeByName(data, "Student does not exists");
        count(1);
        reset();
    }

    @Test
    public void testRemoveTwoById() {
        String[] data1 = addStudent(5);
        String[] data2 = addStudent(6);

        removeById(data1);
        count(1);

        removeById(data2);
        count(0);

        removeById(data1, "Student does not exists");
        removeById(data2, "Student does not exists");
        removeByName(data1, "Student does not exists");
        removeByName(data2, "Student does not exists");

        reset();
    }

    @Test
    public void testRemoveTwoByName() {
        String[] data1 = addStudent(5);
        String[] data2 = addStudent(6);

        removeByName(data1);
        count(1);

        removeByName(data2);
        count(0);

        removeByName(data1, "Student does not exists");
        removeByName(data2, "Student does not exists");
        removeById(data1, "Student does not exists");
        removeById(data2, "Student does not exists");

        reset();
    }

    @Test
    public void testRemoveTwoByIdNonExistent() {
        String[] data1 = addStudent(5);
        addStudent(6);

        data1[0] = "0123";

        removeById(data1, "Student does not exists");
        count(2);

        reset();
    }

    @Test
    public void testRemoveTwoByNameNonExistent() {
        String[] data1 = addStudent(5);
        addStudent(6);

        data1[1] = "ABC";

        removeByName(data1, "Student does not exists");
        count(2);

        reset();
    }

    @Test
    public void testRemoveManyById() {
        String[] data1 = addStudent(5);
        String[] data2 = addStudent(6);
        String[] data3 = addStudent(7);
        String[] data4 = addStudent(8);

        removeById(data1);
        count(3);

        removeById(data2);
        count(2);

        removeById(data3);
        count(1);

        removeById(data4);
        count(0);

        removeByName(data1, "Student does not exists");
        removeByName(data2, "Student does not exists");
        removeByName(data3, "Student does not exists");
        removeByName(data4, "Student does not exists");

        removeById(data1, "Student does not exists");
        removeById(data2, "Student does not exists");
        removeById(data3, "Student does not exists");
        removeById(data4, "Student does not exists");

        reset();
    }

    @Test
    public void testRemoveManyByName() {
        String[] data1 = addStudent(5);
        String[] data2 = addStudent(6);
        String[] data3 = addStudent(7);
        String[] data4 = addStudent(8);

        removeByName(data1);
        count(3);

        removeByName(data2);
        count(2);

        removeByName(data3);
        count(1);

        removeByName(data4);
        count(0);

        removeById(data1, "Student does not exists");
        removeById(data2, "Student does not exists");
        removeById(data3, "Student does not exists");
        removeById(data4, "Student does not exists");

        removeByName(data1, "Student does not exists");
        removeByName(data2, "Student does not exists");
        removeByName(data3, "Student does not exists");
        removeByName(data4, "Student does not exists");

        reset();
    }

    @Test
    public void testRemoveInvalidId() {
        String[] data = addStudent(5);
        data[0] = "abc";

        removeById(data, "Invalid input data");
        count(1);

        reset();
    }

    @Test
    public void testRemoveInvalidName() {
        String[] data = addStudent(5);
        data[1] = "";

        removeByName(data, "Invalid input data");
        count(1);

        reset();
    }

    @Test
    public void testRemoveInvalidLastName() {
        String[] data = addStudent(5);
        data[2] = "";

        removeByName(data, "Invalid input data");
        count(1);

        reset();
    }

    private void removeById(String[] data) {
        assertEquals("OK", uv.processInput("remove " + data[0]));
    }

    private void removeById(String[] data, String lastMsg) {
        assertEquals(lastMsg, uv.processInput("remove " + data[0]));
    }

    private void removeByName(String[] data) {
        assertEquals("First name: ", uv.processInput("remove"));
        assertEquals("Last name: ", uv.processInput(data[1]));
        assertEquals("OK", uv.processInput(data[2]));
    }

    private void removeByName(String[] data, String lastMsg) {
        assertEquals("First name: ", uv.processInput("remove"));
        assertEquals("Last name: ", uv.processInput(data[1]));
        assertEquals(lastMsg, uv.processInput(data[2]));
    }

    // search

    private String searchResult(String[] data) {
         return "\t" + data[0] + " | " + data[2] + ", " + data[1] + " | " + data[3];
    }

    private void searchByName(String[] data) {
        assertEquals("First name: ", uv.processInput("search"));
        assertEquals("Last name: ", uv.processInput(data[1]));
        assertEquals(searchResult(data), uv.processInput(data[2]));
    }

    private void searchByName(String[] data, String lastMsg) {
        assertEquals("First name: ", uv.processInput("search"));
        assertEquals("Last name: ", uv.processInput(data[1]));
        assertEquals(lastMsg, uv.processInput(data[2]));
    }

    private void searchById(String[] data) {
        assertEquals(searchResult(data), uv.processInput("remove " + data[0]));
    }

    private void searchById(String[] data, String lastMsg) {
        assertEquals(lastMsg, uv.processInput("remove " + data[0]));
    }

    @Test
    public void testSearchOnEmptyByID() {
        assertEquals("Student does not exists", uv.processInput("search " + 000));
        reset();
    }

    @Test
    public void testSearchOnEmptyByName() {
        String[] data = randomData(5);
        removeByName(data, "Student does not exists");
        reset();
    }

    @Test
    public void testSearchOneByID() {
        String[] data = addStudent(5);
        searchById(data);
        count(1);
        reset();
    }

    @Test
    public void testSearchOneByName() {
        String[] data = addStudent(5);
        searchByName(data);
        count(1);
        reset();
    }

    @Test
    public void testSearchOneByIDNonExistent() {
        String[] data = addStudent(5);
        data[0] = "123";
        searchById(data, "Student does not exists");
        count(1);
        reset();
    }

    @Test
    public void testSearchOneByNameNonExistent() {
        String[] data = addStudent(5);
        data[1] = "ABC";
        searchByName(data, "Student does not exists");
        count(1);
        reset();
    }

    @Test
    public void testSearchTwoById() {
        String[] data1 = addStudent(5);
        String[] data2 = addStudent(6);

        searchById(data1);
        count(2);

        searchById(data2);
        count(2);

        reset();
    }

    @Test
    public void testSearchTwoByName() {
        String[] data1 = addStudent(5);
        String[] data2 = addStudent(6);

        searchByName(data1);
        count(2);

        searchByName(data2);
        count(2);

        reset();
    }

    @Test
    public void testSearchTwoByIdNonExistent() {
        String[] data1 = addStudent(5);
        addStudent(6);

        data1[0] = "0123";

        searchById(data1, "Student does not exists");
        count(2);

        reset();
    }

    @Test
    public void testSearchTwoByNameNonExistent() {
        String[] data1 = addStudent(5);
        addStudent(6);

        data1[1] = "ABC";

        searchByName(data1, "Student does not exists");
        count(2);

        reset();
    }

    @Test
    public void testSearchManyById() {
        String[] data1 = addStudent(5);
        String[] data2 = addStudent(6);
        String[] data3 = addStudent(7);
        String[] data4 = addStudent(8);

        searchById(data1);
        count(4);

        searchById(data2);
        count(4);

        searchById(data3);
        count(4);

        searchById(data4);
        count(4);

        reset();
    }

    @Test
    public void testsearchManyByName() {
        String[] data1 = addStudent(5);
        String[] data2 = addStudent(6);
        String[] data3 = addStudent(7);
        String[] data4 = addStudent(8);

        searchByName(data1);
        count(4);

        searchByName(data2);
        count(4);

        searchByName(data3);
        count(4);

        searchByName(data4);
        count(4);

        reset();
    }

    @Test
    public void testSearchInvalidId() {
        String[] data = addStudent(5);
        data[0] = "abc";

        searchById(data, "Invalid input data");
        count(1);

        reset();
    }

    @Test
    public void testSearchInvalidName() {
        String[] data = addStudent(5);
        data[1] = "";

        searchByName(data, "Invalid input data");
        count(1);

        reset();
    }

    @Test
    public void testSearchInvalidLastName() {
        String[] data = addStudent(5);
        data[2] = "";

        removeByName(data, "Invalid input data");
        count(1);

        reset();
    }

    // test save

    @Test
    public void testSave() {
        testAddFiveStudents();
        assertEquals("OK", uv.processInput("save students.bin"));

        reset();
    }

    @Test
    public void testRestore() {
        testAddFiveStudents();
        assertEquals("OK", uv.processInput("save students.bin"));
        assertEquals("OK", uv.processInput("restore students.bin"));

        for (int i = 0; i < 5; i++) {
            String[] data = addStudent(5+i);
            searchByName(data);
        }

        count(5);

        reset();
    }

    // TODO: I/O tests


    // print

    @Test
    public void testPrintEmpty() {
        assertEquals("No. of students: 0", uv.processInput("print"));

        reset();
    }

    @Test
    public void testPrintOne() {
        String[] data = addStudent(5);
        assertEquals("No. of students: 1" + searchResult(data), uv.processInput("print"));
        count(5);

        reset();
    }

    @Test
    public void testPrintMany() {
        String[] data1 = randomData(5);
        String[] data2 = randomData(6);
        String[] data3 = randomData(7);
        String[] data4 = randomData(8);

        data1[2] = "A";
        data2[2] = "B";
        data3[2] = "C";
        data4[2] = "D";

        String result = "No. of students: 4\n" + searchResult(data1)
                                               + "\n" + searchResult(data2)
                                               + "\n" + searchResult(data3)
                                               + "\n" + searchResult(data4);

        assertEquals(result, uv.processInput("print"));

        reset();
    }

    // count

    @Test
    public void testCountEmpty() {
        count(0);
        reset();
    }

    @Test
    public void testCountOne() {
        testAddStudent();
        count(1);
        reset();
    }


    @Test
    public void testCountMany() {
        testAddFiveStudents();
        count(5);
        reset();
    }



    // other

    private void count(int i) {
        assertEquals("No. of students: " + i, uv.processInput("count"));
    }

    private String randomString(Random rnd) {
        char[] chars = " abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            char c = chars[rnd.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }

    private String[] randomData(int seed) {
        Random rnd = new Random(seed);
        DecimalFormat df = new DecimalFormat("#.##");

        String[] data = new String[4];
        data[0] = "631502" + rnd.nextInt();
        data[1] = "ImeStudenta" + randomString(rnd);
        data[2] = "PriimekStudenta" + randomString(rnd);
        data[3] = df.format(rnd.nextFloat()*10);

        return data;
    }

    private String[] addStudent(int seed) {
        String[] data = randomData(seed);

        assertEquals("Student ID: ", uv.processInput("add"));
        assertEquals("First name: ", uv.processInput(data[0]));
        assertEquals("Last name: ", uv.processInput(data[1]));
        assertEquals("Avg. grade: ", uv.processInput(data[2]));
        assertEquals("OK", uv.processInput(data[3]));

        return data;
    }

    private String [] addStudent(int seed, String lastMsg) {
        String[] data = randomData(seed);

        assertEquals("Student ID: ", uv.processInput("add"));
        assertEquals("First name: ", uv.processInput(data[0]));
        assertEquals("Last name: ", uv.processInput(data[1]));
        assertEquals("Avg. grade: ", uv.processInput(data[2]));
        assertEquals(lastMsg, uv.processInput(data[3]));

        return data;
    }

    private void addStudent(String[] data) {
        assertEquals("Student ID: ", uv.processInput("add"));
        assertEquals("First name: ", uv.processInput(data[0]));
        assertEquals("Last name: ", uv.processInput(data[1]));
        assertEquals("Avg. grade: ", uv.processInput(data[2]));
        assertEquals("OK", uv.processInput(data[3]));
    }

    private void addStudent(String[] data, String lastMsg) {
        assertEquals("Student ID: ", uv.processInput("add"));
        assertEquals("First name: ", uv.processInput(data[0]));
        assertEquals("Last name: ", uv.processInput(data[1]));
        assertEquals("Avg. grade: ", uv.processInput(data[2]));
        assertEquals(lastMsg, uv.processInput(data[3]));
    }


    // TO DO
    // napišite teste za sklad, prioritetno vrsto in BS drevo
    // testi kličejo (zaporedoma) vse operacije nad določeno strukturo 
    // glej POMOZNE METODE



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


    public void test23(boolean add) {
        if (add) {
            testAddTestSequence();
            assertEquals("OK", uv.processInput("add Test6"));
            assertEquals("OK", uv.processInput("add Test7"));
            assertEquals("OK", uv.processInput("add Test8"));
            assertEquals("OK", uv.processInput("add Test9"));
            assertEquals("OK", uv.processInput("add Test10"));
        }
        assertEquals("Test1", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test4", uv.processInput("remove_first"));
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test6", uv.processInput("remove_first"));
        assertEquals("Test7", uv.processInput("remove_first"));
        assertEquals("Test8", uv.processInput("remove_first"));
        assertEquals("Test9", uv.processInput("remove_first"));
        assertEquals("Test10", uv.processInput("remove_first"));
    }

}
