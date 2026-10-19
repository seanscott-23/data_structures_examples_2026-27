package b_lists.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntDynamicArrayComplexTests {
    // join tests
    @Test
    void join_NullJoiningList(){
        IntDynamicArray initialList = new IntDynamicArray();
        IntDynamicArray joinerList = null;

        assertThrows(IllegalArgumentException.class,
                () -> {
                    initialList.join(joinerList);
                }, "Incorrect (or no) exception thrown when joining a null list"
        );

        int expectedSize = 0;
        int resultSize = initialList.size();
        assertEquals(expectedSize, resultSize, "Expected size does not match actual size after joining " +
                "empty lists. Expected was " + expectedSize + ", actual= " + resultSize);
    }

    @Test
    void join_EmptyListToEmptyList(){
        IntDynamicArray initialList = new IntDynamicArray();
        IntDynamicArray joinerList = new IntDynamicArray();

        initialList.join(joinerList);
        int expectedSize = 0;
        int resultSize = initialList.size();
        assertEquals(expectedSize, resultSize, "Expected size does not match actual size after joining " +
                "empty lists. Expected was " + expectedSize + ", actual= " + resultSize);
    }

    @Test
    void join_EmptyListToPopulatedList(){
        IntDynamicArray initialList = new IntDynamicArray();
        // Populate list before test begins:
        int[] dataset = {1, 2, 3, 4};
        for (int i = 0; i < dataset.length; i++) {
            initialList.add(dataset[i]);
        }

        int startSize = dataset.length;

        IntDynamicArray joinerList = new IntDynamicArray();
        initialList.join(joinerList);

        // Check Size
        int expectedSize = startSize;
        int resultSize = initialList.size();
        assertEquals(expectedSize, resultSize, "Expected size does not match actual size after joining " +
                "empty list to populated list. Expected was " + expectedSize + ", actual= " + resultSize);

        // Check contents
        // Confirm all original values are still correct
        for (int i = 0; i < dataset.length; i++) {
            assertEquals(dataset[i], initialList.get(i), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + i + ")");
        }
    }

    @Test
    void join_PopulatedListToPopulatedList_InsufficientSpace(){
        IntDynamicArray initialList = new IntDynamicArray();
        // Populate list before test begins:
        int[] dataset = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < dataset.length; i++) {
            initialList.add(dataset[i]);
        }

        int startSize = dataset.length;

        IntDynamicArray joinerList = new IntDynamicArray();
        // Populate joiner list before test begins:
        int[] joinerDataset = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 12, 13, 14, 15};
        for (int i = 0; i < joinerDataset.length; i++) {
            joinerList.add(joinerDataset[i]);
        }

        int extraSize = joinerDataset.length;

        initialList.join(joinerList);

        // Check Size
        int expectedSize = startSize + extraSize;
        int resultSize = initialList.size();
        assertEquals(expectedSize, resultSize, "Expected size does not match actual size after joining " +
                "populated list to populated list. Expected was " + expectedSize + ", actual= " + resultSize);

        // Check contents
        // Confirm all original values are still correct
        for (int i = 0; i < dataset.length; i++) {
            assertEquals(dataset[i], initialList.get(i), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + i + ")");
        }

        // Check new contents
        // Confirm all new values are correct
        for (int i = dataset.length; i < expectedSize; i++) {
            assertEquals(joinerDataset[i-dataset.length], initialList.get(i), "Original element " +
                    "(" + joinerDataset[i-dataset.length] + ") was not found at expected position (" + i + ")");
        }

        // Confirm joiner list is not edited
        // Check size
        assertEquals(extraSize, joinerList.size(), "Joined list size has been changed. Expected: " + extraSize + ", " +
                "actual size: " + joinerList.size());
        // Check contents
        for (int i = 0; i < joinerDataset.length; i++) {
            assertEquals(joinerDataset[i], joinerList.get(i),
                    "Content of joined list has been changed. Expected: " + joinerDataset[i] + ", " +
                            "actual value found:" + joinerList.get(i));
        }
    }

    @Test
    void join_PopulatedListToPopulatedList_SufficientExistingSpace(){
        IntDynamicArray initialList = new IntDynamicArray();
        // Populate list before test begins:
        int[] dataset = {1, 2, 3, 4, 5};
        for (int i = 0; i < dataset.length; i++) {
            initialList.add(dataset[i]);
        }

        int startSize = dataset.length;

        IntDynamicArray joinerList = new IntDynamicArray();
        // Populate joiner list before test begins:
        int[] joinerDataset = {10, 20, 30, 40};
        for (int i = 0; i < joinerDataset.length; i++) {
            joinerList.add(joinerDataset[i]);
        }

        int extraSize = joinerDataset.length;

        initialList.join(joinerList);

        // Check Size
        int expectedSize = startSize + extraSize;
        int resultSize = initialList.size();
        assertEquals(expectedSize, resultSize, "Expected size does not match actual size after joining " +
                "populated list to populated list. Expected was " + expectedSize + ", actual= " + resultSize);

        // Check contents
        // Confirm all original values are still correct
        for (int i = 0; i < dataset.length; i++) {
            assertEquals(dataset[i], initialList.get(i), "Original element (" + dataset[i] + ") was not found" +
                    "at expected position (" + i + ")");
        }

        // Check new contents
        // Confirm all new values are correct
        for (int i = dataset.length; i < expectedSize; i++) {
            assertEquals(joinerDataset[i-dataset.length], initialList.get(i), "Original element " +
                    "(" + joinerDataset[i-dataset.length] + ") was not found at expected position (" + i + ")");
        }

        // Confirm joiner list is not edited
        // Check size
        assertEquals(extraSize, joinerList.size(), "Joined list size has been changed. Expected: " + extraSize + ", " +
                "actual size: " + joinerList.size());
        // Check contents
        for (int i = 0; i < joinerDataset.length; i++) {
            assertEquals(joinerDataset[i], joinerList.get(i),
                    "Content of joined list has been changed. Expected: " + joinerDataset[i] + ", " +
                            "actual value found:" + joinerList.get(i));
        }
    }

    @Test
    void join_PopulatedListToEmptyList(){
        IntDynamicArray initialList = new IntDynamicArray();
        int startSize = 0;

        IntDynamicArray joinerList = new IntDynamicArray();
        // Populate joiner list before test begins:
        int[] joinerDataset = {1, 2, 3, 4, 5};
        for (int i = 0; i < joinerDataset.length; i++) {
            joinerList.add(joinerDataset[i]);
        }

        int extraSize = joinerDataset.length;

        initialList.join(joinerList);

        // Check Size
        int expectedSize = startSize + extraSize;
        int resultSize = initialList.size();
        assertEquals(expectedSize, resultSize, "Expected size does not match actual size after joining " +
                "populated list to populated list. Expected was " + expectedSize + ", actual= " + resultSize);

        // Check new contents
        // Confirm all new values are correct
        for (int i = 0; i < expectedSize; i++) {
            assertEquals(joinerDataset[i], initialList.get(i), "Original element " +
                    "(" + joinerDataset[i] + ") was not found at expected position (" + i + ")");
        }

        // Confirm joiner list is not edited
        // Check size
        assertEquals(extraSize, joinerList.size(), "Joined list size has been changed. Expected: " + extraSize + ", " +
                "actual size: " + joinerList.size());
        // Check contents
        for (int i = 0; i < joinerDataset.length; i++) {
            assertEquals(joinerDataset[i], joinerList.get(i),
                    "Content of joined list has been changed. Expected: " + joinerDataset[i] + ", " +
                            "actual value found:" + joinerList.get(i));
        }
    }

    // Clear tests
    @Test
    void clear_EmptyList(){
        IntDynamicArray myList = new IntDynamicArray();
        int expectedSize = 0;

        myList.clear();

        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize);
    }

    @Test
    void clear_PopulatedList(){
        IntDynamicArray myList = new IntDynamicArray();
        int[] dataset = {1, 2, 3, 4, 5};
        for (int i = 0; i < dataset.length; i++) {
            myList.add(dataset[i]);
        }

        myList.clear();

        int expectedSize = 0;
        int resultSize = myList.size();
        assertEquals(expectedSize, resultSize);

        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    myList.get(0);
                }, "Accessing old elements is still possible"
        );
    }
}