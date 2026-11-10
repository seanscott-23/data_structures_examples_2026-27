package c_generics.utils;

public class GenericDynamicArray<T> {
    private static final int EXPANSION_MULTIPLIER = 2;
    private int size = 0;
    private Object[] data = new Object[10];

    private void ensureCapacity(){
        if(size == data.length){
            Object[] temp = new Object[data.length*EXPANSION_MULTIPLIER];

            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }

            data = temp;
        }
    }

    public void add(T value){
        ensureCapacity();

        data[size] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of array");
        }
        return (T)data[index];
    }

    public int size(){
        return size;
    }

    public int indexOf(T target){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(target)){
                return i;
            }
        }

        return -1;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index){
        validateIndex(index);

        T removed = (T) data[index];

        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }

        data[size-1] = null;
        size--;

        return removed;
    }

    private void validateIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of list");
        }
    }

    private void validateInclusiveIndex(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of list");
        }
    }

    public void add(int index, T value){
        validateInclusiveIndex(index);

        ensureCapacity();

//        for (int i = size; i > index; i--) {
//            data[i] = data[i-1];
//        }

        System.arraycopy(data, index, data, index+1, (size-index));

        data[index] = value;
        size++;
    }

    public int lastIndexOf(T target){
        for (int i = size-1; i >= 0; i--) {
            if(data[i].equals(target)){
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T element){
        validateIndex(index);

        T removed = (T) data[index];
        data[index] = element;

        return removed;
    }

    public void join(GenericDynamicArray<T> toBeJoined){
        if(toBeJoined == null){
            throw new IllegalArgumentException("Cannot join a null list");
        }

        if((data.length - size) < toBeJoined.size){
            Object[] temp = new Object[size + toBeJoined.size];

            System.arraycopy(data, 0, temp, 0, size);

            data = temp;
        }

        System.arraycopy(toBeJoined.data, 0, data, size, toBeJoined.size);
        size += toBeJoined.size;
    }

    public void clear(){
        data = new Object[10];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public GenericDynamicArray<T> subset(int startIndex, int endIndex){
        validateIndex(startIndex);

        if(endIndex < 0 | endIndex > size){
            throw new IndexOutOfBoundsException("Index " + endIndex + " is outside bounds of list");
        }

        if(startIndex >= endIndex){
            throw new IllegalArgumentException("Start index of subset (" + startIndex + ") must be less than or equal" +
                    " to end index (" + endIndex + ")");
        }

        GenericDynamicArray<T> subset = new GenericDynamicArray<>();
        for (int i = startIndex; i < endIndex; i++) {
            subset.add((T) data[i]);
        }

        return subset;
    }

    @SuppressWarnings("unchecked")
    public GenericDynamicArray<T> altSubset(int startIndex, int endIndex){
        validateIndex(startIndex);

        if(endIndex < 0 || endIndex > size){
            throw new IndexOutOfBoundsException("Index " + endIndex + " is outside bounds of list");
        }

        if(startIndex > endIndex){
            throw new IllegalArgumentException("Start index of subset (" + startIndex + ") must be less than or equal" +
                    " to end index (" + endIndex + ")");
        }

        GenericDynamicArray<T> subset = new GenericDynamicArray<>();

        int subsetSize = endIndex - startIndex;
        if(subset.data.length < subsetSize){
            subset.data = new Object[subsetSize+10];
        }

        System.arraycopy(data, startIndex, subset.data, 0, subsetSize);
        subset.size = subsetSize;

        return subset;
    }

    @SuppressWarnings("unchecked")
    public GenericDynamicArray<T> deduplicate() {
        GenericDynamicArray<T> result = new GenericDynamicArray<>();

        for (int i = 0; i < size; i++) {
            T element = (T) data[i];

            if (result.indexOf(element) == -1) {
                result.add(element);
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public int removeAll(T element) {
        if(element == null){
            throw new IllegalArgumentException("Null elements are not permitted");
        }

        int read = 0;
        int write = 0;

        // While we haven't read through all elements originally in list
        while (read < size) {
            T current = (T) data[read];

            // If this does not match the element being removed,
            // save it and move where we will write to next up a space
            if (!element.equals(current)) {
                data[write] = current;
                write++;
            }

            // Move to next element in list to check if it should be removed
            read++;
        }

        // Calculate how many elements were removed
        // (original size - number of retained elements)
        int removedCount = size - write;

        // Overwrite "ghost" elements with blanks (null for objects)
        // Use while loop because we're decreasing size as we move through
        while (size > write) {
            // Overwrite last position
            data[size-1] = null;
            // Reduce official size by 1 (size is moving closer to write position)
            size--;
        }

        return removedCount;
    }
}
