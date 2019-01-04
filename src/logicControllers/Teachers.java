package logicControllers;

import entities.AuthInfo;
import entities.Student;
import entities.Subject;
import entities.Teacher;
import logicControllers.DAOS.DAOAuth;
import logicControllers.DAOS.DAOUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Teachers extends Users{

    public void addTeacher(Teacher teacher){
        daoAuth.addAuthInfo(teacher.getAuthInfo());
        daoUser.addTeacher(teacher);
    }

    public List<Teacher> getAllTeachers(){
        try {
            return daoUser.getAllTeachers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void deleteTeacher(){
        return;
    }

}
