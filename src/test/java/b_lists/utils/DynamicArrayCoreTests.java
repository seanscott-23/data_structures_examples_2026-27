package b_lists.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests size(), get() and add() logic
 */
public class DynamicArrayCoreTests {
    @Test
    void size_PopulatedList() {
        DynamicArray myList = new DynamicArray();
        myList.add("Five");
        int expectedResult = 1;
        int result = myList.size();

        assertEquals(expectedResult, result);
    }

    @Test
    void size_EmptyList() {
        DynamicArray myList = new DynamicArray();
        int expectedResult = 0;
        int result = myList.size();

        assertEquals(expectedResult, result);
    }

    @Test
    void size_PopulatedList_MultipleElements() {
        DynamicArray myList = new DynamicArray();
        myList.add("Five");
        myList.add("Ten");
        myList.add("Three");

        int expectedResult = 3;
        int result = myList.size();

        assertEquals(expectedResult, result);
    }

    @Test
    void get_PopulatedList() {
        DynamicArray myList = new DynamicArray();
        myList.add("Five");
        String expectedResult = "Five";
        String result = myList.get(0);

        assertEquals(expectedResult, result);
    }

    @Test
    void get_AccessBeforeList() {
        DynamicArray myList = new DynamicArray();
        myList.add("Five");

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(-1);
                }, "Incorrect (or no) exception thrown"
        );
    }

    @Test
    void get_AccessBeyondEndOfList() {
        DynamicArray myList = new DynamicArray();
        myList.add("Five");

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(2);
                }, "Incorrect (or no) exception thrown"
        );
    }

    @Test
    void get_AccessAfterEndOfList() {
        DynamicArray myList = new DynamicArray();
        myList.add("Five");

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(1);
                }, "Incorrect (or no) exception thrown"
        );
    }

    // Add tests
    @Test
    void add_EmptyList() {
        DynamicArray myList = new DynamicArray();
        String value = "Five";
        myList.add(value);

        // Confirm size changed
        int expectedResult = 1;
        int result = myList.size();
        assertEquals(expectedResult, result);

        // Confirm contents updated correctly
        String expectedValue = "Five";
        String resultValue = myList.get(0);
        assertEquals(expectedValue, resultValue);
    }

    @Test
    void add_PopulatedList() {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String[] dataset = {"1", "2", "3", "4"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        // Add element to end of populated list
        String value = "5";
        myList.add(value);

        // Confirm size changed
        int expectedResult = startSize + 1;
        int result = myList.size();
        assertEquals(expectedResult, result);

        // Confirm contents updated correctly
        String expectedValue = value;
        // Retrieve from end position
        String valueResult = myList.get(myList.size() - 1);
        assertEquals(expectedValue, valueResult);

        // Confirm all other original values are still correct
        for (int i = 0; i < dataset.length; i++) {
            assertEquals(dataset[i], myList.get(i));
        }
    }

    @Test
    void add_PopulatedList_RequiresExpansion() {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String [] dataset = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        // Add element to end of populated list
        String value = "5";
        myList.add(value);

        // Confirm size changed
        int expectedResult = startSize + 1;
        int result = myList.size();
        assertEquals(expectedResult, result);

        // Confirm contents updated correctly
        String expectedValue = value;
        // Retrieve from end position
        String valueResult = myList.get(myList.size() - 1);
        assertEquals(expectedValue, valueResult);

        // Confirm all other original values are still correct
        for (int i = 0; i < dataset.length; i++) {
            assertEquals(dataset[i], myList.get(i));

        }
    }
}
