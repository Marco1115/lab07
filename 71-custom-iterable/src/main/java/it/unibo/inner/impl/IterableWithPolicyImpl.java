package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> elements;
    private Predicate<T> policy;

    public IterableWithPolicyImpl(final T[] inputArray) {
        this.elements = new ArrayList<>(Arrays.asList(inputArray));
    }

    public IterableWithPolicyImpl(final T[]inputArray, final Predicate<T> filter) {
        this(inputArray);
        this.policy = filter;
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
            if (this.current < IterableWithPolicyImpl.this.elements.size()) {
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