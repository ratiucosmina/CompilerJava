package Model.ADTs;

import Model.Exceptions.MyException;

import java.util.Map;

public interface IHeap {
    void addVariable(int value);
    String toString();
    boolean isDefined(int address);
    void updateVariable(int address, int value) throws MyException;
    int lookUp(int address) throws MyException;
    int getFreePosition();
    Map getContent();
    void setContent(Map<Integer, Integer> map);
}
