package b_lists.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DynamicArrayRemoveTests {
    // Illegal actions
    // remove before list starts
    // remove from after list ends
    // remove from size of list
    @ParameterizedTest
    @ValueSource(ints = {-1, 5, 6})
    void remove_IllegalPosition(int index){
        DynamicArray myList = new DynamicArray();

        String[] dataset = {"1", "2", "3", "4", "5"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }
        int startSize = dataset.length;

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    // Remove element from illegal position
                    myList.remove(index);
                }, "Incorrect (or no) exception thrown when removing from illegal position: " + index
        );

        // Confirm size did NOT change
        int expectedResult = startSize;
        int result = myList.size();
        assertEquals(expectedResult, result, "Size changed when it should not have");

        // Confirm all original values are still correct
        for (int i = 0; i < dataset.length; i++) {
            assertEquals(dataset[i], myList.get(i), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + i + ")");
        }
    }

    @Test
    void remove_EmptyList(){
        DynamicArray myList = new DynamicArray();

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    // Remove element from illegal position
                    myList.remove(0);
                }, "Incorrect (or no) exception thrown when removing from empty list"
        );
    }

    // remove from first position
    // remove from last position
    // remove from middle
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4})
    void remove_LegalPosition(int index){
        DynamicArray myList = new DynamicArray();

        String[] dataset = {"1", "2", "3", "4", "5"};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }
        int startSize = dataset.length;

        String removedValue = myList.remove(index);
        String expectedValue = dataset[index];
        assertEquals(expectedValue, removedValue, "Element removed from index ("+ index+ ") does not match expected " +
                "value. Expected=" + expectedValue + ", actual=" + removedValue);


        // Confirm size changed
        int expectedSize = startSize-1;
        int result = myList.size();
        assertEquals(expectedSize, result, "Size did not decrease when it when it should have");

        // Confirm all original values are still correct
        int datasetIndex = 0;
        for (int i = 0; i < expectedSize; i++) {
            if(datasetIndex == index){
                datasetIndex++;
            }

            assertEquals(dataset[datasetIndex], myList.get(i), "Original element (" + dataset[datasetIndex] + ") was not found" +
                    "at expected position (" + i + ")");
            datasetIndex++;
        }
    }

    @Test
    void remove_RepeatedRemovalFromLegalPosition(){
        int [] indices = {1, 3, 4};
        DynamicArray myList = new DynamicArray();

        List<String> values = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            values.add(""+i);
        }

        for (String value: values) {
            myList.add(value);
        }

        for (int numRemovals = 0; numRemovals < indices.length; numRemovals++) {
            int index = indices[numRemovals];

            String expectedValue = values.remove(index);
            String removedValue = myList.remove(index);

            assertEquals(expectedValue, removedValue, "Element removed from index (" + index + ") does not match expected " +
                    "value. Expected=" + expectedValue + ", actual=" + removedValue);

            // Confirm size changed
            int expectedSize = values.size();
            int result = myList.size();
            assertEquals(expectedSize, result, "Size did not decrease when it should have");

            // Confirm all original values are still correct
            for (int i = 0; i < expectedSize; i++) {

                assertEquals(values.get(i), myList.get(i), "Original element (" + values.get(i) + ") was " +
                        "not found at expected position (" + i + ")");
            }
        }
    }

    @Test
    void remove_OnlyElement(){
        DynamicArray myList = new DynamicArray();

        String value = "5";
        myList.add(value);
        int startSize = 1;

        int index = 0;
        String removedValue = myList.remove(index);
        String expectedValue = value;
        assertEquals(expectedValue, removedValue, "Element removed from index ("+ index+ ") does not match expected " +
                "value. Expected=" + expectedValue + ", actual=" + removedValue);


        // Confirm size changed
        int expectedSize = startSize-1;
        int result = myList.size();
        assertEquals(expectedSize, result, "Size did not decrease when it when it should have");

        // Confirm original element cannot still be accessed
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(0);
                }, "Accessing removed element is still possible after removal"
        );

    }

    @Test
    void removeAll_EmptyList() {
        DynamicArray myList = new DynamicArray();

        String element = "1";
        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 0;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of elements removed from empty list");

        // Check result size
        int expectedSize = 0;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "Empty list size was inappropriately changed");
    }

    @Test
    void removeAll_NoMatches() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "2", "3"};
        String element = "4";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 0;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of elements removed when no matches exist");

        // Check result size
        int expectedSize = dataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "List size was inappropriately changed when no matches existed");

        // Check remaining contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),
                    "List contents were changed when no matches existed - expected "
                            + dataset[i] + " at position " + i
                            + ", but found " + myList.get(i));
        }
    }

    @Test
    void removeAll_SingleMatch() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "2", "3"};
        String[] expectedDataset = {"1", "3"};
        String element = "2";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 1;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of elements removed");

        // Check result size
        int expectedSize = expectedDataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "List size does not match the expected number of remaining elements");

        // Check remaining contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedDataset[i], myList.get(i),
                    "Incorrect remaining element - expected "
                            + expectedDataset[i] + " at position " + i
                            + ", but found " + myList.get(i));
        }

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_SingleElement_Match() {
        DynamicArray myList = new DynamicArray();
        String element = "1";
        myList.add(element);

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 1;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of elements removed from single-element list");

        // Check result size
        int expectedSize = 0;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "List should be empty after removing its only element");

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_SingleElement_NoMatch() {
        DynamicArray myList = new DynamicArray();
        myList.add("1");

        int removedCount = myList.removeAll("2");

        // Check number of elements removed
        int expectedRemovedCount = 0;
        assertEquals(expectedRemovedCount, removedCount,
                "An element was reported as removed when no match existed");

        // Check result size
        int expectedSize = 1;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "List size was inappropriately changed");

        // Check remaining contents
        assertEquals("1", myList.get(0),
                "Original element was inappropriately changed");
    }

    @Test
    void removeAll_AllElementsMatch() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "1", "1", "1"};
        String element = "1";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = dataset.length;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of elements removed when all elements match");

        // Check result size
        int expectedSize = 0;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "List should be empty when all elements match");

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_Duplicates_AtStart() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "1", "2", "3"};
        String[] expectedDataset = {"2", "3"};
        String element = "1";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 2;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of matching elements removed at start");

        // Check result size
        int expectedSize = expectedDataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "Incorrect list size after removing matches at start");

        // Check remaining contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedDataset[i], myList.get(i),
                    "Incorrect remaining element - expected "
                            + expectedDataset[i] + " at position " + i
                            + ", but found " + myList.get(i));
        }

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_Duplicates_InMiddle() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "2", "2", "3"};
        String[] expectedDataset = {"1", "3"};
        String element = "2";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 2;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of matching elements removed in middle");

        // Check result size
        int expectedSize = expectedDataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "Incorrect list size after removing matches in middle");

        // Check remaining contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedDataset[i], myList.get(i),
                    "Incorrect remaining element - expected "
                            + expectedDataset[i] + " at position " + i
                            + ", but found " + myList.get(i));
        }

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_Duplicates_AtEnd() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "2", "3", "3"};
        String[] expectedDataset = {"1", "2"};
        String element = "3";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 2;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of matching elements removed at end");

        // Check result size
        int expectedSize = expectedDataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "Incorrect list size after removing matches at end");

        // Check remaining contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedDataset[i], myList.get(i),
                    "Incorrect remaining element - expected "
                            + expectedDataset[i] + " at position " + i
                            + ", but found " + myList.get(i));
        }

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_Duplicates_Runs() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "2", "2", "2", "3"};
        String[] expectedDataset = {"1", "3"};
        String element = "2";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 3;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of elements removed from duplicate run");

        // Check result size
        int expectedSize = expectedDataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "Incorrect list size after removing duplicate run");

        // Check remaining contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedDataset[i], myList.get(i),
                    "Incorrect remaining element - expected "
                            + expectedDataset[i] + " at position " + i
                            + ", but found " + myList.get(i));
        }

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_Duplicates_Scattered() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "2", "1", "3", "1"};
        String[] expectedDataset = {"2", "3"};
        String element = "1";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 3;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of scattered matching elements removed");

        // Check result size
        int expectedSize = expectedDataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "Incorrect list size after removing scattered matches");

        // Check remaining contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedDataset[i], myList.get(i),
                    "Incorrect remaining element - expected "
                            + expectedDataset[i] + " at position " + i
                            + ", but found " + myList.get(i));
        }

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_Duplicates_StartEnd() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "2", "3", "1"};
        String[] expectedDataset = {"2", "3"};
        String element = "1";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 2;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of matching elements removed where matches appear at start and end");

        // Check result size
        int expectedSize = expectedDataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "Incorrect list size after removing matches at start and end");

        // Check remaining contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedDataset[i], myList.get(i),
                    "Incorrect remaining element - expected "
                            + expectedDataset[i] + " at position " + i
                            + ", but found " + myList.get(i));
        }

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_PreservesOrder() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"3", "1", "2", "1", "4"};
        String[] expectedDataset = {"3", "2", "4"};
        String element = "1";

        for (String s : dataset) {
            myList.add(s);
        }

        int removedCount = myList.removeAll(element);

        // Check number of elements removed
        int expectedRemovedCount = 2;
        assertEquals(expectedRemovedCount, removedCount,
                "Incorrect number of elements removed");

        // Check result size
        int expectedSize = expectedDataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "Incorrect list size after removal");

        // Check remaining contents and their order
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedDataset[i], myList.get(i),
                    "Remaining elements are incorrect or out of order - expected "
                            + expectedDataset[i] + " at position " + i
                            + ", but found " + myList.get(i));
        }

        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);
    }

    @Test
    void removeAll_RepeatedCalls() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "2", "1", "3"};

        for (String s : dataset) {
            myList.add(s);
        }

        // Remove first target
        String element = "1";
        int removedCountFirst = myList.removeAll(element);

        // Check first removal count
        int expectedRemovedCountFirst = 2;
        assertEquals(expectedRemovedCountFirst, removedCountFirst,
                "Incorrect number of elements removed on first call");

        // Check contents after first removal
        String[] expectedAfterFirst = {"2", "3"};
        assertEquals(expectedAfterFirst.length, myList.size(),
                "Incorrect size after first removal");

        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedAfterFirst[i], myList.get(i),
                    "Incorrect element after first removal at position " + i);
        }
        // Check no copies remain
        int expectedIndex = -1;
        int resultIndex = myList.indexOf(element);
        assertEquals(expectedIndex, resultIndex, "Copy of removed element remains at index " + resultIndex);

        // Remove second target
        String secondElement = "3";
        int removedCountSecond = myList.removeAll(secondElement);

        // Check second removal count
        int expectedRemovedCountSecond = 1;
        assertEquals(expectedRemovedCountSecond, removedCountSecond,
                "Incorrect number of elements removed on second call");

        // Check final contents
        String[] expectedAfterSecond = {"2"};
        assertEquals(expectedAfterSecond.length, myList.size(),
                "Incorrect size after second removal");

        for (int i = 0; i < myList.size(); i++) {
            assertEquals(expectedAfterSecond[i], myList.get(i),
                    "Incorrect element after second removal at position " + i);
        }
        // Check no copies remain
        int expectedSecondIndex = -1;
        resultIndex = myList.indexOf(secondElement);
        assertEquals(expectedSecondIndex, resultIndex);
    }

    @Test
    void removeAll_NullArgument() {
        DynamicArray myList = new DynamicArray();
        String[] dataset = {"1", "2", "1"};

        for (String s : dataset) {
            myList.add(s);
        }

        // Check null argument is rejected
        assertThrows(IllegalArgumentException.class,
                () -> myList.removeAll(null),
                "Expected IllegalArgumentException when argument is null");

        // Check original size
        int expectedSize = dataset.length;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize,
                "List size was changed after rejecting null argument");

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),
                    "List contents were changed after rejecting null argument");
        }
    }
}