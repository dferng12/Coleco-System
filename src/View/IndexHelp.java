package View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import Controller.Help;
import Controller.Subjects;

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
