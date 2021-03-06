package Controller;

import Model.Subject;
import Model.Teacher;

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

    public void deleteTeacher(Teacher teacher){
        daoUser.removeTeacher(teacher);
    }

    public void getTeacherFromSubject(Subject subject) {
        daoUser.getTeacherFromSubject(subject);
    }
}
