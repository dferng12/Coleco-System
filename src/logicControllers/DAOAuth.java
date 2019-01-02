package logicControllers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAuth extends DAO {

    public DAOAuth(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS userauth "
                        + "(id INT AUTO_INCREMENT PRIMARY KEY," +
                        "username varchar(50)," +
                        "password varchar(260)";

        execQuery(sqlCreate);
    }

    public int auth(String username, String password){
        String query = "SELECT * FROM userauth WHERE username=\""+username+"\" and password=\""+password + "\"";
        ResultSet result = execQuery(query);

        if(result == null) return -1;

        try {
            if(result.next()){
                return result.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("ERROR IN SQL QUERY");
            logger.debug(e.getMessage());
        }

        return -1;
    }
}
