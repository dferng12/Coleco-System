package View;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Controller.AuthController;
import Controller.Users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Auth implements Initializable{
    @FXML
    private Button sendAuthInfo;
    @FXML
    private Button help;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private static User selectedUser;

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
                    loader = new FXMLLoader(getClass().getResource("/views/indexadmin.fxml"));
                }else if(user instanceof Teacher){
                    loader = new FXMLLoader(getClass().getResource("/views/indexteacher.fxml"));
                    selectedUser = user;
                }else if (user instanceof Student){
                    loader = new FXMLLoader(getClass().getResource("/views/indexstudent.fxml"));
                    selectedUser = user;
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
                    }else if(user instanceof Admin){
                        IndexAdmin indexadmin = loader.getController();
                        indexadmin.setAdmin(user);
                    }

                    st.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Error");
                dialog.setContentText("Username or password introduced are not correct, please try again.");
                dialog.setHeaderText(null);
                dialog.showAndWait();
                password.setText("");
            }
        });
        help.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/indexHelp.fxml"));
            IndexHelp help = new IndexHelp();
            loader.setController(help);
            try {

                Scene scene = new Scene(loader.load(), 800, 600);
                Stage stage = new Stage();
                stage.setTitle("Help");
                stage.setScene(scene);
                help.setPage("auth");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    public static User getSelectedUser(){
        return selectedUser;
    }
}
