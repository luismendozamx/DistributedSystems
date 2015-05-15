package util;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int size;
    private int index;

    public ArrayIterator(T[] array, int size){
        this.size = size;
        this.array = array;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index<size;
    }

    @Override
    public T next() {
        T current;
        if(index<size){
            current = array[index];
            index++;
        }else{
            current = null;
        }
        return current;
    }

    @Override
    public void remove() {
        System.out.println("Remove is not yet implemented.");
    }
}
