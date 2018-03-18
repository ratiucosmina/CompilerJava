package Model.Exceptions;

public class EmptyStackException extends Exception {
    private String message;

    public EmptyStackException()
    {
        super("Stack is empty!!!!");
        this.message="Stack is empty!!!!";
    }
}
