package Model;

import java.util.ArrayList;

public class Pair {
    private Integer nr;
    private ArrayList<Integer> list;

    public Pair(Integer nr) {
        this.nr=nr;
        list=new ArrayList<>();
    }

    public Pair(Integer nr, ArrayList<Integer>list){
        this.nr=nr;
        this.list=list;
    }

    public String toString(){
        return "("+nr+", "+list+")";
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public Integer getNr() {
        return nr;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }
}
