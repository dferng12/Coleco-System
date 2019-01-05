package logicControllers;

import entities.Grade;
import entities.Student;
import entities.Subject;
import logicControllers.DAOS.DAOGrades;

public class Grades {
    private DAOGrades daoGrades;

    public Grades(){
        this.daoGrades = new DAOGrades();
    }

    public void addGrade(Subject subject, Student student, Grade grade){
        subject.addGrade(student, grade);
        daoGrades.addGrade(grade);
        daoGrades.addGradeToSubject(subject, grade, student);
    }

    public void removeGradeFromStudent(Student student, Subject subject, Grade grade){
        daoGrades.deleteGradeFromStudent(subject,student,grade);
        daoGrades.deleteGrade(grade);
    }

    public void removeAllGradesFromStudent(Student student, Subject subject){
        for(Grade grade: subject.getGrades(student)){
            removeGradeFromStudent(student, subject, grade);
        }
    }
}
