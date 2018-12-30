package logicControllers;

import entities.User;
import org.apache.log4j.Logger;
import java.sql.*;

public abstract class DAO {
    protected Connection connection = null;
    protected boolean connectionStatus;
    protected static Logger logger = Logger.getLogger(DAO.class);

    public DAO(){
        connectionStatus = connect();
        if(connectionStatus) logger.warn("CONNECTED");
    }

    public ResultSet execQuery(String query){
        try {
            logger.debug("EXECUTING QUERY: " +  query);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);

        } catch (SQLException e) {
            logger.warn("SQL QUERY FAILED: " + e.getMessage());
            return null;
        }
    }

    public User getUser(String DNI){
        return null;
    }


    private boolean connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ColecoSystem", "colecouser", "colecopass");
            return connection.isValid(50000);
        }catch (ClassNotFoundException | SQLException e){
            logger.warn("FAILED TO CONNECT TO DB: " + e.getMessage());

            return false;
        }
    }

    public boolean getConnectionStatus(){
        return connectionStatus;
    }
}
