package entities;

import java.util.ArrayList;
import java.util.List;

public class Inbox {
    private List<Message> messages;


    public List<Message> getMessages() {
        return messages;
    }


    public Inbox(){
        this.messages = new ArrayList<>();
    }


    public void addMessage(Message message){
        this.messages.add(message);
    }
}
