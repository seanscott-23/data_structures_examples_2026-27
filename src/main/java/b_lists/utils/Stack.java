package b_lists.utils;

import java.util.NoSuchElementException;

public class Stack {
    private Node first;
    private int size;

    private static class Node{
        private String data;
        private Node next;

        public Node(String data){
            this.data = data;
            this.next = null;
        }
    }

    public void push(String element){
        validateForNull(element);

        Node newNode = new Node(element);

        if(isEmpty()){
            first = newNode;
        }else {
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public String peek(){
        if(first == null){
            throw new NoSuchElementException("Cannot peek at empty Stack");
        }

        return first.data;
    }

    public String pop(){
        if(first == null){
            throw new NoSuchElementException("Cannot pop from empty Stack");
        }

        String removed = first.data;
        first = first.next;
        size--;
        return removed;
    }

    private static void validateForNull(String target) {
        if(target == null){
            throw new IllegalArgumentException("Nulls are not permitted in the Queue");
        }
    }
}
