package entities;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{
    private List<Subject> subjectList;

    public Student(){
        this.subjectList = new ArrayList<>();
    }

    @Override
    public List<Subject> getSubjects() {
        return subjectList;
    }

    public void addSubject(Subject subject){
        this.subjectList.add(subject);
    }

    @Override
    public String toString() {
        return this.name + " " + this.subname + " " + this.dni;
    }
}
