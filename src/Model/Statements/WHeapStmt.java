package Model.Statements;

import Model.ADTs.IHeap;
import Model.ADTs.MyIDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;

public class WHeapStmt implements IStmt {
    private String variable;
    private Exp value;

    public WHeapStmt(String var,Exp exp){variable=var; value=exp;}

    @Override
    public PrgState execute(PrgState program) throws MyException {
        MyIDictionary<String,Integer> dict=program.getSymTable();
        IHeap heap=program.getHeap();

        int address=dict.lookUp(variable);
        heap.updateVariable(address,value.evaluate(dict,heap));
        return null;
    }

    @Override
    public String toString() {
        return "writeHeap("+variable+","+value+")";
    }
}
