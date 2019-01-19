package View;

import Model.Student;
import Model.Subject;
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
import Controller.Students;
import Controller.Subjects;

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

    @FXML
    private Button help;
    @FXML
    private Button back;

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
            student.getSubjects();
            Stage st =  (Stage) add.getScene().getWindow();
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
                help.setPage("addstudenttsubject");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
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

                IndexAdmin indexController = loader.getController();

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
}
