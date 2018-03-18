package Model.ADTs;

import Model.Exceptions.EmptyStackException;

public class MyStack<E> implements MyIStack<E> {
    java.util.Stack<E> exestack;

    public <E> MyStack(){
        exestack=new java.util.Stack();
    }

    public void push(E stmt){
        exestack.push(stmt);
    }

    public E pop() throws EmptyStackException {
        if(exestack.isEmpty())
            throw new EmptyStackException();
        return exestack.pop();
    }

    public String toString(){
        java.util.Stack<E> aux=new java.util.Stack<E>();
        String s="";
        while(!exestack.empty()){
            E elem=exestack.pop();
            s=s+elem+"\n\t";
            aux.push(elem);
        }
        while(!aux.empty()){
            exestack.push(aux.pop());
        }
        return s;
    }

    public boolean isEmpty(){
        return this.exestack.isEmpty();
    }
}
