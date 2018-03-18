package Model.Statements;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;

public class IfStmt implements IStmt {
    Exp expression;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp expr, IStmt thens, IStmt elses){
        this.expression=expr;
        this.thenS=thens;
        this.elseS=elses;
    }

    public String toString(){
        return "if("+expression+") then{ "+thenS+" } else{ "+elseS+"}";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stack=state.getExeStack();
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        if(this.expression.evaluate(symTable, state.getHeap())==0)
            stack.push(elseS);
        else
            stack.push(thenS);
        return null;
    }
}
