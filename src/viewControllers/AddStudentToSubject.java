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
import logicControllers.Students;
import logicControllers.Subjects;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentToSubject implements Initializable {

    @FXML
    private ListView<Student> liststudents;

    @FXML
    private ListView<Subject> listsubjects;

    @FXML
    private Button add;

    private Student student;

    private Subject subject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Students students = new Students();
        ObservableList<Student> observableList = FXCollections.observableArrayList();
        observableList.addAll(students.getAllStudents());
        liststudents.setItems(observableList);

        Subjects subjects = new Subjects();
        ObservableList<Subject> subjectObservableList = FXCollections.observableArrayList();
        subjectObservableList.addAll(subjects.getAllSubjects());
        listsubjects.setItems(subjectObservableList);

        liststudents.getSelectionModel().selectedItemProperty().addListener((observable, oldclickedStudent, clickedStudent) -> this.student = clickedStudent);

        listsubjects.getSelectionModel().selectedItemProperty().addListener((observable, oldClickedSubject, clickedSubject) -> this.subject = clickedSubject);

        add.setOnAction(actionEvent -> {
            subjects.addStudentToSubject(this.subject, this.student);

            Stage st =  (Stage) add.getScene().getWindow();
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
