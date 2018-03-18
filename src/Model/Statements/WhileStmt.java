package Model.Statements;

import Model.ADTs.IHeap;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;

public class WhileStmt implements IStmt {
    Exp cond;
    IStmt instr;

    public WhileStmt(Exp condition, IStmt instruction){
        cond=condition;
        instr=instruction;
    }

    public String toString(){return "while("+cond+") {"+instr+"}";}

    @Override
    public PrgState execute(PrgState program) throws MyException {
        MyIDictionary<String,Integer> dict=program.getSymTable();
        IHeap heap=program.getHeap();
        MyIStack<IStmt> stack=program.getExeStack();
        if(cond.evaluate(dict,heap)!=0) {
            stack.push(this);
            stack.push(instr);
        }
        return null;
    }
}
