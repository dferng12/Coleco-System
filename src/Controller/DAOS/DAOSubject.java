package Controller.DAOS;

import Model.Student;
import Model.Subject;
import Model.Teacher;
import Controller.Absences;
import Controller.Grades;
import Controller.Students;
import Controller.Teachers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSubject extends DAO{

    public void init(){
        String query  = "CREATE TABLE IF NOT EXISTS subjects (" +
                "name VARCHAR(20) NOT NULL," +
                "teacher varchar(9)," +
                "PRIMARY KEY(name));";
        executeUpdate(query);

        query = "CREATE TABLE IF NOT EXISTS subjects_students(" +
                "id int AUTO_INCREMENT PRIMARY KEY," +
                "subject_name varchar(20) NOT NULL," +
                "dni varchar(9) NOT NULL)";

        executeUpdate(query);

    }

    public void fk(){
        String query = "ALTER TABLE subjects ADD CONSTRAINT fk_subjects_teacher FOREIGN KEY (teacher) REFERENCES teachers(dni)";
        executeUpdate(query);

        query = "ALTER TABLE subjects_students ADD CONSTRAINT fk_subjects_students_subject FOREIGN KEY (subject_name) REFERENCES subjects(name);";
        executeUpdate(query);

        query = "ALTER TABLE subjects_students ADD CONSTRAINT fk_subjects_students_student FOREIGN KEY (dni) REFERENCES students(dni);";
        executeUpdate(query);

    }

    public Subject getSubject(String name){
        String query = "SELECT * FROM subjects WHERE name = \"" + name + "\"";
        ResultSet resultSet = execQuery(query);

        if (resultSet == null) {
            return null;
        }

        DAOUser daoUser = new DAOUser();

        try {
            if(resultSet.next()){
                return new Subject(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void getSubjectsFromStudent(Student user) throws SQLException {
        String query = "SELECT * FROM subjects_students " +
                "INNER JOIN students ON subjects_students.dni = students.dni " +
                "INNER JOIN subjects ON subjects_students.subject_name = subjects.name " +
                "WHERE students.dni = \"" + user.getDni().toString() + "\"";

        ResultSet results = execQuery(query);

        if (results != null){
            while(results.next()){
                user.addSubject(fillSubject(results.getString("subjects.name")));
            }
        }

    }

    public void getSubjectsFromTeacher(Teacher user) throws SQLException {
        String query = "SELECT * FROM subjects " +
                "INNER JOIN teachers ON subjects.teacher = teachers.dni " +
                "WHERE teachers.dni = '" + user.getDni().toString() + "';";

        ResultSet results = execQuery(query);

        if (results != null){
            while(results.next()){
                user.addSubject(fillSubject(results.getString("subjects.name")));
            }
        }

    }

    public void addSubject(Subject subject){
        String query = "INSERT INTO subjects (name) VALUES (\"" + subject.getName() +"\");";
        executeUpdate(query);
    }

    public void addTeacherToSubject(Subject subject, Teacher teacher){
        String query = "UPDATE subjects SET teacher = '" + teacher.getDni().toString() + "' WHERE name = '" + subject.getName() + "';";
        executeUpdate(query);
    }

    public void addStudentToSubject(Subject subject, Student student){
        String query = "INSERT INTO subjects_students(subject_name, dni) VALUES('" + subject.getName() + "','" + student.getDni().toString() + "');";
        executeUpdate(query);
    }

    public List<Subject> getAllSubjects() throws SQLException {
        String query = "SELECT * FROM subjects";


        ResultSet resultSet = execQuery(query);

        if (resultSet == null) return new ArrayList<>();

        ArrayList<Subject> subjects = new ArrayList<>();

        while (resultSet.next()){
            subjects.add(fillSubject(resultSet.getString("name")));
        }

        return subjects;
    }

    public void removeStudentFromSubject(Student student, Subject subject){
        String query = "DELETE FROM subjects_students WHERE dni = '" +  student.getDni().toString() + "' AND subject_name = '" + subject.getName() +"';";
        executeUpdate(query);
    }

    public void removeSubject(Subject subject){
        String query = "DELETE FROM subjects WHERE name = '" + subject.getName() + "';";
        executeUpdate(query);
    }

    public void removeTeacher(Subject subject) {
        String query = "UPDATE subjects SET teacher = null WHERE name = '" +subject.getName() + "';";
        executeUpdate(query);
    }

    public Subject fillSubject(String name){
        Grades grades = new Grades();
        Absences absences = new Absences();
        Students students = new Students();
        Teachers teachers = new Teachers();

        Subject subject = getSubject(name);
        grades.getAllGradesFromSubject(subject);
        absences.getAllGradesFromAbsence(subject);
        students.getAllStudentsFromSubject(subject);
        teachers.getTeacherFromSubject(subject);

        return subject;
    }
}
