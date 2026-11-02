package c_generics.apps;

import c_generics.utils.GenericLinkedList;

import java.util.Random;

public class TestGenericLinkedList {
    static void main(String[] args) {
        Random randomGenerator = new Random();
        GenericLinkedList<Integer> numbers = new GenericLinkedList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(randomGenerator.nextInt(100));
        }

        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }

        GenericLinkedList<String> text = new GenericLinkedList<>();
        for (int i = 0; i < 10; i++) {
            text.add(randomGenerator.nextInt(100) + " - text!");
        }

        for (int i = 0; i < text.size(); i++) {
            System.out.println(text.get(i));
        }
    }
}
