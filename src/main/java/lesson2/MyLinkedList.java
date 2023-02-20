package lesson2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyLinkedList() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }


    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<T> n = first; n != null; n = n.next) {
            result[i++] = n.object;
        }
        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        a = (T1[]) new Object[size];
        int i = 0;
        for (Node<T> n = first; n != null; n = n.next) {
            a[i++] = (T1) n.object;
        }
        return a;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (indexOf(o) < 0) return false;
        }
        return true;
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
            add(index++, t);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = true;
        for (Object o : c) {
            result = result && remove(o);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Node<T> node = first; node != null; node = node.next) {
            if (!c.contains(node.object)){
                remove(node.object);
            }
        }
        return true;
    }

    @Override
    public boolean add(T o) {
        Node<T> tmp = new Node<T>(last, o, null);
        if (size == 0) {
            this.last = tmp;
            this.first = tmp;
        } else {
            this.last.next = tmp;
            this.last = tmp;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index == size) {
            add(element);
            return;
        }
        Node<T> tmp = getNode(index);
        Node<T> newNode = new Node<T>(tmp.previous, (T) element, tmp);
        if (index > 0)
            tmp.previous.next = newNode;
        else
            first = newNode;
        tmp.previous = newNode;
        size++;
    }


    @Override
    public void clear() {
        last = null;
        first = null;
        size = 0;
    }

    private Node<T> getNode(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        Node<T> tmp = first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    @Override
    public T get(int index) {
        return getNode(index).object;
    }

    @Override
    public T set(int index, T element) {
        getNode(index).object = element;
        return element;
    }


    @Override
    public T remove(int index) {
        Node<T> toRemove = getNode(index);
        if (index > 0)
            toRemove.previous.next = toRemove.next;
        else
            first = toRemove.next;
        if (index < size - 1)
            toRemove.next.previous = toRemove.previous;
        else last = toRemove.previous;
        size--;
        return toRemove.object;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0)
            return false;

        remove(index);
        return true;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Node<T> n = first; n != null; n = n.next) {
            if (n.object.equals(o))
                return index;

            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        for (Node<T> n = last; n != null; n = n.previous) {
            if (n.object.equals(o))
                return index;
            index--;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIter();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIter(getNode(index));
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }


    public T getFirst() {
        return first.object;
    }

    public T getLast() {
        return last.object;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString() + " (size=" + size + ") {");
        Iter iter = new Iter();
        while (iter.hasNext()) {
            sb.append(iter.next());
            if (iter.currentNode != null) {
                sb.append(", ");
            }
        }
        sb.append("}\n");

        return sb.toString();
    }

    private static class Node<T> {
        Node<T> previous;
        Node<T> next;
        T object;

        public Node(Node<T> previous, T object, Node<T> next) {
            this.previous = previous;
            this.next = next;
            this.object = object;
        }
    }

    private int findNode(Object o) {
        int i = -1;
        for (Node<T> node = first; node != null; node = node.next) {
            i++;
        }
        return i;

    }

    public class Iter implements Iterator<T> {
        Node<T> currentNode;

        public Iter() {
            currentNode = first;
        }

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public T next() {
            if (currentNode.next != null) {
                currentNode = currentNode.next;
                T result = currentNode.object;
                return result;
            }
            return null;
        }
    }

    public class ListIter implements ListIterator<T> {
        Node<T> currentNode;

        public ListIter() {
            currentNode = first;
        }

        public ListIter(Node<T> n) {
            currentNode = n;
        }


        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public T next() {
            if (currentNode.next != null) {
                currentNode = currentNode.next;
                T result = currentNode.object;
                return result;
            }
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return currentNode.previous != null;
        }

        @Override
        public T previous() {
            if (currentNode.previous != null) {
                currentNode = currentNode.previous;
                T result = currentNode.object;
                return result;
            }
            return null;
        }

        @Override
        public int nextIndex() {
            if (currentNode != last)
                return findNode(currentNode.object) + 1;
            else
                return findNode(currentNode.object);

        }

        @Override
        public int previousIndex() {
            if (currentNode != first)
                return findNode(currentNode.object) - 1;
            else
                return findNode(currentNode.object);
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(currentNode.object);
        }

        @Override
        public void set(T t) {
            currentNode.object = t;

        }

        @Override
        public void add(T t) {
            MyLinkedList.this.add(findNode(currentNode.object), t);
        }
    }
}
