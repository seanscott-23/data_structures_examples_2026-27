package b_lists.testApps;

import b_lists.utils.LinkedList;

public class LinkedListTestBed {
    static void main(String[] args) {
        LinkedList myList = new LinkedList();

        for (int i = 0; i < 5; i++) {
            myList.add("Item " + (i+1));
        }

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
        System.out.println("******************************");

        // Add at a specific index:
        myList.add("NEW item 3", 2);

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
        System.out.println("******************************");

        myList.add("New FIRST element", 0);

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
        System.out.println("******************************");
    }
}
