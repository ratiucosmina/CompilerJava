package Model.Expressions;

import Model.ADTs.Heap;
import Model.ADTs.IHeap;
import Model.ADTs.MyIDictionary;
import Model.Exceptions.MyException;

public class ArithExp extends Exp {
    Exp e1;
    Exp e2;
    String op;

    public ArithExp(Exp ex1, Exp ex2, String oper){
        this.e1=ex1;
        this.e2=ex2;
        this.op=oper;
    }

    public int evaluate(MyIDictionary<String, Integer> dict, IHeap heap) throws MyException {
        switch (op) {
            case "+":
                return e1.evaluate(dict, heap) + e2.evaluate(dict, heap);
            case "-":
                return e1.evaluate(dict, heap) - e2.evaluate(dict, heap);
            case "*":
                return e1.evaluate(dict, heap) * e2.evaluate(dict, heap);
            case "/":{
                if(e2.evaluate(dict, heap)==0)
                    throw new MyException("Division by zero!");
                return e1.evaluate(dict, heap) / e2.evaluate(dict, heap);}
            case "%":
                return e1.evaluate(dict, heap) % e2.evaluate(dict, heap);
        }
        return 0;
    }

    public String toString(){
        return e1+op+e2;
    }
}
