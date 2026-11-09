package b_lists.utils;

import java.util.EmptyStackException;

public class ComposedStack {
    private LinkedList list = new LinkedList();

    public void push(String element){
        list.add(element, 0);
    }

    public String pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }

        return list.remove(0);
    }

    public String peek(){
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
