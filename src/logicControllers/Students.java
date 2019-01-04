package logicControllers;

import entities.AuthInfo;
import entities.Student;
import entities.Subject;
import logicControllers.DAOS.DAOAuth;
import logicControllers.DAOS.DAOUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Students extends Users{

    public void addStudent(Student student){
        daoAuth.addAuthInfo(student.getAuthInfo());
        daoUser.addStudent(student);
    }

    public List<Student> getAllStudents(){
        try {
            return daoUser.getAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void addStudentToSubject(Student student, Subject subject){
        addStudentToSubject(student, subject);
        subject.addStudent(student);
    }

    public void deleteStudent(Student student){
        return;
    }

}
