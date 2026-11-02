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
        validateIndexInBoundsExclusive(index);

        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    private void validateIndexInBoundsExclusive(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of array");
        }
    }


    public void add(String element, int index){
        validateInBoundsInclusive(index);

        Node newNode = new Node(element);
        if(index == 0){
            if(!isEmpty()){
                newNode.next = first;
            }else{
                last = newNode;
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

            if(index == size){
                last = newNode;
            }

            // We can also detect that we're adding at the end of the list by checking whether current is null:
            // if(current == null){
            //      last = newNode;
            // }
        }

        size++;
    }

    public int indexOf(String target){
        validateForNull(target);

        Node current = first;
        for (int i = 0; i < size; i++) {
            if(current.data.equalsIgnoreCase(target)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public boolean removeElement(String target){
        int index = indexOf(target);
        if(index == -1){
            return false;
        }

        remove(index);
        return true;
    }

    public boolean remove(String target){
        validateForNull(target);

        if(isEmpty()){
            return false;
        }

        if(target.equals(first.data)){
            first = first.next;

            if(first == null){
                last = null;
            }
        }else{
            // Handle where edit is in remainder of list
            Node current = first;
            Node prev = null;

            boolean found = false;
            for (int i = 0; i < size; i++) {
                if(target.equals(current.data)){
                    found = true;
                    break;
                }else{
                    prev = current;
                    current = current.next;
                }
            }

            if(!found){
                return false;
            }

            // Delete element from list
            prev.next = current.next;
            // If element to be deleted is at end of list, update last to the one before current
            // (i.e. the one before the deleted element)
            if(current == last) {
                last = prev;
            }
        }

        // Final clean up - reduce size and return removed element
        size--;
        return true;
    }

    private static void validateForNull(String target) {
        if(target == null){
            throw new IllegalArgumentException("Cannot search for null");
        }
    }

    private void validateInBoundsInclusive(int index) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of list");
        }
    }

    // todo: remove(int index) removes the value at the specified index.
    //  This should RETURN the value originally at that index
    public String remove(int index){
        validateIndexInBoundsExclusive(index);

        String removed;
        // Handle where edit is at start of list
        if(index == 0){
            removed = first.data;
            first = first.next;

            if(first == null){
                last = null;
            }
        }else{
            // Handle where edit is in remainder of list
            Node current = first;
            Node prev = null;

            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }

            removed = current.data;
            // Delete element from list
            prev.next = current.next;
            // If deleted index was end of list, update last to the one before current
            // (i.e. the one before the deleted element)
            if(index == size-1){
                last = prev;
            }

            // Could also detect that we've deleted the last element by checking if current is last
            // if(current == last){
            //    last = prev;
            // }
        }

        // Final clean up - reduce size and return removed element
        size--;
        return removed;
    }
}
