package viewControllers;

import entities.Absence;
import entities.Grade;
import entities.Student;
import entities.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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

    }
}
