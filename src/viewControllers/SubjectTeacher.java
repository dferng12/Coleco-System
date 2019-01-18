package viewControllers;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logicControllers.Absences;
import logicControllers.Grades;
import logicControllers.Students;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class SubjectTeacher implements Initializable {

    @FXML
    private ListView<Student> students;

    @FXML
    private Button addgrade;

    @FXML
    private Button addabsence;

    @FXML
    private Button finalgrade;

    @FXML
    private Button showinfo;

    @FXML
    private Label name;

    @FXML
    private Label finalgradevalue;

    @FXML
    private Button back;

    @FXML
    private Button help;


    @FXML
    private TextField value;

    @FXML
    private TextField percentage;

    @FXML
    private TextField reason;

    @FXML
    private TextField penalty;

    @FXML
    private DatePicker date;

    private Teacher teacher;

    private Subject subject;

    private Student selectedStudent;

    public void setData(Subject subject, Teacher teacher){
        this.subject = subject;
        this.teacher = teacher;

        ObservableList<Student> observableList = FXCollections.observableArrayList();
        observableList.addAll(subject.getStudents());
        students.setItems(observableList);

        this.name.setText(subject.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        students.getSelectionModel().selectedItemProperty().addListener((observable, oldClickedStudent, clickedStudent) -> {
            this.selectedStudent = clickedStudent;
        });

        showinfo.setOnAction(actionEvent -> {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/subjectstudent.fxml"));
            Region root;

            try {
                root = loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                SubjectStudent subjectStudent = loader.getController();
                subjectStudent.setData(this.subject, this.selectedStudent);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addgrade.setOnAction(actionEvent -> {
            if(this.value.getText().equals("") || this.percentage.getText().equals("")) return;
            Grades grades = new Grades();
            Grade grade = new Grade(Integer.parseInt(this.value.getText()), Integer.parseInt(this.percentage.getText()));
            grades.addGrade(subject,selectedStudent,grade);
        });

        addabsence.setOnAction(actionEvent -> {
            if(this.reason.getText().equals("") || this.penalty.getText().equals("")) return;

            Absences absences = new Absences();
            Instant instant = Instant.from(this.date.getValue().atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            Absence absence = new Absence(date, this.reason.getText(), Integer.parseInt(this.penalty.getText()));
            absences.addAbsence(subject, selectedStudent, absence);

        });

        finalgrade.setOnAction(actionEvent -> {
            double finalGrade = subject.computeGrade(selectedStudent);
            this.finalgradevalue.setText(String.valueOf(finalGrade));
        });

        back.setOnAction(event -> {
            Stage st =  (Stage) back.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/indexteacher.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                IndexTeacher indexController = loader.getController();
                indexController.setTeacher(this.teacher);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        help.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/indexHelp.fxml"));
            IndexHelp help = new IndexHelp();
            loader.setController(help);
            try {

                Scene scene = new Scene(loader.load(), 800, 600);
                Stage stage = new Stage();
                stage.setTitle("Help");
                stage.setScene(scene);
                help.setPage("subjectteacher");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
}
