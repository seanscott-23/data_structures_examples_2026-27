package c_generics.utils;


import java.util.EmptyStackException;

public class GenericComposedStack<E> {
    private GenericLinkedList<E> list = new GenericLinkedList<>();

    public void push(E element){
        list.add(element, 0);
    }

    public E pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }

        return list.remove(0);
    }

    public E peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }

        return list.get(0);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }
}
