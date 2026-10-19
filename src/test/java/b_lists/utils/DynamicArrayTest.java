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
    void get_AccessBeyondEndOfList(){
        DynamicArray myList = new DynamicArray();
        myList.add(5);

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(2);
                }, "Incorrect (or no) exception thrown"
        );
    }

    @Test
    void get_AccessAfterEndOfList(){
        DynamicArray myList = new DynamicArray();
        myList.add(5);

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(1);
                }, "Incorrect (or no) exception thrown"
        );
    }

    @Test
    void indexOf_EmptyList(){
        DynamicArray myList = new DynamicArray();

        int expectedResult = -1;
        int target = 10;
        int result = myList.indexOf(target);

        assertEquals(expectedResult, result);
    }

    @Test
    void indexOf_NoMatchPresent(){
        DynamicArray myList = new DynamicArray();
        int value = 5;
        myList.add(value);

        int expectedResult = -1;
        int target = 10;
        int result = myList.indexOf(target);

        assertEquals(expectedResult, result);
    }

    @Test
    void indexOf_OneMatchPresent(){
        DynamicArray myList = new DynamicArray();
        int value = 5;
        myList.add(value);

        int expectedResult = 0;
        int result = myList.indexOf(value);

        assertEquals(expectedResult, result);
    }

    @Test
    void indexOf_MultipleMatchesPresent(){
        DynamicArray myList = new DynamicArray();
        int value = 5;
        myList.add(value);
        myList.add(1);
        myList.add(value);

        int expectedResult = 0;
        int result = myList.indexOf(value);

        assertEquals(expectedResult, result);
    }
}