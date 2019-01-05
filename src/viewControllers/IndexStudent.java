package viewControllers;

import entities.Student;
import entities.Subject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexStudent implements Initializable {

    @FXML
    private ListView<Subject> subjects;

    @FXML
    private Button logout;

    @FXML
    private Button updateinfo;

    @FXML
    private Button showinfo;

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        ObservableList<Subject> observableList = FXCollections.observableArrayList();
        observableList.addAll(student.getSubjects());
        subjects.setItems(observableList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        subjects.getSelectionModel().selectedItemProperty().addListener((observable, oldClickedSubject, clickedSubject) -> {
            Stage st =  (Stage) logout.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/subjectstudent.fxml"));
            Region root;

            try {
                root = loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                SubjectStudent subjectStudent = loader.getController();
                subjectStudent.setData(clickedSubject, student);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logout.setOnAction(event -> {
            Stage st =  (Stage) logout.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/auth.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                Auth auth = loader.getController();

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
