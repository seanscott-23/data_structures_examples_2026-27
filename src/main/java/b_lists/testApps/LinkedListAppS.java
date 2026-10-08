package b_lists.testApps;

import b_lists.utils.LinkedListS;

public class LinkedListAppS {
    public static void main(String[] args) {
        LinkedListS list = new LinkedListS();

        System.out.println("Initial list is empty: " + list.isEmpty());
        System.out.println("Initial list size: " + list.size());
        System.out.println("----------------------------------");

        System.out.println("Adding 'First'...");
        list.add("First");
        System.out.println("Size after 1 add: " + list.size());
        System.out.println("Element at index 0: " + list.get(0));
        System.out.println("----------------------------------");

        System.out.println("Adding 'Second', 'Third', and 'Fourth'...");
        list.add("Second");
        list.add("Third");
        list.add("Fourth");

        System.out.println("Size after adding more elements: " + list.size());
        System.out.println("List contents:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Index " + i + ": " + list.get(i));
        }

        System.out.println("----------------------------------");
        list.addAtIndex("New", 3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Index " + i + ": " + list.get(i));
        }

    }
}
