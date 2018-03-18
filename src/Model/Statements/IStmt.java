package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;

public interface IStmt {
    String toString();
    PrgState execute(PrgState program) throws MyException;
}
