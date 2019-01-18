package View;

import Model.Absence;
import Model.Grade;
import Model.Student;
import Model.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubjectStudent implements Initializable {
    @FXML
    private ListView<Grade> grades;

    @FXML
    private ListView<Absence> absences;

    @FXML
    private Label name;

    @FXML
    private Button back;
    @FXML
    private Button help;


    private Student student;

    private Subject subject;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setData(Subject subject, Student student){
        this.subject = subject;
        this.student = student;

        ObservableList<Grade> observableList = FXCollections.observableArrayList();
        observableList.addAll(subject.getGrades(student));
        grades.setItems(observableList);

        ObservableList<Absence> observableListAbsences = FXCollections.observableArrayList();
        observableListAbsences.addAll(subject.getAbsences(student));
        absences.setItems(observableListAbsences);

        this.name.setText(subject.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        help.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/indexHelp.fxml"));
            IndexHelp help = new IndexHelp();
            loader.setController(help);
            try {

                Scene scene = new Scene(loader.load(), 800, 600);
                Stage stage = new Stage();
                stage.setTitle("Help");
                stage.setScene(scene);
                help.setPage("subjectstudent");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
}
