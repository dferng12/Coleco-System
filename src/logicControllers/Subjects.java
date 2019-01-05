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

    public void removeStudentFromSubject(Student student, Subject subject){
        Grades grades = new Grades();
        grades.removeAllGradesFromStudent(student, subject);

        Absences absences = new Absences();
        absences.removeAllAbsencesFromStudent(subject, student);

        daoSubject.removeStudentFromSubject(student, subject);
        subject.removeStudent(student);
    }

    public void removeTeacherFromSubject(Subject subject){
        daoSubject.removeTeacher(subject);
        subject.removeTeacher();
    }
}
