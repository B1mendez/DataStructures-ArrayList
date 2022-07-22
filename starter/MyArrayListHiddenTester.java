/**
 * Name: Brian Mendez
 * ID: A17211975
 * Email: b1mendez@ucsd.edu
 * Sources used: Tutor Andrew Tang for the hidden junit tests, 
 * Wk2ArrayListWorksheet<E> lecture slide, and Zybooks.
 * 
 * Description: Hidden test to check for illegal arguments in the program 
 * and test other examples that were not used in the public test. This 
 * tester file uses junit testing.
 */

 //IMPORTANT: Do not change the headers!

import static org.junit.Assert.*;

import java.security.InvalidAlgorithmParameterException;

import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.plaf.synth.SynthScrollBarUI;

import org.junit.*;

public class MyArrayListHiddenTester {
    static final int DEFAULT_CAPACITY = 5;
    static final int INVALID_CAPACITY = -1;

    Object[] arr = new Object[10];
    Integer[] arrInts = { 1, 2, 3, 4, 5 };
    Integer[] nullArr = null; 

    private MyArrayList listNull, listDefaultCap, listFullCapacity, listInvalidCapacity, listWithNull, listFull;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test */
    @Before
    public void setUp() throws Exception {
        listNull = new MyArrayList();
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listFullCapacity = new MyArrayList(DEFAULT_CAPACITY);
        listInvalidCapacity = new MyArrayList();
        listWithNull = new MyArrayList(arr);
        listFull = new MyArrayList<Integer>(arrInts); 
    }

    /**
     * Aims to test the capacity argument constructor when the input
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){
        try {
            MyArrayList<Integer> listInvalidCapacity = new MyArrayList<>(INVALID_CAPACITY); 
            fail("Invalid Argument");
        }   catch(IllegalArgumentException e) {
            //passes
            System.out.println("Exception caught");
        }
    }

    /**
     * Aims to test the Array argument constructor when the input
     * is null
     */
    @Test
    public void testConstructorNullArg(){
        try {
            MyArrayList<Integer> listNull = new MyArrayList<>(nullArr); 
            fail("Invalid Argument");
        }   catch(NullPointerException e) {
            //passes
            System.out.println("Exception caught");
        }
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listFull.append(0); 

        assertEquals("Check that append increases the size", 6, listFull.size);
        assertEquals("Check that the capacity is updated", 10, listFull.data.length);
        assertEquals("Check the correct element", null, listFull.data[6]);
        assertEquals("Check the correct element", 0, listFull.data[5]);
    }

    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listDefaultCap.prepend(null);

        assertEquals("Check that prepended item", null, listDefaultCap.data[0]);
        assertEquals("Check list size after the prepend", 1, listDefaultCap.size);
        assertEquals("Check that capacity is unchanged", 5, listDefaultCap.data.length);
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBound(){
        MyArrayList<Integer> listDefaulCap = new MyArrayList<>(arrInts); 
        try {
            listDefaultCap.insert(-1, Integer.valueOf(10));
            fail("Index out of Bounds");

        }   catch(IndexOutOfBoundsException e) {
            System.out.println("Exception caught");
        }
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        for (int i = 0; i < listDefaultCap.data.length; i ++){
            listDefaultCap.insert(i, i * 2);
        }

        assertEquals("should insert 5 to the list", 0, listDefaultCap.data[0]);
        assertEquals("should increment size", 5, listDefaultCap.size);
        assertEquals("check that if the capacity is updated", 5, listDefaultCap.data.length);
        assertEquals("Check the correct element", 2, listDefaultCap.data[1]);
        assertEquals("Check the correct element", 4, listDefaultCap.data[2]);
        assertEquals("Check the correct element", 6, listDefaultCap.data[3]);
        assertEquals("Check the correct element", 8, listDefaultCap.data[4]);
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        boolean exceptionThrown = false;
        try {
            // Put the code that might throw an exception here.
            listFull.get(100);
        }   catch (IndexOutOfBoundsException e) {
            // You only get here if exception was thrown.  Do something here.
            exceptionThrown = true;
        }
        assertTrue("Except not thrown for empty array removeFirst",  exceptionThrown);  
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
        boolean exceptionThrown = false;
        try {
            // Put the code that might throw an exception here.
            listFull.set(100, 2);
        }   catch (IndexOutOfBoundsException e) {
            // You only get here if exception was thrown.  Do something here.
            exceptionThrown = true;
        }
        assertTrue("Except not thrown for empty array removeFirst",  exceptionThrown);
    }


    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        boolean exceptionThrown = false;
        try {
            // Put the code that might throw an exception here.
            listFull.remove(100);
        }   catch (IndexOutOfBoundsException e) {
            // You only get here if exception was thrown.  Do something here.
            exceptionThrown = true;
        }
        assertTrue("Except not thrown for empty array removeFirst",  exceptionThrown);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
        boolean exceptionThrown = false;
        try {
            // Put the code that might throw an exception here.
            listFull.expandCapacity(2);
        }   catch (IllegalArgumentException e) {
            // You only get here if exception was thrown.  Do something here.
            exceptionThrown = true;
        }
        assertTrue("Except not thrown for empty array removeFirst",  exceptionThrown);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than double(2x) the current capacity
     */
    @Test
    public void testExpandCapacityExplode(){
        listFull.expandCapacity(40);

        assertEquals("Capacity should be updated", 40, listFull.data.length);
        assertEquals("The size should still be the same", 5, listFull.size);
    }

}
