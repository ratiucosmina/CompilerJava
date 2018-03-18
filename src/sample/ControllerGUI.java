package sample;

import Controller.Controller;
import Model.ADTs.*;
import Model.Exceptions.MyException;
import Model.Expressions.ArithExp;
import Model.Expressions.ConstExp;
import Model.Expressions.RHeapExp;
import Model.Expressions.VarExp;
import Model.MyEvent;
import Model.Pair;
import Model.PrgState;
import Model.Statements.*;
import Model.Tuple;
import Repository.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Map;

public class ControllerGUI {
    public Button runButton;
    public ListView prgList;
    public TextField nrPrgField;
    public ListView exeStack;
    public TableColumn valHeapColumn;
    public TableColumn addrColumn;
    public TableColumn valSymColumn;
    public TableColumn varColumn;
    public TableView symTable;
    public TableColumn idColumn;
    public TableColumn fileColumn;
    public ListView outList;
    public Label currentProg;
    public TableView heapTable;
    public TableView fileTable;
    public TableColumn valheapColumn;
    public TableView barrierTable;
    public TableColumn idBarrierColumn;
    public TableColumn valBarrierColumn;
    public TableColumn listColumn;
    private ArrayList<PrgState> allPrgState;
    private Controller ctrl;
    private PrgState state;
    public ListView stmtList;
    public String stmt;

    public Button chooseButon;
    public Button loadButton;


    public void allProgs() {
        allPrgState=new ArrayList<>();
        IStmt ex1 = new CompStmt(new OFStmt("var_f", "test.in"),
                new CompStmt(new RFStmt(new VarExp("var_f"), "var_c"),
                        new CompStmt(new PrintStmt(new VarExp("var_c")),
                                new CompStmt(new IfStmt(new VarExp("var_c"),
                                        new CompStmt(new RFStmt(new VarExp("var_f"), "var_c"),
                                                new PrintStmt(new VarExp("var_c"))),
                                        new PrintStmt(new ConstExp(0))),
                                        new CFStmt(new VarExp("var_f"))))));
        try {
            allPrgState.add(new PrgState(1, new MyStack(), new MyDictionary(), new MyFileDictionary(), new MyList(), new Heap(), new Barrier(), ex1, "file.txt"));
        }catch(MyException e){
            throw new RuntimeException(e);
        }
        IStmt ex2 = new CompStmt(new AssignStmt("v", new ConstExp(1)),
                new CompStmt(new IfStmt(new VarExp("v"),
                        new CompStmt(new AssignStmt("v", new ConstExp(2)),
                                new PrintStmt(new VarExp("v"))),
                        new PrintStmt(new VarExp("v"))),
                        new PrintStmt(new ConstExp(2))));
        try {
            allPrgState.add(new PrgState(2, new MyStack(), new MyDictionary(), new MyFileDictionary(), new MyList(), new Heap(), new Barrier(), ex2, "file2.txt"));
        }catch(MyException e){
            throw new RuntimeException(e);
        }
        IStmt ex3=new CompStmt(new AssignStmt("v",new ConstExp(10)),
                new CompStmt(new NewStmt("v",new ConstExp(20)),
                        new CompStmt(new NewStmt("a",new ConstExp(22)),
                                new CompStmt(new WHeapStmt("a",new ConstExp(30)),
                                        new CompStmt(new PrintStmt(new VarExp("a")),new PrintStmt(new RHeapExp("a")))))));
        try {
            allPrgState.add(new PrgState(3, new MyStack(), new MyDictionary(), new MyFileDictionary(), new MyList(), new Heap(), new Barrier(), ex3, "file3.txt"));
        }catch(MyException e){
            throw new RuntimeException(e);
        }
        IStmt ex4=new CompStmt(new AssignStmt("v",new ConstExp(10)),
                new CompStmt(new NewStmt("a",new ConstExp(22)),
                        new CompStmt(new ForkStmt(new CompStmt(new WHeapStmt("a",new ConstExp(30)),
                                new CompStmt(new AssignStmt("v",new ConstExp(32)),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new RHeapExp("a")))))),
                                new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new RHeapExp("a"))))));
        try {
            allPrgState.add(new PrgState(4, new MyStack(), new MyDictionary(), new MyFileDictionary(), new MyList(), new Heap(), new Barrier(), ex4, "file4.txt"));
        }catch(MyException e){
            throw new RuntimeException(e);
        }

        IStmt ex5=new CompStmt(new AssignStmt("a",new ConstExp(1)),
                                new CompStmt(new NewStmt("a",new ConstExp(2)),
                                            new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("b",new ConstExp(3)),new PrintStmt(new VarExp("a")))),
                                                    new CompStmt(new AssignStmt("a",new ArithExp(new VarExp("a"),new ConstExp(2),"+")),
                                                                new PrintStmt(new VarExp("a"))))));
        try {
            allPrgState.add(new PrgState(5, new MyStack(), new MyDictionary(), new MyFileDictionary(), new MyList(), new Heap(), new Barrier(), ex5, "file5.txt"));
        }catch(MyException e){
            throw new RuntimeException(e);
        }

        IStmt ex6=new CompStmt(new ForkStmt(new CompStmt(new ForkStmt(new SkipStmt()),
                                                        new PrintStmt(new ConstExp(1)))),
                                new CompStmt(new ForkStmt(new CompStmt(new ForkStmt(new SkipStmt()),
                                                                    new PrintStmt(new ConstExp(2)))),
                                            new PrintStmt(new ConstExp(3))));
        try {
            allPrgState.add(new PrgState(6, new MyStack(), new MyDictionary(), new MyFileDictionary(), new MyList(), new Heap(), new Barrier(), ex6, "file6.txt"));
        }catch(MyException e){
            throw new RuntimeException(e);
        }

        IStmt ex7=new CompStmt(new NewStmt("v1",new ConstExp(2)),
                                new CompStmt(new NewStmt("v2",new ConstExp(3)),
                                            new CompStmt(new NewStmt("v3",new ConstExp(4)),
                                                        new CompStmt(new NewBarrierStmt("cnt",new RHeapExp("v2")),
                                                                    new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"),
                                                                                                            new CompStmt(new WHeapStmt("v1",new ArithExp(new RHeapExp("v1"), new ConstExp(10),"*")),
                                                                                                                        new PrintStmt(new RHeapExp("v1"))))),
                                                                                new CompStmt(new ForkStmt(new CompStmt(new AwaitStmt("cnt"),
                                                                                                                        new CompStmt(new WHeapStmt("v2",new ArithExp(new RHeapExp("v2"),new ConstExp(10),"*")),
                                                                                                                                    new CompStmt(new WHeapStmt("v2",new ArithExp(new RHeapExp("v2"),new ConstExp(10),"*")),
                                                                                                                                                new PrintStmt(new RHeapExp("v2")))))),
                                                                                            new CompStmt(new AwaitStmt("cnt"),
                                                                                                        new PrintStmt(new RHeapExp("v3")))))))));
        try {
            allPrgState.add(new PrgState(7, new MyStack(), new MyDictionary(), new MyFileDictionary(), new MyList(), new Heap(), new Barrier(), ex7, "file7.txt"));
        }catch(MyException e){
            throw new RuntimeException(e);
        }
    }



    public void PrgChosen(ActionEvent actionEvent) {
        int id=stmtList.getSelectionModel().getSelectedIndex();
        PrgState prog=allPrgState.get(id);
        Repository repo=new Repository();
        repo.addProgram(prog);
        ctrl=new Controller(repo);
        stmt=prog.getInit().toString();
        init();
    }

    public void loadPrgList(ActionEvent actionEvent) {
        allProgs();
        for (PrgState p : allPrgState)
            stmtList.getItems().addAll(p.getInit().toString());
    }
    public void init(){
        //currentProg.setText(stmt);
        ArrayList<PrgState> repo=ctrl.getRepo().getRepo();
        nrPrgField.setText(((Integer)repo.size()).toString());
        for(PrgState p:repo)
            prgList.getItems().addAll(p.getId());
    }

    public void oneStep(ActionEvent actionEvent) {
        try{
            ctrl.allSteps();
            prgList.getItems().clear();
            init();
            insertIntoTables();
        }
        catch(MyException e){
            Stage error=new Stage();
            error.setTitle("Message Window");
            Label msg=new Label(e.toString());
            Button okButton=new Button("OK");
            okButton.setOnAction(f->error.close());
            VBox layout=new VBox(15);
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(msg,okButton);
            Scene scene=new Scene(layout,400,200);
            error.setScene(scene);
            error.showAndWait();
        }
    }

    public void clearAll(){
        exeStack.getItems().clear();
        outList.getItems().clear();
        fileTable.getItems().clear();
        symTable.getItems().clear();
        heapTable.getItems().clear();
        barrierTable.getItems().clear();
    }

    public void insertIntoTables(){
        clearAll();
        ObservableList<Map.Entry<String,Integer>> sym=FXCollections.observableArrayList(state.getSymTable().getContent().entrySet());
        varColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String,Integer>,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String,Integer>,String> param) {
                return new SimpleStringProperty(param.getValue().getKey());
            }
        });
        valSymColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Integer>, SimpleIntegerProperty>() {
            @Override
            public SimpleIntegerProperty call(TableColumn.CellDataFeatures<Map.Entry<String,Integer>,Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getValue());
            }
        });
        symTable.setItems(sym);

        ObservableList<Map.Entry<Integer,Integer>> heap=FXCollections.observableArrayList(state.getHeap().getContent().entrySet());
        addrColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer,Integer>,Integer>, SimpleIntegerProperty>() {
            @Override
            public SimpleIntegerProperty call(TableColumn.CellDataFeatures<Map.Entry<Integer,Integer>,Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getKey());
            }
        });
        valHeapColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer>, SimpleIntegerProperty>() {
            @Override
            public SimpleIntegerProperty call(TableColumn.CellDataFeatures<Map.Entry<Integer,Integer>,Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getValue());
            }
        });
        heapTable.setItems(heap);

        ObservableList<Map.Entry<Integer, Tuple>> file=FXCollections.observableArrayList(state.getFileTable().getContent().entrySet());
        fileColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Tuple>,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Tuple>,String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getFilename());
            }
        });
        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Tuple>, Integer>, SimpleIntegerProperty>() {
            @Override
            public SimpleIntegerProperty call(TableColumn.CellDataFeatures<Map.Entry<Integer, Tuple>,Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getKey());
            }
        });
        fileTable.setItems(file);

        ObservableList<Map.Entry<Integer, Pair>> barrier=FXCollections.observableArrayList(state.getBarrier().getContent().entrySet());
        idBarrierColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer,Pair>,Integer>, SimpleIntegerProperty>() {
            @Override
            public SimpleIntegerProperty call(TableColumn.CellDataFeatures<Map.Entry<Integer,Pair>,Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getKey());
            }
        });
        valBarrierColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Pair>, Integer>, SimpleIntegerProperty>() {
            @Override
            public SimpleIntegerProperty call(TableColumn.CellDataFeatures<Map.Entry<Integer,Pair>,Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getValue().getNr());
            }
        });

        listColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer,Pair>,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<Integer,Pair>,String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getList().toString());
            }
        });

        barrierTable.setItems(barrier);

        exeStack.getItems().addAll(state.getExeStack());
        outList.getItems().addAll(state.getOutput());
    }

    public void selectedPrgState(MouseEvent mouseEvent) {
        int id = prgList.getSelectionModel().getSelectedIndex();
        state = ctrl.getRepo().getRepo().get(id);
        currentProg.setText("Current program ID: " +state.getId());
        insertIntoTables();
        //symTable.getItems().addAll(state.getSymTable());
    }
}
