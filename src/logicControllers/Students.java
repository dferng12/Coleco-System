package logicControllers;

import entities.AuthInfo;
import entities.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Students {

    private DAOUser daoUser;
    private DAOAuth daoAuth;

    public Students(){
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
}
