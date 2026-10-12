package b_lists.utils;

public class LinkedList {
    private Node first;
    private Node last;
    private int size;


    private static class Node{
        private String data;
        private Node next;

        public Node(String data){
            this.data = data;
            this.next = null;
        }
    }

    public void add(String element){
        Node newNode = new Node(element);

        if(isEmpty()){
            first = newNode;
            last = newNode;
        }else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }
    // public int size() - returns the size of the list (how many elements are currently stored)
    public int size(){
        return size;
    }
    // public boolean isEmpty() - returns if the list is empty
    public boolean isEmpty(){
        return first == null;
    }

    public String getFirst(){
        if(first == null){
            return null;
        }

        return first.data;
    }

    // public String get(int index) - returns the data at the specified position
    // If the supplied index is illegal (< 0 or >= size) then an IndexOutOfBoundsException should be thrown
    public String get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of array");
        }

        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }


    public void add(String element, int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of array");
        }

        Node newNode = new Node(element);
        if(index == 0){
            if(first != null){
                newNode.next = first;
            }

            first = newNode;
        }else {

            Node prev = null;
            Node current = first;

            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }

            newNode.next = current;
            prev.next = newNode;
        }

        size++;
    }

    // todo: remove(int index) removes the value at the specified index.
    //  This should RETURN the value originally at that index
}
