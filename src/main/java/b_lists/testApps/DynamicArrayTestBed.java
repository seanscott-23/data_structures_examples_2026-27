package b_lists.testApps;

import b_lists.utils.DynamicArray;

import java.util.Random;

public class DynamicArrayTestBed {
    static void main(String[] args) {
        DynamicArray myList = new DynamicArray();
        Random rg = new Random();

        for (int i = 0; i < 10; i++) {
            myList.add(rg.nextInt(100));
        }

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
//
//        int removed = myList.remove(3);
//        System.out.println("Value removed: " + removed);
//
//        for (int i = 0; i < myList.size(); i++) {
//            System.out.println(myList.get(i));
//        }

        System.out.println("----------------------------------");
        System.out.println("Inserting " + 50 + " at position "+ 2);
        myList.add(2, 50);

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }

        System.out.println("----------------------------------");
        System.out.println("Last index of " + 50 + ": " + myList.lastIndexOf(50));

    }

}
