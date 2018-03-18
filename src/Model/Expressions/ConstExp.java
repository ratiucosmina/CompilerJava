package Model.Expressions;

import Model.ADTs.IHeap;
import Model.ADTs.MyIDictionary;
import Model.Exceptions.MyException;

public class ConstExp extends Exp {
    int number;

    public ConstExp(int nr){
        this.number=nr;
    }

    @Override
    public int evaluate(MyIDictionary<String, Integer> dict, IHeap heap) throws MyException {
        return number;
    }

    public String toString(){
        return number+"";
    }
}
