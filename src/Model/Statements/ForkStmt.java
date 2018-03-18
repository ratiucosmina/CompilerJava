package Model.Statements;

import Model.ADTs.*;
import Model.Exceptions.MyException;
import Model.PrgState;

public class ForkStmt implements IStmt {
    private IStmt child;

    public ForkStmt(IStmt child){this.child=child;}

    public String toString(){
        return "fork( {"+child+"} )";
    }

    @Override
    public PrgState execute(PrgState program) throws MyException {
        //int newID=program.getId()*10;
        MyIStack<IStmt> newStack =new MyStack<>();
        //newStack.push(child);
        MyIDictionary<String,Integer> newDict=new MyDictionary<>(program.getSymTable());
        MyILst<String> out=program.getOutput();
        IHeap heap=program.getHeap();
        IBarrier barrier=program.getBarrier();
        MyFileDictionary newFile=program.getFileTable();
        synchronized (heap){
            int newID=program.getLastID()*10;
            PrgState childProg=new PrgState(newID,newStack,newDict,newFile,out,heap,barrier,child,program.getFileName());
            return childProg;
        }
    }
}
