package Model.Expressions;

import Model.ADTs.IHeap;
import Model.ADTs.MyIDictionary;
import Model.Exceptions.MyException;

public abstract class Exp {
    public abstract String toString();
    public abstract int evaluate(MyIDictionary<String, Integer> dict, IHeap heap) throws MyException;
}
