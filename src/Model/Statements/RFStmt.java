package Model.Statements;

import Model.Exceptions.MyException;
import Model.Expressions.ConstExp;
import Model.Expressions.Exp;
import Model.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class RFStmt implements IStmt {
    private Exp file;
    private String variable;

    public RFStmt(Exp f, String v){file=f; variable=v;}

    @Override
    public PrgState execute(PrgState program) throws MyException {
        int desc=file.evaluate(program.getSymTable(), program.getHeap());
        BufferedReader buff;
        try {
            buff = program.getFileTable().lookUp(desc).getFile();
        }
        catch(MyException e){
            throw new MyException("Variable "+file+" not defined!");
        }
        try {
            String line = buff.readLine();
            if(line==null){
                AssignStmt stmt=new AssignStmt(variable,new ConstExp(0));
                stmt.execute(program);
                return null;
            }
            else{
                Integer val=Integer.parseInt(line);
                AssignStmt stmt=new AssignStmt(variable,new ConstExp(val));
                stmt.execute(program);
                return null;
            }
        }
        catch(IOException e){
            throw new MyException(e.toString());
        }
    }

    @Override
    public String toString() {
        return "readFile("+file+","+variable+")";
    }
}
