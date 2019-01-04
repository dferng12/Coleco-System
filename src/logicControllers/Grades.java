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

}
