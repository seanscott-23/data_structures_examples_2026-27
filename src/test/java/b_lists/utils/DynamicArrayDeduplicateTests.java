package b_lists.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class DynamicArrayDeduplicateTests {
    @Test
    void deduplicate_EmptyList(){
        DynamicArray myList = new DynamicArray();

        DynamicArray result = myList.deduplicate();

        // Check result size
        int expectedSize = 0;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list " +
                "(should be empty)");

        // Check original size
        int expectedOriginalSize = 0;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }

    @Test
    void deduplicate_NoDuplicates_SingleElement(){
        DynamicArray myList = new DynamicArray();
        String element = "1";
        
        myList.add(element);

        DynamicArray result = myList.deduplicate();

        // Check result size
        int expectedSize = 1;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size
        int expectedOriginalSize = 1;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check filtered contents
        assertEquals(element, result.get(0));

        // Check original contents
        assertEquals(element, myList.get(0));

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }

    @Test
    void deduplicate_NoDuplicates_MultipleElements(){
        DynamicArray myList = new DynamicArray();
        String [] dataset = {"1", "2", "3", "4"};
        String [] filteredDataset = {"1", "2", "3", "4"};

        for (String s : dataset) {
            myList.add(s);
        }

        DynamicArray result = myList.deduplicate();

        // Check result size
        int expectedSize = filteredDataset.length;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size
        int expectedOriginalSize = dataset.length;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check filtered contents
        for (int i = 0; i < result.size(); i++) {
            assertEquals(filteredDataset[i], result.get(i),
                    "Incorrect element found in returned list - expected "
                            + filteredDataset[i] + " at position " + i + ", " + "but" +
                            " found " + result.get(i));
        }

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i), "Original list contents have been edited - expected " + dataset[i] + " at position " + i + ", but" +
                    " found " + myList.get(i));
        }

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }

    @Test
    void deduplicate_AllDuplicates(){
        DynamicArray myList = new DynamicArray();
        String [] dataset = new String[5];
        String singleElement = "1";
        for (int i = 0; i < 5; i++) {
            dataset[i] = singleElement;
        }

        String [] filteredDataset = {singleElement};

        for (String s : dataset) {
            myList.add(s);
        }

        DynamicArray result = myList.deduplicate();

        // Check result size (Should only contain one element)
        int expectedSize = filteredDataset.length;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size (Should contain all elements from original)
        int expectedOriginalSize = dataset.length;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check filtered contents (Should only contain one element)
        for (int i = 0; i < result.size(); i++) {
            assertEquals(filteredDataset[i], result.get(i),
                    "Incorrect element found in returned list - expected "
                            + filteredDataset[i] + " at position " + i + ", " + "but" +
                            " found " + result.get(i));
        }

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),
                    "Original list contents have been edited - expected " + dataset[i] + " at position " + i + ", but" +
                            " found " + myList.get(i));
        }

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }

    @Test
    void deduplicate_OneDuplicate_Start(){
        DynamicArray myList = new DynamicArray();
        String [] dataset = {"1", "1", "2", "3", "4"};
        String [] filteredDataset = {"1", "2", "3", "4"};
        for (String s : dataset) {
            myList.add(s);
        }

        DynamicArray result = myList.deduplicate();

        // Check result size
        int expectedSize = filteredDataset.length;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size
        int expectedOriginalSize = dataset.length;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check filtered contents
        for (int i = 0; i < result.size(); i++) {
            assertEquals(filteredDataset[i], result.get(i),
                    "Incorrect element found in returned list - expected "
                            + filteredDataset[i] + " at position " + i + ", " + "but" +
                            " found " + result.get(i));
        }

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),"Original list contents have been edited - expected " + dataset[i] + " at position " + i + ", but" +
                    " found " + myList.get(i));
        }

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }

    @Test
    void deduplicate_OneDuplicate_Middle(){
        DynamicArray myList = new DynamicArray();
        String [] dataset = {"1", "2", "2", "3", "4"};
        String [] filteredDataset = {"1", "2", "3", "4"};
        for (String s : dataset) {
            myList.add(s);
        }

        DynamicArray result = myList.deduplicate();

        // Check result size
        int expectedSize = filteredDataset.length;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size
        int expectedOriginalSize = dataset.length;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check filtered contents
        for (int i = 0; i < result.size(); i++) {
            assertEquals(filteredDataset[i], result.get(i),
                    "Incorrect element found in returned list - expected "
                            + filteredDataset[i] + " at position " + i + ", " + "but" +
                            " found " + result.get(i));
        }

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),"Original list contents have been edited - expected " + dataset[i] + " at position " + i + ", but" +
                    " found " + myList.get(i));
        }

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }

    @Test
    void deduplicate_OneDuplicate_End(){
        DynamicArray myList = new DynamicArray();
        String [] dataset = {"1", "2", "3", "4", "3"};
        String [] filteredDataset = {"1", "2", "3", "4"};
        for (String s : dataset) {
            myList.add(s);
        }

        DynamicArray result = myList.deduplicate();

        // Check result size
        int expectedSize = filteredDataset.length;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size
        int expectedOriginalSize = dataset.length;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check filtered contents
        for (int i = 0; i < result.size(); i++) {
            assertEquals(filteredDataset[i], result.get(i),
                    "Incorrect element found in returned list - expected "
                            + filteredDataset[i] + " at position " + i + ", " + "but" +
                            " found " + result.get(i));
        }

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),"Original list contents have been edited - expected " + dataset[i] + " at position " + i + ", but" +
                    " found " + myList.get(i));
        }

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }


    @Test
    void deduplicate_OneDuplicate_ElementsNotSorted(){
        DynamicArray myList = new DynamicArray();
        String [] dataset = {"2", "1", "8", "-1", "3", "1"};
        String [] filteredDataset = {"2", "1", "8", "-1", "3"};
        for (String s : dataset) {
            myList.add(s);
        }

        DynamicArray result = myList.deduplicate();

        // Check result size
        int expectedSize = filteredDataset.length;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size
        int expectedOriginalSize = dataset.length;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check filtered contents
        for (int i = 0; i < result.size(); i++) {
            assertEquals(filteredDataset[i], result.get(i),
                    "Incorrect element found in returned list - expected "
                            + filteredDataset[i] + " at position " + i + ", " + "but" +
                            " found " + result.get(i));
        }

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),"Original list contents have been edited - expected " + dataset[i] + " at position " + i + ", but" +
                    " found " + myList.get(i));
        }

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }

    @Test
    void deduplicate_MultipleDuplicates_Runs(){
        DynamicArray myList = new DynamicArray();
        String [] dataset = {"1", "2", "2", "3", "3", "4"};
        String [] filteredDataset = {"1", "2", "3", "4"};
        for (String s : dataset) {
            myList.add(s);
        }

        DynamicArray result = myList.deduplicate();

        // Check result size - should match filtered data set
        int expectedSize = filteredDataset.length;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size - should match original data set
        int expectedOriginalSize = dataset.length;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check filtered contents
        for (int i = 0; i < result.size(); i++) {
            assertEquals(filteredDataset[i], result.get(i),
                    "Incorrect element found in returned list - expected "
                            + filteredDataset[i] + " at position " + i + ", " + "but" +
                            " found " + result.get(i));
        }

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),"Original list contents have been edited - expected " + dataset[i] + " at position " + i + ", but" +
                    " found " + myList.get(i));
        }

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }

    @Test
    void deduplicate_MultipleDuplicates_Scattered(){
        DynamicArray myList = new DynamicArray();
        String [] dataset = {"1", "2", "3", "1", "3", "4", "2"};
        String [] filteredDataset = {"1", "2", "3", "4"};
        for (String s : dataset) {
            myList.add(s);
        }

        DynamicArray result = myList.deduplicate();

        // Check result size - should match filtered data set
        int expectedSize = filteredDataset.length;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size - should match original data set
        int expectedOriginalSize = dataset.length;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        // Check filtered contents
        for (int i = 0; i < result.size(); i++) {
            assertEquals(filteredDataset[i], result.get(i),
                    "Incorrect element found in returned list - expected "
                            + filteredDataset[i] + " at position " + i + ", " + "but" +
                            " found " + result.get(i));
        }

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),"Original list contents have been edited - expected " + dataset[i] + " at position " + i + ", but" +
                    " found " + myList.get(i));
        }

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }

    @Test
    void deduplicate_EditResultList_OriginalUnchanged(){
        DynamicArray myList = new DynamicArray();
        String [] dataset = {"1", "2", "3", "1"};
        for (String s : dataset) {
            myList.add(s);
        }

        DynamicArray result = myList.deduplicate();

        // Check result size - should match filtered data set
        int expectedSize = dataset.length - 1;
        int resultSize = result.size();
        assertEquals(expectedSize, resultSize, "Size does not align with number of unique elements in original list");

        // Check original size - should match original data set
        int expectedOriginalSize = dataset.length;
        int resultOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, resultOriginalSize, "Original size was " +
                "inappropriately changed as a result of deduplication");

        String [] expandedDataset = {"1", "2", "3", "5"};
        result.add("5");

        // Check updated result size - should match expanded data set
        int expectedExpandedSize = expandedDataset.length;
        int resultExpandedSize = result.size();
        assertEquals(expectedExpandedSize, resultExpandedSize, "Size of result list after add is incorrect");

        // Check original size - should still match original data set
        int updatedOriginalSize = myList.size();
        assertEquals(expectedOriginalSize, updatedOriginalSize, "Original size was " +
                "inappropriately changed when adding to deduplicated list");

        // Check filtered contents
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expandedDataset[i], result.get(i),
                    "Incorrect element found in returned list after add - expected "
                            + expandedDataset[i] + " at position " + i + ", " + "but" +
                            " found " + result.get(i));
        }

        // Check original contents
        for (int i = 0; i < myList.size(); i++) {
            assertEquals(dataset[i], myList.get(i),"Original list contents have been edited - expected " + dataset[i] + " at position " + i + ", but" +
                    " found " + myList.get(i));
        }

        // Check two lists are separate
        assertNotSame(myList, result, "Original list and generated deduplicated copy are not independent objects.");
    }
}
