package Repository;

import Model.PrgState;

import java.util.ArrayList;

public interface IRepository {
    PrgState getCurrentProgram();
    String toString();
    void addProgram(PrgState prog);
    void logPrgStateExec(PrgState state);
    ArrayList<PrgState> getRepo();
    void setRepo(ArrayList<PrgState> repo);
}
