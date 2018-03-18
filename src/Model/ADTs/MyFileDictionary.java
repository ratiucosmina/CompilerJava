package Model.ADTs;

import Model.Exceptions.MyException;
import Model.Tuple;

import java.util.HashMap;
import java.util.Map;

public class MyFileDictionary extends MyDictionary {
    static int i=0;
    HashMap<Integer, Tuple> dict;

    public MyFileDictionary(){dict=new HashMap<>();}

    public void addVariable(String filename) throws MyException {
        Tuple t=new Tuple(filename);
        i++;
        this.dict.put(i,t);
    }

    public Tuple lookUp(int var) throws MyException {
        if(!this.dict.containsKey(var))
            throw new MyException("Variable "+var+" is not defined yet!");
        return this.dict.get(var);
    }

    public int getDescriptor(String filename){
        for(int k=1;k<=i;k++)
            try{
                if(this.lookUp(k).getFilename()==filename)
                    return k;
            }
            catch(MyException e){ }
        return 0;
    }
    public void deleteFile(String file) throws MyException {
        int k=getDescriptor(file);
        if(k==0){
            throw new MyException("File not opened!\n");
        }
        this.dict.remove(k);
    }

    public String toString(){
        String s="";
        for(int var:this.dict.keySet()) {
            s = s + var + "->" + this.dict.get(var) + "\n\t";
        }
        return s;
    }

    public Map<Integer, Tuple> getContent(){return (Map)this.dict;}

}