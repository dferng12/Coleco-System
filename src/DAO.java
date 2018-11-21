import org.apache.log4j.Logger;

import java.sql.*;

public class DAO {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public boolean connectionStatus = false;
    private static Logger logger = Logger.getLogger(DAO.class);

    public DAO(){
        connectionStatus = connect();
        logger.warn("CONNECTED");
    }

    private boolean connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ColecoSystem", "colecouser", "colecopass");
            return connection.isValid(50000);
        }catch (ClassNotFoundException | SQLException e){
            return false;
        }
    }
}
