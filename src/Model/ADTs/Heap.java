package Model.ADTs;

import Model.Exceptions.MyException;

import java.util.Map;

public class Heap implements IHeap {
    private int freePos=1;
    MyDictionary<Integer,Integer> heap;

    public Heap(){freePos=1;heap =new MyDictionary<>();}

    @Override
    public void addVariable(int value){
        try {
            heap.addVariable(freePos, value);
        }
        catch(MyException e){}
        freePos++;
    }

    @Override
    public void updateVariable(int address, int value) throws MyException {
        heap.updateVariable(address,value);
    }

    @Override
    public boolean isDefined(int address) {
        return heap.isDefined(address);
    }

    @Override
    public int lookUp(int address) throws MyException {
        return heap.lookUp(address);
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    @Override
    public int getFreePosition() {
        return freePos;
    }

    @Override
    public Map getContent() {
        return (Map<Integer,Integer>)heap.getContent();
    }

    public void setContent(Map<Integer,Integer> map){this.heap.setContent(map);}
}
