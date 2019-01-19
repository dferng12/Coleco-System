package Model;

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

    public Subject getSubject(String name){
        for(Subject subject: subjectList){
            if(subject.getName().equals(name)){
                return subject;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Estudiante: " + this.name + " " + this.subname;
    }
}
