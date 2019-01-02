package logicControllers;

import entities.Inbox;
import entities.Message;
import entities.User;
import java.util.HashMap;
import java.util.List;

public class MailCenter {
    private HashMap<User, Inbox> users;
    private static MailCenter singleton;

    private MailCenter(){
        this.users = new HashMap<>();
    }

    public static MailCenter getInstance(){
        if(singleton == null){
            singleton = new MailCenter();
        }

        return singleton;
    }

    public void addUser(User user){
        singleton.users.computeIfAbsent(user, k -> new Inbox());
    }

    public void sendMessage(User user, Message message){
        singleton.users.get(user).addMessage(message);
    }

    public List<Message> getMessages(User user){
        return singleton.users.get(user).getMessages();
    }
}
