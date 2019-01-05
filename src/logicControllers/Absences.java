package logicControllers;

import entities.Absence;
import entities.Student;
import entities.Subject;
import logicControllers.DAOS.DAOAbsences;

public class Absences {
    private DAOAbsences daoAbsences;

    public Absences(){
        this.daoAbsences = new DAOAbsences();
    }

    public void addAbsence(Subject subject, Student student, Absence absence){
        subject.addAbsence(student, absence);
        daoAbsences.addAbsence(absence);
        daoAbsences.addGradeToSubject(subject, absence, student);
    }

    public void removeAbsenceFromStudent(Subject subject, Student student, Absence absence){
        daoAbsences.deleteAbsencesFromStudent(subject, student, absence);
        daoAbsences.deleteAbsence(absence);
    }

    public void removeAllAbsencesFromStudent(Subject subject, Student student){
        for(Absence absence: subject.getAbsences(student)){
            removeAbsenceFromStudent(subject, student, absence);
        }
    }
}
