package viewControllers;

import entities.AuthInfo;
import entities.DNI;
import entities.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logicControllers.Students;
import logicControllers.Users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddStudent implements Initializable{

    @FXML
    private TextField name;

    @FXML
    private TextField dni;

    @FXML
    private TextField subname;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button create;

    @FXML
    private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create.setOnAction(event -> {
            Students students = new Students();
            Student student = new Student();

            student.setDni(DNI.createDNI(dni.getText()));
            student.setName(name.getText());
            student.setSubname(subname.getText());
            AuthInfo authInfo = new AuthInfo(username.getText(), password.getText());
            student.setAuthInfo(authInfo);

            students.addStudent(student);
        });

        back.setOnAction(event -> {
            Stage st =  (Stage) back.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/indexadmin.fxml"));
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
