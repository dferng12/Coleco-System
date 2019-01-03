package viewControllers;

import entities.Admin;
import entities.Student;
import entities.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logicControllers.AuthController;
import logicControllers.DAO;
import logicControllers.UserController;

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
        UserController userController  = new UserController();
        sendAuthInfo.setOnAction(event -> {
            int authKey = authController.auth(this.username.getText(), this.password.getText());
            if(authKey != -1){

                Stage st =  (Stage) sendAuthInfo.getScene().getWindow();
                if(userController.getUser(authKey) instanceof Admin){
                    System.out.print("ADMIN");
                }else if(userController.getUser(authKey) instanceof Teacher){
                    System.out.print("TEACHER");
                }else if (userController.getUser(authKey) instanceof Student){
                    System.out.print("STUDENT");
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/index.fxml"));
                Region root;

                try {
                    root =loader.load();

                    Scene scene = new Scene(root);
                    st.setScene(scene);

                    Index indexController = loader.getController();

                    st.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
