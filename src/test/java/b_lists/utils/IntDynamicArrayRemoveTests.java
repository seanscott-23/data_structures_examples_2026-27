package b_lists.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntDynamicArrayRemoveTests {
    // Illegal actions
    // remove before list starts
    // remove from after list ends
    // remove from size of list
    @ParameterizedTest
    @ValueSource(ints = {-1, 5, 6})
    void remove_IllegalPosition(int index){
        IntDynamicArray myList = new IntDynamicArray();

        int[] dataset = {1, 2, 3, 4, 5};
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
        IntDynamicArray myList = new IntDynamicArray();

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
        IntDynamicArray myList = new IntDynamicArray();

        int[] dataset = {1, 2, 3, 4, 5};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }
        int startSize = dataset.length;

        int removedValue = myList.remove(index);
        int expectedValue = dataset[index];
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
        IntDynamicArray myList = new IntDynamicArray();

        List<Integer> values = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            values.add(i);
        }

        for (int value: values) {
            myList.add(value);
        }

        for (int numRemovals = 0; numRemovals < indices.length; numRemovals++) {
            int index = indices[numRemovals];

            int expectedValue = values.remove(index);
            int removedValue = myList.remove(index);

            assertEquals(expectedValue, removedValue, "Element removed from index (" + index + ") does not match expected " +
                    "value. Expected=" + expectedValue + ", actual=" + removedValue);

            // Confirm size changed
            int expectedSize = values.size();
            int result = myList.size();
            assertEquals(expectedSize, result, "Size did not decrease when it should have");

            // Confirm all original values are still correct
            for (int i = 0; i < expectedSize; i++) {

                assertEquals(values.get(i), myList.get(i), "Original element (" + values.get(i) + ") was not " +
                        "found" +
                        "at expected position (" + i + ")");
            }
        }
    }

    @Test
    void remove_OnlyElement(){
        IntDynamicArray myList = new IntDynamicArray();

        int value = 5;
        myList.add(value);
        int startSize = 1;

        int index = 0;
        int removedValue = myList.remove(index);
        int expectedValue = value;
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
}