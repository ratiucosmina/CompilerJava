package Model.ADTs;

public class MyList<E> implements MyILst<E> {
    private java.util.List<E> list;

    public<E> MyList(){
        list=new java.util.ArrayList<>();
    }

    @Override
    public void addList(E elem) {
        list.add(elem);
    }

    public String toString(){
        String s="";
        for(E elem:list){
            s=s+elem+"   ";
        }
        return s;
    }
}
