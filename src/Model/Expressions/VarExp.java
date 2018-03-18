package Model.Expressions;

import Model.ADTs.IHeap;
import Model.ADTs.MyIDictionary;
import Model.Exceptions.MyException;

public class VarExp extends Exp {
    String variable;

    public VarExp(String var){
        variable=var;
    }

    @Override
    public int evaluate(MyIDictionary<String, Integer> dict, IHeap heap) throws MyException {
        return dict.lookUp(variable);
    }

    public String toString(){
        return variable;
    }
}
