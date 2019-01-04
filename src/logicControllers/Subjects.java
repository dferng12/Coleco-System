package logicControllers;

import entities.Student;
import entities.Subject;
import entities.Teacher;
import logicControllers.DAOS.DAOSubject;

import java.sql.SQLException;

public class Subjects {
    private DAOSubject daoSubject;

    public Subjects(){
        this.daoSubject = new DAOSubject();
    }

    public void addTeacherToSubject(Teacher teacher, Subject subject){
        daoSubject.addTeacherToSubject(subject, teacher);
        subject.setTeacher(teacher);
    }

    public void getSubjectsFromTeacher(Teacher teacher){
        try {
            daoSubject.getSubjectsFromTeacher(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSubjectsFromStudent(Student student){
        try {
            daoSubject.getSubjectsFromStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeStudentFromSubject(Subject subject, Student student){
        return;
    }
}
