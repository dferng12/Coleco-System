package View;

import Model.Subject;
import Model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexTeacher implements Initializable {

    @FXML
    private ListView<Subject> subjects;

    @FXML
    private Button logout;

    @FXML
    private Button updateinfo;

    @FXML
    private Button showinfo;

    @FXML
    private Button help;

    @FXML
    private Button messages;

    @FXML
    private Button changepassword;

    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        ObservableList<Subject> observableList = FXCollections.observableArrayList();
        observableList.addAll(teacher.getSubjects());
        subjects.setItems(observableList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subjects.getSelectionModel().selectedItemProperty().addListener((observable, oldClickedSubject, clickedSubject) -> {
            Stage st =  (Stage) logout.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/subjectteacher.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                SubjectTeacher subjectTeacher = loader.getController();
                subjectTeacher.setData(clickedSubject, teacher);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        changepassword.setOnAction(event -> {
            Stage st =  (Stage) changepassword.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/changepassword.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                ChangePassword changePassword = loader.getController();
                changePassword.setUser(this.teacher);

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
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
                help.setPage("indexteacher");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        logout.setOnAction(event -> {
            Stage st =  (Stage) logout.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/auth.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                Auth auth = loader.getController();

                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        messages.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/messages.fxml"));
            Messages messages = new Messages();
            loader.setController(messages);
            try {


                Scene scene = new Scene(loader.load(), 700, 400);
                Stage stage = new Stage();
                stage.setTitle("Inbox");
                stage.setScene(scene);
                messages.setUser(teacher);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
