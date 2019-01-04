package logicControllers;

import entities.Inbox;
import entities.Message;
import entities.User;
import logicControllers.DAOS.DAOMessages;

import java.util.HashMap;
import java.util.List;

public class MailCenter {
    private HashMap<User, Inbox> users;
    private static MailCenter singleton;
    DAOMessages daoMessages;

    private MailCenter(){
        this.users = new HashMap<>();
        this.daoMessages = new DAOMessages();
    }

    public static MailCenter getInstance(){
        if(singleton == null){
            singleton = new MailCenter();
        }

        return singleton;
    }

    public void addUser(User user){
        singleton.users.computeIfAbsent(user, k -> new Inbox());
        singleton.users.put(user, daoMessages.getMessagesFromUser(user));
    }

    public void sendMessage(Message message){
        singleton.users.get(message.getReceiver()).addMessage(message);
        daoMessages.addMessage(message, message.getSender(), message.getReceiver());
    }

    public List<Message> getMessages(User user){
        return singleton.users.get(user).getMessages();
    }
}
