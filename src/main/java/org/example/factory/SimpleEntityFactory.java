package org.example.factory;

//10.7
public abstract class SimpleEntityFactory<T> implements EntityFactory<T> {
    private int nextId = 1;

    protected int getNextId() {

        return nextId++;
    }
}
