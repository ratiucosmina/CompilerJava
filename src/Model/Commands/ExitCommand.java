package Model.Commands;

import Model.Exceptions.MyException;

public class ExitCommand extends Command {
    public ExitCommand(String key, String desc){
        super(key, desc);
    }
    @Override
    public void execute() throws MyException {
        System.out.println("Thank you!");
        System.exit(0);
    }
}