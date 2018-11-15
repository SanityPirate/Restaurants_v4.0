// DoublyLinkedList class from https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<RestaurantEntry> implements Iterable<RestaurantEntry> {
    private int n;
    private Node head;
    private Node tail;


    public DoublyLinkedList() {
        tail = new Node();
        head = new Node();
        tail.next = head;
        head.prev = tail;
    }

    private class Node {
        private RestaurantEntry restaurant;
        private Node next;
        private Node prev;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void add(RestaurantEntry restaurant) {
        Node last = head.prev;
        Node x = new Node();
        x.restaurant = restaurant;
        x.next = head;
        x.prev = last;
        head.prev = x;
        last.next = x;
        n++;
    }

    public ListIterator<RestaurantEntry> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements ListIterator<RestaurantEntry> {
        private Node current = tail.next;
        private Node lastAccessed = null;

        private int index = 0;

        public boolean hasNext() {
            return index < n;
        }

        public boolean hasPrevious() {
            return index > 0;
        }

        public int previousIndex() {
            return index - 1;
        }

        public int nextIndex() {
            return index;
        }

        public RestaurantEntry next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastAccessed = current;
            RestaurantEntry restaurant = current.restaurant;
            current = current.next;
            index++;
            return restaurant;
        }

        public RestaurantEntry previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = current.prev;
            index--;
            lastAccessed = current;
            return current.restaurant;
        }

        public void set(RestaurantEntry restaurant) {
            if (lastAccessed == null) {
                throw new IllegalStateException();
            }
            lastAccessed.restaurant = restaurant;
        }

        public void remove() {
            if (lastAccessed == null) {
                throw new IllegalStateException();
            }
            Node x = lastAccessed.prev;
            Node y = lastAccessed.next;
            x.next = y;
            y.prev = x;
            n--;
            if (current == lastAccessed) {
                current = y;
            } else {
                index--;
            }
            lastAccessed = null;
        }

        public void add(RestaurantEntry restaurant) {
            Node x = current.prev;
            Node y = new Node();
            Node z = current;
            y.restaurant = restaurant;
            x.next = y;
            y.next = z;
            z.prev = y;
            y.prev = x;
            n++;
            index++;
            lastAccessed = null;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (RestaurantEntry restaurant : this) {
            s.append(restaurant + "\n");
        }
        return s.toString();
    }
}
