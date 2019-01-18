package View;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Message implements Initializable{
    @FXML
    private Label subject;

    @FXML
    private Label text;

    @FXML
    private Label from;

    public void setData(Model.Message message){
        this.subject.setText(message.getSubject());
        this.text.setText(message.getText());
        this.from.setText(message.getSender().getName() +" "+message.getSender().getSubname());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
