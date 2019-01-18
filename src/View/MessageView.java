package View;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Controller.MailCenter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageView implements Initializable {

    private User from;

    @FXML
    private ListView<User> liststudents;

    private User to;

    @FXML
    private TextField subject;

    @FXML
    private TextArea text;

    @FXML
    private Button back;
    @FXML
    private Button send;
    @FXML
    private Button help;

    public void setData(User user){
        this.from = user;

        ObservableList<User> observableList = FXCollections.observableArrayList();
        Teacher teacher = (Teacher) user;
        for(Subject subject: teacher.getSubjects()){
            observableList.addAll(subject.getStudents());
        }
        liststudents.setItems(observableList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        liststudents.getSelectionModel().selectedItemProperty().addListener((observable, oldclickedStudent, clickedStudent) -> this.to = clickedStudent);

        back.setOnAction(event -> {
            Stage st =  (Stage) back.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/messages.fxml"));
            Region root;
            Messages messages = new Messages();
            loader.setController(messages);
            try {
                root =loader.load();
                Scene scene = new Scene(root);
                st.setScene(scene);
                messages.setUser(from);
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
                help.setPage("messageview");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        send.setOnAction(event -> {
            if(to != null && !subject.getText().equals("") && !text.getText().equals("")){
                Model.Message message = new Model.Message(subject.getText(), text.getText());
                MailCenter mail = MailCenter.getInstance();

                User receiver = to;
                mail.addUser(from);
                mail.addUser(receiver);
                message.setReceiver(receiver);
                message.setSender(from);
                mail.sendMessage(message);
            }
        });
    }
}
