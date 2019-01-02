package entities;

import java.util.ArrayList;

public class Subject {

    private final String name;
    private ArrayList<Grade> grades = new ArrayList<>();
    public Subject(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
