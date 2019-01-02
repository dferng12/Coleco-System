package viewControllers;

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
            if(authController.auth(this.username.getText(), this.password.getText())){
                status.setText("PASSED");
                if(userController.identify(this.username.getText()).equals("admin")){
                    System.out.print("ADMIN IDENTIFY");
                }else if(userController.identify(this.username.getText()).equals("teacher")){
                    System.out.print("TEACHER IDENTIFY");
                }else if(userController.identify(this.username.getText()).equals("student")){
                    System.out.print("STUDENT IDENTIFY");
                }else{
                    System.out.print("ROL ERROR");
                }

                    Stage st =  (Stage) sendAuthInfo.getScene().getWindow();
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
