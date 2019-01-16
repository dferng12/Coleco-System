package viewControllers;

import entities.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import logicControllers.MailCenter;
import logicControllers.Students;
import logicControllers.Subjects;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Messages implements Initializable {

    @FXML
    private ListView<Message> messages;

    @FXML
    private Button newMessage;

    @FXML
    private Button help;

    private Inbox inbox;
    private User user;
    public Inbox getInbox() {
        return inbox;
    }

    public void setUser(User user) {
        this.user = user;
        MailCenter mail = MailCenter.getInstance();
        mail.addUser(user);
        ObservableList<Message> observableList = FXCollections.observableArrayList();
        observableList.addAll(mail.getMessages(user));
        messages.setItems(observableList);
        if(user instanceof Student){
            newMessage.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messages.getSelectionModel().selectedItemProperty().addListener((observable, oldClickedSubject, clickedMessage) -> {
            Stage st =  (Stage) messages.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/messageView.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);

                MessageView messageView = loader.getController();

                messageView.setData(clickedMessage, user);
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
                help.setPage("messages");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        newMessage.setOnAction(event -> {
            Stage st =  (Stage) newMessage.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/messageView.fxml"));
            Region root;

            try {
                root =loader.load();

                Scene scene = new Scene(root);
                st.setScene(scene);
                MessageView messageView = loader.getController();

                messageView.setUser(user);


                st.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });


    }
}
