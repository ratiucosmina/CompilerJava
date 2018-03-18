package Model.Expressions;


import Model.ADTs.IHeap;
import Model.ADTs.MyIDictionary;
import Model.Exceptions.MyException;

public class BoolExp extends Exp {
    private Exp ex1,ex2;
    private String op;

    public BoolExp(Exp e1,Exp e2, String op){
        this.ex1=e1;
        this.ex2=e2;
        this.op=op;
    }

    public String toString(){ return ex1+op+ex2;}

    @Override
    public int evaluate(MyIDictionary<String, Integer> dict, IHeap heap) throws MyException {
        switch(op) {
            case ("<"): { if(ex1.evaluate(dict,heap)<ex2.evaluate(dict,heap)) return 1; break;}
            case ("<="): { if(ex1.evaluate(dict,heap)<=ex2.evaluate(dict,heap)) return 1; break;}
            case (">"): { if(ex1.evaluate(dict,heap)>ex2.evaluate(dict,heap)) return 1; break;}
            case (">="): { if(ex1.evaluate(dict,heap)>=ex2.evaluate(dict,heap)) return 1; break;}
            case ("=="): { if(ex1.evaluate(dict,heap)==ex2.evaluate(dict,heap)) return 1; break;}
            case ("!="): { if(ex1.evaluate(dict,heap)!=ex2.evaluate(dict,heap)) return 1; break;}
        }
        return 0;
    }
}
