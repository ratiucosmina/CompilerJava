package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;

public class NewBarrierStmt implements IStmt {
    String var;
    Exp expr;

    public NewBarrierStmt(String variable,Exp expression){
        this.var=variable;
        this.expr=expression;
    }

    @Override
    public String toString() {
        return "newBarrier("+var+","+expr+")";
    }

    @Override
    public PrgState execute(PrgState program) throws MyException {
        Integer value=expr.evaluate(program.getSymTable(),program.getHeap());
        Integer id=program.getBarrier().getId();
        program.getBarrier().addBarrier(value);

        if(program.getSymTable().isDefined(var))
            program.getSymTable().updateVariable(var,id);
        else
            program.getSymTable().addVariable(var,id);
        return null;
    }
}
