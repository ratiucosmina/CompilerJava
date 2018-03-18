package Model.ADTs;

import Model.Exceptions.MyException;
import Model.Pair;

import java.util.ArrayList;
import java.util.Map;

public interface IBarrier {
    void addBarrier(Integer nr);
    void updateBarrie(Integer id, Integer pid) throws MyException;
    String toString();
    Pair lookUp(Integer id) throws MyException;
    boolean isDefined(Integer id);
    Map<Integer,Pair> getContent();
    Integer getId();
}
