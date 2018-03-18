package Model.Statements;

import Model.Exceptions.MyException;
import Model.Pair;
import Model.PrgState;

public class AwaitStmt implements IStmt {
    private String var;

    public AwaitStmt(String variable){this.var=variable;}

    @Override
    public String toString() {
        return "await("+var+")";
    }

    @Override
    public PrgState execute(PrgState program) throws MyException {
        Integer index = (Integer) program.getSymTable().lookUp(var);
        if (!program.getBarrier().isDefined(index))
            throw new MyException("Barrier not defined!");
        Pair p = program.getBarrier().lookUp(index);
        Integer length = p.getList().size();
        Integer n = p.getNr();
        if (n > length)
            if (p.getList().contains(program.getId()))
                program.getExeStack().push(this);
            else {
                p.getList().add(program.getId());
                program.getExeStack().push(this);
            }
        return null;
    }
}
