package Controller;

import Model.ADTs.MyILst;
import Model.ADTs.MyList;
import Model.Exceptions.MyException;
import Model.PrgState;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller{
    IRepository repo;
    ExecutorService executor;
    MyILst<String> out=new MyList<>();

    Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}

    public Controller(IRepository r){
        repo=r;
    }

    public void addProgram(PrgState prog){
        repo.addProgram(prog);
    }

//    public PrgState oneStep(PrgState prog) {
//        try{
//            MyIStack<IStmt> stack=prog.getExeStack();
//            IStmt stmt=stack.pop();
//            stmt.execute(prog);
//        }
//        catch(EmptyStackException e){
//            System.out.println(e);
//        }
//        catch(MyException e){
//            System.out.println(e);
//        }
//        return prog;
//    }

    public ArrayList<PrgState> removeCompletedPrg(ArrayList<PrgState> inPrgList){
        return (ArrayList<PrgState>)inPrgList.stream()
                .filter(p -> p.isCompleted())
                .collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) throws MyException {

        prgList.forEach(prg -> repo.logPrgStateExec(prg));

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());

        ArrayList<String> exceptions=new ArrayList<>();
        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            exceptions.add(e.toString());
                        }
                        return null;
                    }).filter(p -> p != null).collect(Collectors.toList());
            if(exceptions.size()!=0)
                throw new MyException(exceptions.get(0));
            prgList.addAll(newPrgList);
            prgList.forEach(prg -> repo.logPrgStateExec(prg));
            repo.setRepo((ArrayList<PrgState>) prgList);
        } catch (InterruptedException e) {
        }
    }

    public void allSteps() throws MyException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs

        List<PrgState> prgList=removeCompletedPrg(repo.getRepo());
        if(prgList.size() == 0) {
            throw new MyException("The program has finished successfully!");
        }
        oneStepForAllPrg(prgList);
        prgList=removeCompletedPrg(repo.getRepo());
        executor.shutdownNow();
        repo.setRepo((ArrayList<PrgState>)prgList);
    }

    public PrgState getCurrentProgram(){
        return repo.getCurrentProgram();
    }

    public IRepository getRepo() {
        return repo;
    }
}
