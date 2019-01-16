package logicControllers.DAOS;

import entities.Inbox;
import entities.Message;
import entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOMessages extends DAO{

    public void init(){
        String query = "CREATE TABLE IF NOT EXISTS messages(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "subject varchar(200) NOT NULL," +
                "text varchar(1000)," +
                "sender varchar(9)," +
                "receiver varchar(9));";

        executeUpdate(query);
    }

    public void fk(){
        String query = "ALTER TABLE messages ADD CONSTRAINT fk_messages_sender FOREIGN KEY (sender) REFERENCES teachers(dni)";
        executeUpdate(query);

        query = "ALTER TABLE messages ADD CONSTRAINT fk_messages_receiver FOREIGN KEY (receiver) REFERENCES students(dni)";
        executeUpdate(query);
    }

    public void addMessage(Message message, User sender, User receiver){
        String query = "INSERT INTO messages(subject, text, sender, receiver) VALUES ('" + message.getSubject() + "','" + message.getText() + "','" + sender.getDni().toString() + "','" + receiver.getDni().toString() + "');";

        executeUpdate(query);
    }

    public Inbox getMessagesFromUser(User user){
        String query = "SELECT subject, text, teachers.auth as sender FROM messages INNER JOIN teachers " +
                "ON teachers.dni = messages.sender WHERE messages.receiver = '" + user.getDni().toString() + "';";
        ResultSet resultSet = execQuery(query);
        DAOUser daoUser = new DAOUser();

        if(resultSet == null){
            Inbox inbox = new Inbox();
            inbox.setUser(user);
            return inbox;
        }

        try {
            Inbox inbox = new Inbox();
            while(resultSet.next()){
                Message message = new Message(resultSet.getString("subject"), resultSet.getString("text"));
                message.setReceiver(user);
                message.setSender(daoUser.getUser(resultSet.getString("sender")));
                inbox.addMessage(message);
            }

            return inbox;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Inbox inbox = new Inbox();
        inbox.setUser(user);
        return inbox;
    }
}
