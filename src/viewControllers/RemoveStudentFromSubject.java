package viewControllers;

import entities.Student;
import entities.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logicControllers.DAOS.DAOSubject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class RemoveStudentFromSubject implements Initializable {

    @FXML
    ListView<Student> studentssubject = new ListView<Student>();

    @FXML
    private Button remove;

    @FXML
    private Button back;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Student> list = FXCollections.observableArrayList();
        Subject selectedSubject = IndexAdmin.getSelectedSubject();
        list.addAll(selectedSubject.getStudents());
        studentssubject.setItems(list);

        remove.setOnAction(event -> {
            Student stud = studentssubject.getSelectionModel().getSelectedItem();
            DAOSubject dao = new DAOSubject();
            dao.removeStudentFromSubject(stud, selectedSubject);
            studentssubject.getItems().remove(stud);
        });

        back.setOnAction(event -> {
            Stage st =  (Stage) back.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/indexadmin.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
