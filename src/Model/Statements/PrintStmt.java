package Model.Statements;

import Model.ADTs.MyILst;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;

public class PrintStmt implements IStmt {
    Exp printed;

    public PrintStmt(Exp to_print){
        this.printed=to_print;
    }

    public String toString(){
        return "print("+printed+")";
    }

    @Override
    public PrgState execute(PrgState program) throws MyException {
        MyILst<String> list=program.getOutput();
        Integer s=printed.evaluate(program.getSymTable(),  program.getHeap());
        list.addList(s.toString());
        return null;
    }
}
