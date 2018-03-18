package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Tuple;

import java.io.IOException;

public class CFStmt implements IStmt {
    Exp variable;

    public CFStmt(Exp var){ variable=var;}

    @Override
    public PrgState execute(PrgState program) throws MyException {
        int desc=variable.evaluate(program.getSymTable(), program.getHeap());
        Tuple file=program.getFileTable().lookUp(desc);
        try {
            file.getFile().close();
        }catch (IOException e){
            throw new MyException(e.toString());
        }
        program.getFileTable().deleteFile(file.getFilename());
        return null;
    }

    public String toString(){
        return "closeRFile("+variable+")";
    }
}
