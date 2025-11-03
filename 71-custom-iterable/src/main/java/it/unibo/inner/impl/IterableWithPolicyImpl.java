package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Arrays;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> elements;
    private Predicate<T> policy;

    public IterableWithPolicyImpl(final T[] inputArray) {
        this(inputArray, new Predicate<T>() {

            @Override
            public boolean test(T elem) {
                return true;
            }
            
        });
    }

    public IterableWithPolicyImpl(final T[]inputArray, final Predicate<T> filter) {
        this.elements = new ArrayList<>(Arrays.asList(inputArray));
        this.policy = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return this.new ArrayIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.policy = filter;
    }

    private class ArrayIterator implements Iterator<T> {

        private int current;

        public ArrayIterator() {
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            for(int i = current; i < IterableWithPolicyImpl.this.elements.size(); i++) {
                if(IterableWithPolicyImpl.this.policy.test(IterableWithPolicyImpl.this.elements.get(i))) {
                    current = i;
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return IterableWithPolicyImpl.this.elements.get(current++);
            }
            throw new NoSuchElementException();
        }

    }

}