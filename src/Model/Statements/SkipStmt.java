package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;

public class SkipStmt implements IStmt {
    public SkipStmt(){};
    public String toString(){return "skip()";};

    @Override
    public PrgState execute(PrgState program) throws MyException {
        return null;
    }
}
