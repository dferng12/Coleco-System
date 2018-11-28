package viewControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logicControllers.AuthController;

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
        sendAuthInfo.setOnAction(event -> {
            if(authController.auth(this.username.getText(), this.password.getText())){
                status.setText("PASSED");
            }
        });
    }
}
