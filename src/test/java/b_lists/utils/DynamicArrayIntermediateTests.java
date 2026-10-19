package b_lists.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DynamicArrayIntermediateTests {
    @Test
    void indexOf_EmptyList() {
        DynamicArray myList = new DynamicArray();

        int expectedResult = -1;
        String target = "Ten";
        int result = myList.indexOf(target);

        assertEquals(expectedResult, result);
    }

    @Test
    void indexOf_NoMatchPresent() {
        DynamicArray myList = new DynamicArray();
        String value = "5";
        myList.add(value);

        int expectedResult = -1;
        String target = "10";
        int result = myList.indexOf(target);

        assertEquals(expectedResult, result);
    }

    @Test
    void indexOf_OneMatchPresent() {
        DynamicArray myList = new DynamicArray();
        String value = "5";
        myList.add(value);

        int expectedResult = 0;
        int result = myList.indexOf(value);

        assertEquals(expectedResult, result);
    }

    @Test
    void indexOf_OneMatchPresent_MiddleOfMultipleElementsInList() {
        DynamicArray myList = new DynamicArray();
        myList.add("10");
        myList.add("20");
        String value = "5";
        myList.add(value);
        myList.add("30");

        int expectedResult = 2;
        int result = myList.indexOf(value);

        assertEquals(expectedResult, result);
    }

    @Test
    void indexOf_OneMatchPresent_EndOfMultipleElementsInList() {
        DynamicArray myList = new DynamicArray();
        myList.add("10");
        myList.add("20");
        myList.add("30");
        String value = "5";
        myList.add(value);

        int expectedResult = 3;
        int result = myList.indexOf(value);

        assertEquals(expectedResult, result);
    }

    @Test
    void indexOf_MultipleMatchesPresent() {
        DynamicArray myList = new DynamicArray();
        String value = "5";
        myList.add(value);
        myList.add("1");
        myList.add(value);

        int expectedResult = 0;
        int result = myList.indexOf(value);

        assertEquals(expectedResult, result);
    }

    // Add at position
    @Test
    void addAtPosition_EmptyList() {
        DynamicArray myList = new DynamicArray();
        int index = 0;
        String value = "5";

        // Add at start of empty list
        myList.add(index, value);

        // Confirm size changed
        int expectedResult = 1;
        int result = myList.size();
        assertEquals(expectedResult, result);

        // Confirm contents updated correctly
        String expectedValue = value;
        String valueResult = myList.get(0);
        assertEquals(expectedValue, valueResult);
    }

    @Test
    void addAtIndex_PopulatedList() {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String [] dataset = {"1", "2", "3", "4"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        // Add element to middle of populated list
        int index = 2;
        String value = "5";
        myList.add(index, value);

        // Confirm size changed
        int expectedResult = startSize + 1;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size did not increase as expected - was " + result + ", but" +
                "expected was " + expectedResult);

        // Confirm contents updated correctly
        String expectedValue = value;
        // Retrieve from insertion position
        String valueResult = myList.get(index);
        assertEquals(expectedValue, valueResult, "Inserted value ("+ expectedValue + ") was not found " +
                        "at expected position (" + index + ")");

        // Confirm all other original values are still correct
        // i tracks where we are in the original data
        // listIndex tracks where we are in the list information
        int listIndex = 0;
        for (int i = 0; i < dataset.length; i++) {
            if(i == index){
                listIndex++;
            }
            assertEquals(dataset[i], myList.get(listIndex), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + listIndex + ")");
            listIndex++;
        }
    }

    @Test
    void addAtIndex_StartOfPopulatedList() {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String [] dataset = {"1", "2", "3", "4"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        // Add element to start of populated list
        int index = 0;
        String value = "5";
        myList.add(index, value);

        // Confirm size changed
        int expectedResult = startSize + 1;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size did not increase as expected - was " + result + ", but" +
                "expected was " + expectedResult);

        // Confirm contents updated correctly
        String expectedValue = value;
        // Retrieve from insertion position
        String valueResult = myList.get(index);
        assertEquals(expectedValue, valueResult, "Inserted value ("+ expectedValue + ") was not found " +
                "at expected position (" + index + ")");

        // Confirm all other original values are still correct
        // i tracks where we are in the original data
        // listIndex tracks where we are in the list information
        int listIndex = 0;
        for (int i = 0; i < dataset.length; i++) {
            if(i == index){
                listIndex++;
            }
            assertEquals(dataset[i], myList.get(listIndex), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + listIndex + ")");
            listIndex++;
        }
    }

    @Test
    void addAtIndex_IndexEqualsSizeMinusOne() {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String [] dataset = {"1", "2", "3", "4"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        // Add element immediately before last index of populated list
        int index = myList.size()-1;
        String value = "5";
        myList.add(index, value);

        // Confirm size changed
        int expectedResult = startSize + 1;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size did not increase as expected - was " + result + ", but" +
                "expected was " + expectedResult);

        // Confirm contents updated correctly
        String expectedValue = value;
        // Retrieve from insertion position
        String valueResult = myList.get(index);
        assertEquals(expectedValue, valueResult, "Inserted value ("+ expectedValue + ") was not found " +
                "at expected position (" + index + ")");

        // Confirm all other original values are still correct
        // i tracks where we are in the original data
        // listIndex tracks where we are in the list information
        int listIndex = 0;
        for (int i = 0; i < dataset.length; i++) {
            if(i == index){
                listIndex++;
            }
            assertEquals(dataset[i], myList.get(listIndex), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + listIndex + ")");
            listIndex++;
        }
    }

    @Test
    void addAtIndex_IndexEqualsSize() {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String [] dataset = {"1", "2", "3", "4"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        // Add element to end of populated list
        int index = myList.size();
        String value = "5";
        myList.add(index, value);

        // Confirm size changed
        int expectedResult = startSize + 1;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size did not increase as expected - was " + result + ", but" +
                "expected was " + expectedResult);

        // Confirm contents updated correctly
        String expectedValue = value;
        // Retrieve from insertion position
        String resultValue = myList.get(index);
        assertEquals(expectedValue, resultValue, "Inserted value ("+ expectedValue + ") was not found " +
                "at expected position (" + index + ")");

        // Confirm all other original values are still correct
        // i tracks where we are in the original data
        // listIndex tracks where we are in the list information
        int listIndex = 0;
        for (int i = 0; i < dataset.length; i++) {
            if(i == index){
                listIndex++;
            }
            assertEquals(dataset[i], myList.get(listIndex), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + listIndex + ")");
            listIndex++;
        }
    }

    // Adding before list begins
    @Test
    void addAtIndex_AddBeforeList() {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String [] dataset = {"1", "2", "3", "4"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    // Add element before start of populated list
                    int index = -1;
                    String value = "5";
                    myList.add(index, value);
                }, "Incorrect (or no) exception thrown when adding before start of list"
        );

        // Confirm size did NOT change
        int expectedResult = startSize;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size increased when it should not have");

        // Confirm all original values are still correct
        for (int i = 0; i < dataset.length; i++) {
            assertEquals(dataset[i], myList.get(i), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + i + ")");
        }
    }

    // Adding beyond list end
    @Test
    void addAtIndex_AddIndexGreaterThanSize() {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String [] dataset = {"1", "2", "3", "4"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    // Add element beyond end  of populated list
                    int index = startSize + 2;
                    String value = "5";
                    myList.add(index, value);
                }, "Incorrect (or no) exception thrown when adding beyond end of list"
        );

        // Confirm size did NOT change
        int expectedResult = startSize;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size increased when it should not have");

        // Confirm all original values are still correct
        for (int i = 0; i < dataset.length; i++) {
            assertEquals(dataset[i], myList.get(i), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + i + ")");
        }
    }

    // Set tests
    // Set at position - empty
    @Test
    void set_EmptyList() {
        DynamicArray myList = new DynamicArray();

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    // Set element within empty list
                    int index = 0;
                    String value = "5";
                    myList.set(index, value);
                }, "Incorrect (or no) exception thrown when setting in empty list"
        );

        // Confirm size did NOT change
        int expectedResult = 0;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size increased when it should not have");
    }

    // Set at position - populated
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 3})
    void set_StartMiddleEndOfPopulatedList(int index) {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String [] dataset = {"1", "2", "3", "4"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        // Set element in populated list
        String value = "5";
        String removedResult = myList.set(index, value);
        String expectedRemoved = dataset[index];
        assertEquals(expectedRemoved, removedResult,
                "Incorrect value returned from set action - was " + removedResult +
                        ", but expected was " + expectedRemoved);


        // Confirm size did NOT change
        int expectedResult = startSize;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size increased when it shouldn't - was " + result + ", but" +
                "expected was " + expectedResult);

        // Confirm contents updated correctly
        String expectedValue = value;
        // Retrieve from set position
        String resultValue = myList.get(index);
        assertEquals(expectedValue, resultValue, "Set value ("+ expectedValue + ") was not found " +
                "at expected position (" + index + ")");

        // Confirm all other original values are still correct
        // Where we're at the index, skip
        for (int i = 0; i < dataset.length; i++) {
            if(i == index){
                continue;
            }
            assertEquals(dataset[i], myList.get(i), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + i + ")");
        }
    }

    // Setting before list begins
    @ParameterizedTest
    @ValueSource(ints = {-1, 4, 5})
    void set_SetNegative_EqualIndex_AfterEnd(int index) {
        DynamicArray myList = new DynamicArray();
        // Populate list before test begins:
        String [] dataset = {"1", "2", "3", "4"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        int startSize = dataset.length;

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    // Set element in populated list
                    String value = "5";
                    myList.set(index, value);
                }, "Expected IndexOutOfBoundsException when setting at index: " + index
        );

        // Confirm size did NOT change
        int expectedResult = startSize;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size increased when it should not have");

        // Confirm all original values are still correct
        for (int i = 0; i < dataset.length; i++) {
            assertEquals(dataset[i], myList.get(i), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + i + ")");
        }
    }
}