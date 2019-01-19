package View;

import Model.Student;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Message implements Initializable{
    @FXML
    private Label subject;

    @FXML
    private Label text;

    @FXML
    private Label from;

    @FXML
    private Button back;

    @FXML
    private Button help;


    private User student;

    public void setData(Model.Message message){
        this.subject.setText(message.getSubject());
        this.text.setText(message.getText());
        this.from.setText(message.getSender().getName() +" "+message.getSender().getSubname());
        this.student = message.getReceiver();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnAction(event -> {
            Stage st =  (Stage) back.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/messages.fxml"));
            Region root;
            Messages messages = new Messages();
            loader.setController(messages);
            try {
                root =loader.load();
                Scene scene = new Scene(root);
                st.setScene(scene);
                messages.setUser(student);
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
                help.setPage("message");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}
