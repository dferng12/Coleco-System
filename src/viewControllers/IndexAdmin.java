package viewControllers;

import entities.Student;
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
import logicControllers.Users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexAdmin implements Initializable{

    @FXML
    private ListView<Student> listsubjects;

    @FXML
    private Button addsubject;

    @FXML
    private Button addstudent;

    @FXML
    private Button logout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Students students = new Students();
        ObservableList<Student> observableList = FXCollections.observableArrayList();

        observableList.addAll(students.getAllStudents());
        listsubjects.setItems(observableList);

        addstudent.setOnAction(event -> {
            Stage st =  (Stage) addstudent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/addstudent.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                AddStudent indexController = loader.getController();

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
