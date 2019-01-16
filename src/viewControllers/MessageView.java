package viewControllers;

import entities.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logicControllers.MailCenter;
import logicControllers.Users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageView implements Initializable {

    private User user;

    @FXML
    private TextField who;

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

    private Message message;


    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setData(Message message, User user){

        this.message = message;
        this.user = user;

        who.setText(message.getSender().toString());

        subject.setText(message.getSubject());

        text.setText(message.getText());
        send.setVisible(false);
    }
    public void setUser(User user){
        this.user = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                messages.setUser(user);
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
            Message message = new Message(subject.getText(), text.getText());
            MailCenter mail = MailCenter.getInstance();
            Users users = new Users();

            User receiver = users.getUser(who.getText());
            mail.addUser(user);
            mail.addUser(receiver);
            message.setReceiver(receiver);
            message.setSender(user);
            mail.sendMessage(message);

        });
    }
}
