package Model.Statements;

import Model.ADTs.MyIStack;
import Model.Exceptions.MyException;
import Model.PrgState;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt f, IStmt s){
        this.first=f;
        this.second=s;
    }

    public String toString(){
        return first+";"+second;
    }

    public PrgState execute(PrgState program) throws MyException {
        MyIStack<IStmt> stk=program.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }
}
