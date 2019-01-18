package View;

import Model.AuthInfo;
import Model.DNI;
import Model.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Controller.MailCenter;
import Controller.Teachers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTeacher implements Initializable{

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
    private Button help;


    @FXML
    private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create.setOnAction(event -> {
            Teachers teachers = new Teachers();
            Teacher teacher = new Teacher();

            teacher.setDni(DNI.createDNI(dni.getText()));
            teacher.setName(name.getText());
            teacher.setSubname(subname.getText());
            AuthInfo authInfo = new AuthInfo(username.getText(), password.getText());
            teacher.setAuthInfo(authInfo);
            MailCenter mail = MailCenter.getInstance();
            teachers.addTeacher(teacher);

            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Teacher created");
            dialog.setContentText("The teacher "+name.getText()+" with DNI: "+dni.getText()+" has been successfully created.");
            dialog.setHeaderText(null);
            dialog.showAndWait();
            username.setText("");
            password.setText("");
            dni.setText("");
            name.setText("");
            subname.setText("");
            username.setText("");
            password.setText("");
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
        help.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/indexHelp.fxml"));
            IndexHelp help = new IndexHelp();
            loader.setController(help);
            try {

                Scene scene = new Scene(loader.load(), 800, 600);
                Stage stage = new Stage();
                stage.setTitle("Help");
                stage.setScene(scene);
                help.setPage("addteacher");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
