package logicControllers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAuth extends DAO {

    public boolean auth(String username, String password){
        String query = "SELECT COUNT(*) FROM userauth WHERE username=\""+username+"\" and password=\""+password + "\"";
        ResultSet result = execQuery(query);

        if(result == null) return false;

        try {
            if(result.next()){
                return result.getInt(1) == 1;
            }
        } catch (SQLException e) {
            logger.error("ERROR IN SQL QUERY");
            logger.debug(e.getMessage());
        }

        return false;
    }
}
