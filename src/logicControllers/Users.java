package logicControllers;

import entities.AuthInfo;
import entities.Student;
import entities.User;
import logicControllers.DAOS.DAOAuth;
import logicControllers.DAOS.DAOUser;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Users {
    private static Logger logger = Logger.getLogger(AuthController.class);
    private DAOUser daoUser;
    private DAOAuth daoAuth;

    public Users(){
        daoUser = new DAOUser();
        daoAuth = new DAOAuth();
    }

    public void addStudent(Student student, AuthInfo authInfo){
        daoUser.addStudent(student, authInfo.getUser());
        daoAuth.addAuthInfo(authInfo);
    }

    public List<Student> getAllStudents(){
        try {
            return daoUser.getAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public User getUser(String username){
        return daoUser.getUser(username);
    }

}
