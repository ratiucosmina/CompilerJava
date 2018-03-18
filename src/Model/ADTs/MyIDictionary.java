package Model.ADTs;

import Model.Exceptions.MyException;

import java.util.Map;

public interface MyIDictionary<E,T> {
    void addVariable(E variable, T value) throws MyException;
    String toString();
    boolean isDefined(E variable);
    void updateVariable(E variable, T value) throws MyException;
    T lookUp(E variable) throws MyException;
    Map getContent();
    void setContent(Map<E, T> map);
}
