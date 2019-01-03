package logicControllers;

import entities.Admin;
import entities.Student;
import entities.Teacher;
import entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser extends DAO {

    public DAOUser() {
        String query = "CREATE TABLE IF NOT EXISTS students (" +
                "username VARCHAR(20) NOT NULL," +
                "name VARCHAR(20) NOT NULL," +
                "subname VARCHAR(45) NOT NULL," +
                "dni VARCHAR(9) NOT NULL," +
                "age INT NOT NULL," +
                "email VARCHAR(50) NOT NULL," +
                "phoneNumber CHAR(10) NOT NULL," +
                "address VARCHAR(100) NOT NULL," +
                "auth INT," +
                "PRIMARY KEY(dni)," +
                "FOREIGN KEY (auth)" +
                "REFERENCES userauth(id)" +
                "ON DELETE CASCADE);";

        executeUpdate(query);

        query = "CREATE TABLE IF NOT EXISTS teachers (" +
                "username VARCHAR(20) NOT NULL," +
                "name VARCHAR(20) NOT NULL," +
                "subname VARCHAR(45) NOT NULL," +
                "dni VARCHAR(9) NOT NULL," +
                "age INT NOT NULL," +
                "auth INT," +
                "email VARCHAR(50) NOT NULL," +
                "phoneNumber CHAR(10) NOT NULL," +
                "address VARCHAR(100) NOT NULL," +
                "PRIMARY KEY(dni)," +
                "FOREIGN KEY (auth)" +
                "REFERENCES userauth(id)" +
                "ON DELETE CASCADE);";

        executeUpdate(query);

        query = "CREATE TABLE IF NOT EXISTS admins (" +
                "username VARCHAR(20) PRIMARY KEY," +
                "auth INT," +
                "FOREIGN KEY (auth)" +
                "REFERENCES userauth(id)" +
                "ON DELETE CASCADE);";

        executeUpdate(query);
    }

    public User getUser(int authIndex){
        String query = "SELECT * FROM students INNER JOIN userauth ON students.auth = userauth.id WHERE auth = " + String.valueOf(authIndex);
        ResultSet result = execQuery(query);
        User user = null;

        if(result != null){
            try {
                if(result.next()){
                    user = new Student();
                    //Rellenar con la info recibida
                    return user;

                }
            } catch (SQLException e) {
                logger.error("ERROR IN SQL QUERY");
                logger.debug(e.getMessage());
            }
        }

        query = "SELECT * FROM teachers INNER JOIN userauth ON teachers.auth = userauth.id WHERE auth = " + String.valueOf(authIndex);
        result = execQuery(query);

        if(result != null){
            try {
                if(result.next()){
                    user = new Teacher();
                    //Rellenar con la info recibida
                    return user;

                }
            } catch (SQLException e) {
                logger.error("ERROR IN SQL QUERY");
                logger.debug(e.getMessage());
            }
        }

        query = "SELECT * FROM admins INNER JOIN userauth ON admins.auth = userauth.id WHERE auth = " + String.valueOf(authIndex);
        result = execQuery(query);

        if(result != null){
            try {
                if(result.next()){
                    user = new Admin();
                    //Rellenar con la info recibida
                    return user;
                }
            } catch (SQLException e) {
                logger.error("ERROR IN SQL QUERY");
                logger.debug(e.getMessage());
            }
        }

        return null;
    }
}