package viewControllers;

import entities.Admin;
import entities.Student;
import entities.Teacher;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logicControllers.AuthController;
import logicControllers.Users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Auth implements Initializable{
    @FXML
    private Button sendAuthInfo;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AuthController authController = new AuthController();
        Users users = new Users();
        sendAuthInfo.setOnAction(event -> {
            String authUsername = authController.auth(this.username.getText(), this.password.getText());
            if(!authUsername.equals("")){
                User user = users.getUser(authUsername);

                FXMLLoader loader = null;
                Stage st =  (Stage) sendAuthInfo.getScene().getWindow();
                if(user instanceof Admin){
                    loader = new FXMLLoader(getClass().getResource("../views/indexadmin.fxml"));
                }else if(user instanceof Teacher){
                    loader = new FXMLLoader(getClass().getResource("../views/indexteacher.fxml"));
                }else if (user instanceof Student){
                    loader = new FXMLLoader(getClass().getResource("../views/indexstudent.fxml"));

                }

                Region root;

                try {
                    root =loader.load();

                    Scene scene = new Scene(root);
                    st.setScene(scene);

                    if(user instanceof Student){
                        IndexStudent indexStudent = loader.getController();
                        indexStudent.setStudent((Student) user);
                    }else if(user instanceof Teacher){
                        IndexTeacher indexTeacher = loader.getController();
                        indexTeacher.setTeacher((Teacher) user);
                    }

                    st.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
