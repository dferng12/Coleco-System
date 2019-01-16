package viewControllers;

import entities.Student;
import entities.Subject;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

public class IndexAdmin implements Initializable{

    @FXML
    private ListView<Student> liststudents;

    @FXML
    private ListView<Subject> listsubjects;

    @FXML
    private Button addstudenttosubject;

    @FXML
    private Button addsubject;

    @FXML
    private Button addstudent;

    @FXML
    private Button addteacher;

    @FXML
    private Button addteachertosubject;

    @FXML
    private Button logout;

    @FXML
    private Button messages;

    private User admin;

    public void setAdmin(User admin) {
        this.admin = admin;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Students students = new Students();
        ObservableList<Student> observableList = FXCollections.observableArrayList();
        observableList.addAll(students.getAllStudents());
        liststudents.setItems(observableList);

        Subjects subjects = new Subjects();
        ObservableList<Subject> subjectObservableList = FXCollections.observableArrayList();
        subjectObservableList.addAll(subjects.getAllSubjects());
        listsubjects.setItems(subjectObservableList);

        addstudent.setOnAction(event -> {
            Stage st =  (Stage) addstudent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addstudent.fxml"));
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

        addsubject.setOnAction(event -> {
            Stage st =  (Stage) addstudent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addsubject.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                AddSubject indexController = loader.getController();

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addstudenttosubject.setOnAction(actionEvent -> {
            Stage st =  (Stage) addstudenttosubject.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addstudenttosubject.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                AddStudentToSubject controller = loader.getController();

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addteacher.setOnAction(actionEvent -> {
            Stage st =  (Stage) addstudent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addteacher.fxml"));
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

        addteachertosubject.setOnAction(actionEvent -> {
            Stage st =  (Stage) addstudent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addteachertosubject.fxml"));
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
        messages.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/messages.fxml"));
            Messages messages = new Messages();
            loader.setController(messages);
            try {


                Scene scene = new Scene(loader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Inbox");
                stage.setScene(scene);
                messages.setUser(admin);
                stage.show();
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
