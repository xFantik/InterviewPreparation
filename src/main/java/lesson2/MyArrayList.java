package lesson2;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private final static int DEFAULT_SIZE = 5;
    private Object[] array;
    private int size;

    public MyArrayList() {
        this.array = new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (array.length == size) {
            array = Arrays.copyOf(array, size * 2);
        }
        array[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0)
            return false;
        for (; index < size; index++) {
            if (index == size - 1) {
                array[index] = null;
                size--;
                return true;
            } else {
                array[index] = array[index + 1];
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = true;
        for (Object o : c) {
            if (!contains(o))
                result = false;
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (T t : c) {
            add(index, t);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        array = new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        array[index] = element;
        return (T) array[index];
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index - 1);
        if (array.length == size) {
            array = Arrays.copyOf(array, size * 2);
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T result = (T) array[index];
        for (int i = index; i < size; i++) {
            if (i == size - 1) {
                array[i] = null;
                size--;
            } else {
                array[i] = array[i + 1];
            }
        }
        return result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        //Такой вывод создан для диагностики
        return "MyArrayList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, size));

    }
}
