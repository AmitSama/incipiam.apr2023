package com.smartreporting.application.util;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;

public class FixedSizeQueue<E> extends AbstractQueue<E> {

    final Object[] items;
    int count;

    public FixedSizeQueue(int capacity) {
        super();
        items = new Object[capacity];
        count = 0;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> itr = (Iterator<E>) Arrays.stream(items).iterator();
        return itr;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException("Nulls not allowed in the queue");
        if (count == items.length) {
            this.poll();
        }
        this.items[count] = e;
        count++;
        return true;
    }

    @Override
    public E poll() {
        if (count <= 0)
            return null;
        E item = (E) items[0];
        shiftLeft();
        count--;
        return item;
    }

    private void shiftLeft() {
        int i = 1;
        while (i < items.length) {
            if (items[i] == null) {
                break;
            }
            items[i - 1] = items[i];
            i++;
        }
    }

    @Override
    public E peek() {
        if (count <= 0) {
            return null;
        }
        return (E) items[0];
    }

}
