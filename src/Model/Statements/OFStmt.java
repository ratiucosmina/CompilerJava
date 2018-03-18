package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;

public class OFStmt implements IStmt {
    private String var;
    private String filename;

    public OFStmt(String v,String f){
        var=v;
        filename=f;
    }

    @Override
    public PrgState execute(PrgState program) throws MyException {
        if(program.getFileTable().getDescriptor(filename)!=0)
            throw new MyException("File already opened!");
        program.getFileTable().addVariable(filename);
        program.getSymTable().addVariable(var,program.getFileTable().getDescriptor(filename));
        return null;
    }

    public String toString(){
        return "openRFile("+var+",\""+filename+"\")";
    }
}
