package c_generics.utils;

import java.util.NoSuchElementException;

public class GenericQueue<T> {
    protected Node<T> first;
    protected Node<T> last;
    protected int size;


    protected static class Node<T>{
        protected T data;
        protected Node<T> next;

        public Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(T element){
        validateForNull(element);

        Node<T> newNode = new Node<>(element);

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

    public T peek(){
        if(first == null){
            throw new NoSuchElementException("Cannot peek at empty Queue");
        }

        return first.data;
    }

    public T dequeue(){
        if(first == null){
            throw new NoSuchElementException("Cannot remove from empty Queue");
        }

        T removed = first.data;
        first = first.next;
        if(first == null){
            last = null;
        }

        size--;
        return removed;
    }

    private void validateForNull(T target) {
        if(target == null){
            throw new IllegalArgumentException("Nulls are not permitted in the Queue");
        }
    }
}
