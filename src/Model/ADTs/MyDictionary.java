package Model.ADTs;

import Model.Exceptions.MyException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<E,T> implements MyIDictionary<E,T>, Serializable {
    protected HashMap<E,T> dict;

    public<E,T> MyDictionary(){
        this.dict=new HashMap<>();
    }

    public<E,T> MyDictionary(MyIDictionary<E,T> oldDict){
        Map old=oldDict.getContent();
        Map newold=new HashMap();
        for(Object s:old.keySet()){
            newold.put(s,old.get(s));
        }
        this.setContent(newold);
    }

    @Override
    public void addVariable(E variable, T value) throws MyException {
        if(this.dict.containsKey(variable))
            throw new MyException("Variable "+variable+" is already defined!");
        this.dict.put(variable,value);
    }

    public String toString(){
        String s="";
        for(E var:this.dict.keySet()) {
            s = s + var + "->" + this.dict.get(var) + "\n\t";
        }
        return s;
    }

    @Override
    public boolean isDefined(E variable) {
        return this.dict.containsKey(variable);
    }

    @Override
    public void updateVariable(E variable, T value) throws MyException {
        if(!this.dict.containsKey(variable))
            throw new MyException("Variable "+variable+" is not defined yet!");
        this.dict.put(variable,value);
    }

    public T lookUp(E var) throws MyException {
        if(!this.dict.containsKey(var))
            throw new MyException("Variable "+var+" is not defined yet!");
        return this.dict.get(var);
    }

    @Override
    public Map getContent() {
        return (Map<Integer,Integer>)dict;
    }

    public void setContent(Map<E,T> map){ this.dict=(HashMap<E,T>)map;}
}
