package View;

import Controller.Students;
import Controller.Subjects;
import Model.Student;
import Model.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Controller.DAOS.DAOSubject;

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

    @FXML
    private Button help;

    private Subject subject;

    public void setSubject(Subject subject){
        this.subject = subject;
        ObservableList<Student> list = FXCollections.observableArrayList();
        list.addAll(subject.getStudents());
        studentssubject.setItems(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        remove.setOnAction(event -> {
            Student stud = studentssubject.getSelectionModel().getSelectedItem();
            Subjects subjects = new Subjects();

            if(stud != null){
                subjects.removeStudentFromSubject(stud, this.subject);
                studentssubject.getItems().remove(stud);
            }
        });

        back.setOnAction(event -> {
            Stage st =  (Stage) back.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/indexadmin.fxml"));
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
        help.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/indexHelp.fxml"));
            IndexHelp help = new IndexHelp();
            loader.setController(help);
            try {

                Scene scene = new Scene(loader.load(), 800, 600);
                Stage stage = new Stage();
                stage.setTitle("Help");
                stage.setScene(scene);
                help.setPage("removestudentfromsubject");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
