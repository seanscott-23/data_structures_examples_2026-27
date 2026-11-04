package b_lists.utils;

public class ListSet extends LinkedList{
    public void add(String element){
        if(element == null){
            throw new IllegalArgumentException("Cannot add a null element");
        }

        if(contains(element)){
            throw new DuplicateElementException("Cannot add a duplicate to set");
        }

        super.add(element);
    }

    public boolean contains(String element){
        if(element == null){
            throw new IllegalArgumentException("Cannot add a null element");
        }

        return indexOf(element) != -1;
    }
}
