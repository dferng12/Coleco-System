package View;

import Controller.DAOS.DAOAuth;
import Model.AuthInfo;
import Model.Student;
import Model.Teacher;
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
                    if(Auth.getSelectedUser() != null){
                        if(Auth.getSelectedUser() instanceof Student){ //Si es un alumno.
                            DAOAuth dao = new DAOAuth();
                            Student selectedStudent = (Student) Auth.getSelectedUser();
                            AuthInfo authInfo = new AuthInfo(selectedStudent.getAuthInfo().getUser(), changepassword.getText());
                            selectedStudent.setAuthInfo(authInfo);
                            dao.updateAuthInfo(authInfo);
                        }else if(Auth.getSelectedUser() instanceof Teacher){ //Si es un profesor.
                            DAOAuth dao = new DAOAuth();
                            Teacher selectedTeacher = (Teacher) Auth.getSelectedUser();
                            AuthInfo authInfo = new AuthInfo(selectedTeacher.getAuthInfo().getUser(), changepassword.getText());
                            selectedTeacher.setAuthInfo(authInfo);
                            dao.updateAuthInfo(authInfo);
                        }
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

                    st.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
