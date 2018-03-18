package Model.Commands;


import Controller.Controller;
import Model.Exceptions.MyException;

public class RunCommand extends Command {
    private Controller ctr;
    public RunCommand(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() throws MyException {
        ctr.allSteps();
    }

    public Controller getCtr() {
        return ctr;
    }
}
