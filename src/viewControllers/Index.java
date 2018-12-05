package viewControllers;

import entities.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import logicControllers.IndexController;

import java.net.URL;
import java.util.ResourceBundle;

public class Index implements Initializable{

    @FXML
    private ListView<Subject> listsubjects;

    private IndexController index;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Subject> observableList = FXCollections.observableArrayList(new Subject("Mates"), new Subject("Lang"));
        listsubjects.setItems(observableList);
    }

    public void setUser(String DNI){
        this.index = new IndexController(DNI);
    }



}
