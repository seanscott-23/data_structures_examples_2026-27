package b_lists.utils;

import java.util.NoSuchElementException;

public class Queue {
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

    public void enqueue(String element){
        validateForNull(element);

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

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public String peek(){
        if(first == null){
            throw new NoSuchElementException("Cannot peek at empty Queue");
        }

        return first.data;
    }

    public String dequeue(){
        if(first == null){
            throw new NoSuchElementException("Cannot remove from empty Queue");
        }

        String removed = first.data;
        first = first.next;
        if(first == null){
            last = null;
        }

        size--;
        return removed;
    }

    private static void validateForNull(String target) {
        if(target == null){
            throw new IllegalArgumentException("Nulls are not permitted in the Queue");
        }
    }
}
