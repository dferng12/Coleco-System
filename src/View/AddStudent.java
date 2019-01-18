package View;

import Model.AuthInfo;
import Model.DNI;
import Model.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Controller.Students;

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

    @FXML
    private Button help;

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

            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Student created");
            dialog.setContentText("The student "+name.getText()+" with DNI: "+dni.getText()+" has been successfully created.");
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
                help.setPage("addstudent");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
