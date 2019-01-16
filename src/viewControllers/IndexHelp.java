package viewControllers;
import entities.Student;
import entities.Subject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logicControllers.Help;
import logicControllers.Subjects;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexHelp implements Initializable {

    @FXML
    private ListView<String> index;

    @FXML
    private TextArea info;





    public void setPage(String clase) {

        Help help = new Help();
        Subjects subjects = new Subjects();
        ObservableList<String> indexObservableList = FXCollections.observableArrayList();
        indexObservableList.addAll(help.getIndex());
        index.setItems(indexObservableList);
        info.setText(help.getText(clase));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        index.getSelectionModel().selectedItemProperty().addListener((observable, oldClickedSubject, clickedSubject) -> {
            Help help = new Help();
            info.setText(help.getText(clickedSubject));
        });


    }


}
