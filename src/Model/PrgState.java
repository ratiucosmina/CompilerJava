package Model;

import Model.ADTs.*;
import Model.Exceptions.EmptyStackException;
import Model.Exceptions.MyException;
import Model.Statements.IStmt;

public class PrgState {
    private MyIStack exeStack;
    private MyIDictionary symTable;
    private MyFileDictionary fileTable;
    private MyILst output;
    private IHeap heap;
    private IBarrier barrier;
    private IStmt init;
    private String file;
    private int id;
    private static int lastID;

    public PrgState(int id, MyIStack stack, MyIDictionary dict, MyFileDictionary file, MyILst list, IHeap h, IBarrier bar,IStmt prog, String f) throws MyException {
        this.id=id;
        this.lastID=id;
        this.exeStack=stack;
        this.symTable=dict;
        this.output=list;
        this.fileTable=file;
        this.init=prog;
        this.heap=h;
        this.barrier=bar;
        this.file=f;
//        try {
//            PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(f, false)));
//            logFile.write(prog.toString()+"\n");
//            logFile.flush();
//            logFile.close();
//        }
//        catch(Exception e){
//            throw new MyException("Failed to create the log file!");
//        }
        this.exeStack.push(this.init);
    }

    public void reset(MyIStack stack, MyIDictionary dict, MyFileDictionary file, MyILst list, IHeap h, IBarrier bar,IStmt prog){
        this.exeStack=stack;
        exeStack.push(prog);
        this.symTable=dict;
        this.output=list;
        this.fileTable=file;
        this.barrier=bar;
        this.init=prog;
        this.heap=h;
    }

    public MyIStack getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack stack){
        this.exeStack=stack;
    }

    public MyIDictionary getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary dict){
        this.symTable=dict;
    }

    public MyILst getOutput() {
        return output;
    }

    public void setOutput(MyILst list){
        this.output=list;
    }

    public IStmt getInit() {
        return init;
    }

    public void setInit(IStmt prog){
        this.init=prog;
    }

    public IHeap getHeap() {
        return heap;
    }

    public String toString(){
        return "Program state "+id+"\nStack:\n\t"+this.exeStack.toString()+"\nSymbol Table:\n\t"+this.symTable.toString()+"\nOutput:\n\t"+this.output.toString()+"\nFile Table:\n\t"+this.fileTable+"\nHeap:\n\t"+this.heap+"\nCyclic Barrier: "+this.barrier+"\n";
    }

    public IBarrier getBarrier() {
        return barrier;
    }

    public void setBarrier(IBarrier barrier) {
        this.barrier = barrier;
    }

    public String getFileName(){
        return file;
    }

    public MyFileDictionary getFileTable() {
        return fileTable;
    }

    public PrgState oneStep() throws Exception {
        if(exeStack.isEmpty())
            throw new EmptyStackException();
        MyIStack<IStmt> stack = this.getExeStack();
        IStmt stmt = stack.pop();
        return stmt.execute(this);
    }

    public int getId() {
        return id;
    }
    public Boolean isCompleted(){return !exeStack.isEmpty();}

    public static int getLastID() {
        return lastID;
    }
}
