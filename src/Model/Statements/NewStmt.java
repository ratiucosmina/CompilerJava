package Model.Statements;

import Model.ADTs.IHeap;
import Model.ADTs.MyIDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;

public class NewStmt implements IStmt {
    private String variable;
    private Exp value;

    public NewStmt(String var,Exp exp){variable=var; value=exp;}

    @Override
    public PrgState execute(PrgState program) throws MyException {
        IHeap heap=program.getHeap();
        MyIDictionary<String,Integer> dict=program.getSymTable();
        if(dict.isDefined(variable))
            dict.updateVariable(variable,heap.getFreePosition());
        else
            dict.addVariable(variable,heap.getFreePosition());
        heap.addVariable(value.evaluate(dict, program.getHeap()));
        return null;
    }

    @Override
    public String toString() {
        return "new("+variable+","+value+")";
    }
}
