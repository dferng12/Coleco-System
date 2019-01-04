package entities;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User{
    private List<Subject> subjects;

    public Teacher(){
        this.subjects = new ArrayList<>();
    }

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

    public List<Subject> getSubjects(){
        return this.subjects;
    }


}
