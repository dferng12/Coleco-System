package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subject {

    private final String name;
    private List<Student> students;
    private Teacher teacher;
    private HashMap<Student, List<Grade>> grades;
    private HashMap<Student, List<Absence>> absences;

    public Subject(String name){
        this.name = name;
        this.students = new ArrayList<>();
        this.grades = new HashMap<>();
        this.absences = new HashMap<>();
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public Teacher getTeacher(){
        return this.teacher;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public void addGrade(Student student, Grade grade){
        this.grades.computeIfAbsent(student, k -> new ArrayList<>());
        this.grades.get(student).add(grade);
    }

    public void addAbsence(Student student, Absence absence){
        this.absences.computeIfAbsent(student, k -> new ArrayList<>());
        this.absences.get(student).add(absence);
    }

    public List<Grade> getGrades(Student student){
        return this.grades.get(student);
    }

    public List<Absence> getAbsences(Student student){
        return this.absences.get(student);
    }

    public Student getStudent(DNI dni){
        for(Student student: students){
           if(student.getHandler().compareTo(dni)){
               return student;
           }
        }

        return null;
    }

    public int computeGrade(Student student){
        int totalGrade = 0;

        for(Grade grade: this.grades.get(student)){
            totalGrade += grade.getValue()*grade.getPercentage();
        }

        for(Absence absence: this.absences.get(absences)){
            totalGrade -= absence.getPenalty();
        }

        return totalGrade;
    }

    @Override
    public String toString(){
        return name;
    }
}
