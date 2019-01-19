package View;

import Controller.DAOS.DAOAuth;
import Model.AuthInfo;
import Model.Student;
import Model.Teacher;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePassword implements Initializable {

    @FXML
    private Button change;

    @FXML
    private PasswordField changepassword;

    @FXML
    private PasswordField changepasswordrepeat;

    @FXML
    private Button back;

    @FXML
    private Button help;

    private User user;

    public void setUser(User user){
        this.user = user;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        change.setOnAction(event -> {
            if(changepassword.getText().equals("") || changepasswordrepeat.getText().equals("")){ //Si alguno de los campos no ha sido rellenado
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setTitle("Missing information");
                dialog.setContentText("You have not properly filled the password fields.");
                dialog.setHeaderText(null);
                dialog.showAndWait();

                changepassword.setText("");
                changepasswordrepeat.setText("");
            }else{
                String passText1 = changepassword.getText();
                String passText2 = changepasswordrepeat.getText();
                if(!passText1.equals(passText2)){ //Si los dos campos de password no coinciden
                    Alert dialog = new Alert(Alert.AlertType.ERROR);
                    dialog.setTitle("Error");
                    dialog.setContentText("The introduced passwords do not match.");
                    dialog.setHeaderText(null);
                    dialog.showAndWait();

                    changepassword.setText("");
                    changepasswordrepeat.setText("");
                }else{ //Si ambas passwords coinciden
                    if(this.user != null){
                        AuthInfo newAuthInfo = new AuthInfo(this.user.getAuthInfo().getUser(), changepassword.getText());
                        this.user.setAuthInfo(newAuthInfo);
                        new DAOAuth().updateAuthInfo(newAuthInfo);

                        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                        dialog.setTitle("Password successfully changed.");
                        dialog.setContentText("The new password has been successfully changed.");
                        dialog.setHeaderText(null);
                        dialog.showAndWait();

                        changepassword.setText("");
                        changepasswordrepeat.setText("");
                    }
                }
            }

        });

        back.setOnAction(event -> {
            if(Auth.getSelectedUser() instanceof Student){
                Stage st =  (Stage) changepassword.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/indexstudent.fxml"));
                Region root;

                try {
                    root =loader.load();
                    Scene scene = new Scene(root);
                    st.setScene(scene);
                    IndexStudent indexController = loader.getController();
                    indexController.setStudent((Student) user);

                    st.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(Auth.getSelectedUser() instanceof Teacher){
                Stage st =  (Stage) changepassword.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/indexteacher.fxml"));
                Region root;
                try {
                    root =loader.load();
                    Scene scene = new Scene(root);
                    st.setScene(scene);
                    IndexTeacher indexController = loader.getController();
                    indexController.setTeacher((Teacher) user);
                    st.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                help.setPage("changepassword");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
