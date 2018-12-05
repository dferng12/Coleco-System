package entities;

import java.util.List;

public class Student extends User{
    private List<Subject> subjectList;

    @Override
    public List<Subject> getSubjects() {
        return subjectList;
    }

}
