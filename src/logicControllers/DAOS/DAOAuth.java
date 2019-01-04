package logicControllers.DAOS;

import entities.AuthInfo;
import logicControllers.AuthController;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAuth extends DAO {

    public DAOAuth() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS userauth( "+
                "username varchar(50) PRIMARY KEY," +
                "password varchar(260));";

        executeUpdate(sqlCreate);
    }

    public String auth(String username, String password){
        String query = "SELECT * FROM userauth WHERE username=\""+username+"\" and password=\""+password + "\"";
        ResultSet result = execQuery(query);

        if(result == null) return "";

        try {
            if(result.next()){
                return result.getString("username");
            }
        } catch (SQLException e) {
            logger.error("ERROR IN SQL QUERY");
            logger.debug(e.getMessage());
        }

        return "";
    }

    public void addAuthInfo(AuthInfo authInfo) {
        String query = "INSERT INTO userauth(username,password) VALUES (\"" + authInfo.getUser() + "\", \"" + AuthController.hash(authInfo.getPasswd()) + "\");";

        executeUpdate(query);
    }

    public void createAdmin(){

        String adminExists = "SELECT COUNT(*) AS total FROM userauth";
        String adminCreate= "INSERT INTO userauth VALUES('colecoadmin', '7b8956fc49b3c4e92f5761b2bb503a548640537d1abb7b7c1e2f7fdf7df40995');";
        String userCreate = "INSERT INTO admins(dni, name, subname, email, auth) VALUES ('71474345M', 'Coleco', 'System', 'coleco@coleco.com', 'colecoadmin');";
        logger.debug("colecoadmin and passadmin new auxiliar admin keys");
        try {
            ResultSet result = execQuery(adminExists);
            result.next();
            int total = result.getInt("total");
            if (total == 0) {
                executeUpdate(adminCreate);
                executeUpdate(userCreate);
            }else{
                logger.debug("colecoadmin EXISTS YET");
            }
        } catch (SQLException e) {
            logger.error("ERROR IN SQL QUERY");
            logger.debug(e.getMessage());
        }
    }
}
