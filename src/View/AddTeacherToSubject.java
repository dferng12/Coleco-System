package View;

import Model.Subject;
import Model.Teacher;
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
import Controller.Subjects;
import Controller.Teachers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTeacherToSubject implements Initializable {

    @FXML
    private ListView<Teacher> listteachers;

    @FXML
    private ListView<Subject> listsubjects;

    @FXML
    private Button add;

    @FXML
    private Button help;

    private Teacher teacher;

    private Subject subject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Teachers teachers = new Teachers();
        ObservableList<Teacher> observableList = FXCollections.observableArrayList();
        observableList.addAll(teachers.getAllTeachers());
        listteachers.setItems(observableList);

        Subjects subjects = new Subjects();
        ObservableList<Subject> subjectObservableList = FXCollections.observableArrayList();
        subjectObservableList.addAll(subjects.getAllSubjects());
        listsubjects.setItems(subjectObservableList);

        listteachers.getSelectionModel().selectedItemProperty().addListener((observable, oldclieckedTeacher, clieckedTeacher) -> this.teacher = clieckedTeacher);

        listsubjects.getSelectionModel().selectedItemProperty().addListener((observable, oldClickedSubject, clickedSubject) -> this.subject = clickedSubject);

        add.setOnAction(actionEvent -> {
            subjects.addTeacherToSubject(this.teacher, this.subject);

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
                help.setPage("addteachertosubject");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
}
