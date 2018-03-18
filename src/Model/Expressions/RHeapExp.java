package Model.Expressions;


import Model.ADTs.IHeap;
import Model.ADTs.MyIDictionary;
import Model.Exceptions.MyException;

public class RHeapExp extends Exp {
    private String variable;

    public RHeapExp(String v){variable=v;}

    @Override
    public int evaluate(MyIDictionary<String, Integer> dict, IHeap heap) throws MyException {
        int address=dict.lookUp(variable);
        return heap.lookUp(address);
    }

    @Override
    public String toString() {
        return "readHash("+variable+")";
    }
}
