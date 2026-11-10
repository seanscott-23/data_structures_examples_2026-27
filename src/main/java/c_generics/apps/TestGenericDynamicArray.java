package c_generics.apps;

import c_generics.utils.GenericDynamicArray;

public class TestGenericDynamicArray {
    static void main(String[] args) {
        GenericDynamicArray<String> myStringList = new GenericDynamicArray<>();
        GenericDynamicArray<Integer> myIntList = new GenericDynamicArray<>();
        GenericDynamicArray<Double> myDoubleList = new GenericDynamicArray<>();

        for (int i = 0; i < 10; i++) {
            myStringList.add("Element " + i);
            myIntList.add(i);
            myDoubleList.add(i * 1.0);
        }

        for (int i = 0; i < myStringList.size(); i++) {
            System.out.println("Element " + i + ":");
            System.out.println("\tString -> " + myStringList.get(i));
            System.out.println("\tint -> " + myIntList.get(i));
            System.out.println("\tdouble -> " + myDoubleList.get(i));
        }
    }
}
