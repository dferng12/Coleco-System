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
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentInfo implements Initializable {



    @FXML
    private TextField name;
    @FXML
    private TextField subname;
    @FXML
    private TextField dni;
    @FXML
    private TextField userName;
    @FXML
    private TextField grade;
    @FXML
    private Button back;
    @FXML
    private ListView<Subject> subjects;
    @FXML
    private Button help;

    private Student student;

    public void setData(Student student) {
        this.student = student;
        name.setText(student.getName());
        subname.setText(student.getSubname());
        dni.setText(student.getDni().toString());
        userName.setText(student.getAuthInfo().getUser());
        ObservableList<Subject> observableList = FXCollections.observableArrayList();
        observableList.addAll(student.getSubjects());
        subjects.setItems(observableList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subjects.getSelectionModel().selectedItemProperty().addListener((observable, oldClickedSubject, clickedSubject) -> {

            grade.setText(String.valueOf(clickedSubject.computeGrade(student)));

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
                help.setPage("studentinfo");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        back.setOnAction(event -> {
            Stage st =  (Stage) back.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/indexstudent.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                IndexStudent indexController = loader.getController();
                indexController.setStudent(student);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
