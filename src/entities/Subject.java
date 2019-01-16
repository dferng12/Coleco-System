package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subject {

    private SubjectHandler id;
    private final String name;
    private List<Student> students;
    private Teacher teacher;
    private HashMap<Integer, List<Grade>> grades;
    private HashMap<Integer, List<Absence>> absences;

    public Subject(String name){
        this.name = name;
        this.students = new ArrayList<>();
        this.grades = new HashMap<>();
        this.absences = new HashMap<>();
        this.id = new SubjectHandler(this.name);
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

    public List<Student> getStudents(){
        return this.students;
    }

    public void addGrade(Student student, Grade grade){
        this.grades.computeIfAbsent(Integer.parseInt(student.getDni().toString().substring(0,8)), k -> new ArrayList<>());
        this.grades.get(Integer.parseInt(student.getDni().toString().substring(0,8))).add(grade);
    }

    public void addAbsence(Student student, Absence absence){
        this.absences.computeIfAbsent(Integer.parseInt(student.getDni().toString().substring(0,8)), k -> new ArrayList<>());
        this.absences.get(Integer.parseInt(student.getDni().toString().substring(0,8))).add(absence);
    }

    public List<Grade> getGrades(Student student){
        return this.grades.get(Integer.parseInt(student.getDni().toString().substring(0,8))) == null ? new ArrayList<>() : this.grades.get(Integer.parseInt(student.getDni().toString().substring(0,8)));
    }

    public List<Absence> getAbsences(Student student){
        return this.absences.get(Integer.parseInt(student.getDni().toString().substring(0,8))) == null ? new ArrayList<>() : this.absences.get(Integer.parseInt(student.getDni().toString().substring(0,8)));
    }

    public Student getStudent(DNI dni){
        for(Student student: students){
           if(student.getHandler().compareTo(dni)){
               return student;
           }
        }

        return null;
    }

    public double computeGrade(Student student){
        int totalGrade = 0;
        String dni1 = student.getDni().toString().substring(0,8);
        int dni =  Integer.parseInt(dni1);


        for(Grade grade: this.grades.get(dni)){
            totalGrade += grade.getValue()*grade.getPercentage();
        }

        for(Absence absence: this.absences.get(dni)){
            totalGrade -= absence.getPenalty();
        }

        return totalGrade/100.0;
    }

    public void removeStudent(Student student){
        students.remove(student);
        grades.remove(student);
        absences.remove(student);
    }

    public void removeTeacher(){
        this.teacher = null;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }
}
