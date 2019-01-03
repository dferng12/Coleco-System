package logicControllers;

import entities.Student;
import entities.Subject;
import entities.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOSubject extends DAO{

    public DAOSubject(){
        String query  = "CREATE TABLE IF NOT EXISTS subjects (" +
                "name VARCHAR(20) NOT NULL," +
                "teacher varchar(9) NOT NULL," +
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

        try {
            if(resultSet.next()){
                Subject subject = new Subject(resultSet.getString("name"));
                return subject;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void getSubjectsFromStudent(Student user) throws SQLException {
        String query = "SELECT * FROM subjects_students " +
                "INNER JOIN students ON subjects_students.dni = students.dni" +
                "INNER JOIN subjects ON subjects_students.subject_name = subjects.name" +
                "WHERE students.dni = \"" + user.getDni().toString() + "\"";

        ResultSet results = execQuery(query);

        if (results != null){
            while(results.next()){
                Subject subject = new Subject(results.getString("name"));

            }
        }

    }

    public void getSubjectsFromTeacher(Teacher user) throws SQLException {
        String query = "SELECT * FROM subjects " +
                "INNER JOIN teachers ON subjects.teacher = teachers.dni" +
                "WHERE teachers.dni = " + user.getDni().toString();

        ResultSet results = execQuery(query);

        if (results != null){
            DAOUser daoUser = new DAOUser();
            while(results.next()){
                Subject subject = new Subject(results.getString("name"));
                daoUser.getStudentsFromSubject(subject);
                user.addSubject(subject);
            }
        }

    }

    public void addSubject(Subject subject){
        String query = "INSERT INTO subjects (name) VALUES (\"" + subject.getName() +"\");";
        executeUpdate(query);
    }
}
