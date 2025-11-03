package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> elements;

    public IterableWithPolicyImpl(final T[] inputArray) {
        this.elements = new ArrayList<>(Arrays.asList(inputArray));
    }

    @Override
    public Iterator<T> iterator() {
        return this.new ArrayIterator<T>();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO
    }

    private class ArrayIterator<T> implements Iterator<T> {

        private int current;

        public ArrayIterator() {
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            if (this.current < IterableWithPolicyImpl.this.elements.size() - 1) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            return (T) IterableWithPolicyImpl.this.elements.get(current++);
        }

    }

}