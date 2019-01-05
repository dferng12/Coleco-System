package logicControllers;

import entities.Student;
import entities.Subject;

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
        daoUser.removeStudent(student);
        daoAuth.deleteAuthInfo(student.getAuthInfo());
    }

    public void updateStudentData(Student student){
        daoUser.updateStudent(student);
    }

    public void getAllStudentsFromSubject(Subject subject){
        try {
            List<Student> students = daoUser.getStudentsFromSubject(subject);
            for(Student student: students){
                subject.addStudent(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
