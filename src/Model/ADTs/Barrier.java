package Model.ADTs;

import Model.Exceptions.MyException;
import Model.Pair;

import java.util.ArrayList;
import java.util.Map;

public class Barrier implements IBarrier {
    private static Integer id=0;
    private MyIDictionary<Integer, Pair> barrier;

    public Barrier(){id=0; barrier=new MyDictionary<>();}

    @Override
    public String toString() {
        return barrier.toString();
    }

    @Override
    public Map<Integer, Pair> getContent() {
        return barrier.getContent();
    }

    @Override
    public synchronized void addBarrier(Integer nr) {
        try {
            barrier.addVariable(id, new Pair(nr));
        } catch(MyException e){}
    }

    @Override
    public synchronized void updateBarrie(Integer id, Integer pid) throws MyException {
        ArrayList<Integer> list = barrier.lookUp(id).getList();
        list.add(id);
        barrier.lookUp(id).setList(list);

    }

    @Override
    public synchronized Pair lookUp(Integer id) throws MyException {
        return barrier.lookUp(id);

    }

    @Override
    public boolean isDefined(Integer id) {
        return barrier.isDefined(id);
    }

    public Integer getId() {
        return id;
    }
}
