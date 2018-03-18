package Model.Statements;

import Model.ADTs.MyIDictionary;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;

public class AssignStmt implements IStmt {
    String id;
    Exp value;

    public AssignStmt(String var,Exp val){
        this.id=var;
        this.value=val;
    }

    public String toString(){
        return id+"="+value;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String,Integer> symTbl= state.getSymTable();
        int val = value.evaluate(symTbl,state.getHeap());
        if (symTbl.isDefined(id))
            symTbl.updateVariable(id, val);
        else
            try {
                symTbl.addVariable(id,val);
            } catch (MyException error) {
                error.printStackTrace();
            }
        return null;
    }
}
