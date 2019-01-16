package viewControllers;

import entities.Subject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logicControllers.Subjects;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSubject implements Initializable {
    @FXML
    private Button create;

    @FXML
    private Button help;

    @FXML
    private TextField subjectname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create.setOnAction(actionEvent -> {
            Subjects subjects = new Subjects();
            Subject subject = new Subject(subjectname.getText());
            subjects.addSubject(subject);

            Stage st =  (Stage) create.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/indexadmin.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

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
                help.setPage("addsubject");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
