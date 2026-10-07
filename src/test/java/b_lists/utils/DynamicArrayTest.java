package b_lists.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void get() {
        DynamicArray myList = new DynamicArray();
        myList.add(5);
        int expectedResult = 5;
        int result = myList.get(0);

        assertEquals(expectedResult, result);
    }

    @Test
    void get_AccessBeforeList(){
        DynamicArray myList = new DynamicArray();
        myList.add(5);
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(-1);
                }, "Incorrect (or no) exception thrown"
        );
    }

    @Test
    void get_AccessAfterList(){
        DynamicArray myList = new DynamicArray();
        myList.add(5);
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(4);
                }, "Incorrect (or no) exception thrown"
        );
    }

    @Test
    void indexOf(){
        DynamicArray myList = new DynamicArray();
        myList.add(5);
        int expectedResult = 0;
        int result = myList.indexOf(5);

        assertEquals(expectedResult, result);
    }

    @Test
    void indexOf_NotFound(){
        DynamicArray myList = new DynamicArray();
        myList.add(5);
        int expectedResult = -1;
        int result = myList.indexOf(10);

        assertEquals(expectedResult, result);
    }

    @Test
    void IndexOf_EmptyList(){
        DynamicArray myList = new DynamicArray();
        int expectedResult = -1;
        int result = myList.indexOf(5);

        assertEquals(expectedResult, result);
    }
}