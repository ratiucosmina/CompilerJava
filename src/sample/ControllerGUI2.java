package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


public class ControllerGUI2 {
    public ListView prgList;

    public void oneStep(ActionEvent actionEvent) {
        System.out.println("1");
        prgList.getItems().addAll("1");
    }

    public void PrgChosen(ActionEvent actionEvent) {
        System.out.println("2");
        prgList.getItems().addAll("2");
    }

    public void loadPrgList(ActionEvent actionEvent) {
        System.out.println("3");
        prgList.getItems().addAll("3");
    }

    public void selectedPrgState(MouseEvent mouseEvent) {
    }
}
