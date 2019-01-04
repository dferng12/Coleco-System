package entities;

import java.util.ArrayList;
import java.util.List;

public class Inbox {
    private List<Message> messages;
    private User user;


    public Inbox(){
        this.messages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }
    public void addMessage(Message message){
        this.messages.add(message);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
