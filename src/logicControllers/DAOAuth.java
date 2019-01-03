package logicControllers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAuth extends DAO {

    public DAOAuth() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS userauth "
                + "(id INT AUTO_INCREMENT PRIMARY KEY," +
                "username varchar(50)," +
                "password varchar(260));";

        executeUpdate(sqlCreate);
        String adminExists = "SELECT COUNT(*) AS total FROM userauth";
        String adminCreate = "INSERT INTO userauth VALUES ( '1', 'colecoadmin' , '7b8956fc49b3c4e92f5761b2bb503a548640537d1abb7b7c1e2f7fdf7df40995');";
        logger.debug("colecoadmin and passadmin new auxiliar admin keys");
        try {
            ResultSet result = execQuery(adminExists);
            result.next();
            int total = result.getInt("total");
            if (total == 0) {
                executeUpdate(adminCreate);
            }else{
                logger.debug("colecoadmin EXISTS YET");
            }
        } catch (SQLException e) {
            logger.error("ERROR IN SQL QUERY");
            logger.debug(e.getMessage());
        }
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
