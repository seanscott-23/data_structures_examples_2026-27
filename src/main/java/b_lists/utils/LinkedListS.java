package b_lists.utils;

public class LinkedListS {
    private static class Node {
        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node first;
    private int size;

    public void add(String element) {
        if (size == 0) {
            Node newNode = new Node(element);
            first = newNode;
        } else {
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            Node newNode = new Node(element);
            current.next = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void addAtIndex(String data, int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node newNode = new Node(data);

        if(index == 0){
            if(first != null){
                newNode.next = first;
            }
            first = newNode;

        } else {
            Node prev = null;
            Node current = first;
            for(int i = 0; i < index; i++){
                prev = current;
                current = current.next;
            }
            newNode.next = current;
            prev.next = newNode;
        }
        size++;
    }

    public String remove(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        String data = null;
        if(index == 0){
            if(first != null){
                data = first.data;
                first = first.next;
                size --;
                return data;
            }

        } else {
            Node prev = null;
            Node current = first;
            for(int i = 0; i < index; i++){
                prev = current;
                current = current.next;
            }
            data = current.data;
            prev.next = current.next;
        }
        size--;
        return data;
    }

    public String remove(String search){
        if(search == null)
            return null;

        String removedData = null;

        Node prev = null;
        Node current = first;
        if(current == null)
            return null;

        if(current.data.equals(search)){
            removedData = current.data;
            first = current.next;
            size--;
            return removedData;
        }

        for(int i = 0; i < size; i++){
            if(current.data.equals(search)) {
                removedData = current.data;
                prev.next = current.next;
                size --;
            }
            prev = current;
            if(current.next == null)
                break;
            current = current.next;
        }
        return removedData;
    }
}

