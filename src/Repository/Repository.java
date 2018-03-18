package Repository;

import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Repository implements IRepository {
    ArrayList<PrgState> repo;
    int index;
    PrintWriter logFile;

    public Repository(){
        repo=new ArrayList<>();
        index=0;
    }

    public void addProgram(PrgState prog){
        this.repo.add(prog);
        index++;
        logPrgStateExec(prog);
        try {
            PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(prog.getFileName(), false)));
            logFile.write(prog.toString()+"\n");
            logFile.flush();
            logFile.close();
        }
        catch(Exception e){

        }
    }

    public PrgState getCurrentProgram() {
        return repo.get(index-1);
    }

    @Override
    public void logPrgStateExec(PrgState state) {
        try{
            logFile= new PrintWriter(new BufferedWriter(new FileWriter(getCurrentProgram().getFileName(), true)));
            logFile.write(state.toString());
            logFile.flush();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        logFile.close();
    }

    public ArrayList<PrgState> getRepo() {
        return repo;
    }

    public void setRepo(ArrayList<PrgState> repo) {
        this.repo = repo;
    }
}
