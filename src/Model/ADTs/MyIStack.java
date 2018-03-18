package Model.ADTs;

import Model.Exceptions.EmptyStackException;

public interface MyIStack<E> {
    public void push(E stmt);
    public E pop() throws EmptyStackException;
    public String toString();
    boolean isEmpty();
}
