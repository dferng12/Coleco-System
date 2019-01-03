package logicControllers;

import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUser extends DAO {

    public DAOUser() {
        String query = "CREATE TABLE IF NOT EXISTS students (" +
                "username VARCHAR(20) NOT NULL," +
                "name VARCHAR(20) NOT NULL," +
                "subname VARCHAR(45) NOT NULL," +
                "dni VARCHAR(9) NOT NULL," +
                "age INT," +
                "email VARCHAR(50) ," +
                "phoneNumber CHAR(10)," +
                "auth varchar(60)," +
                "PRIMARY KEY(dni));";

        executeUpdate(query);

        query = "CREATE TABLE IF NOT EXISTS teachers (" +
                "username VARCHAR(20) NOT NULL," +
                "name VARCHAR(20) NOT NULL," +
                "subname VARCHAR(45) NOT NULL," +
                "dni VARCHAR(9) NOT NULL," +
                "age INT," +
                "auth varchar(60)," +
                "email VARCHAR(50)," +
                "phoneNumber CHAR(10)," +
                "PRIMARY KEY(dni));";

        executeUpdate(query);

        query = "CREATE TABLE IF NOT EXISTS admins (" +
                "username VARCHAR(20) PRIMARY KEY," +
                "auth varchar(60));";

        executeUpdate(query);
    }

    public void fk(){
        String query = "ALTER TABLE students ADD CONSTRAINT fk_students FOREIGN KEY (auth) REFERENCES userauth(username)";
        executeUpdate(query);

        query = "ALTER TABLE teachers ADD CONSTRAINT fk_teachers FOREIGN KEY (auth) REFERENCES userauth(username)";
        executeUpdate(query);

        query = "ALTER TABLE admins ADD CONSTRAINT fk_admins FOREIGN KEY (auth) REFERENCES userauth(username)";
        executeUpdate(query);
    }

    public User getUser(String authName){
        String query = "SELECT * FROM students INNER JOIN userauth ON students.auth = userauth.username WHERE auth = \"" + authName + "\"";

        ResultSet result = execQuery(query);

        if(result != null){
            try {
                if(result.next()){
                    DAOSubject daoSubject = new DAOSubject();
                    Student user = new Student();
                    user.setName(result.getString("name"));
                    user.setDni(DNI.createDNI(result.getString("dni")));
                    daoSubject.getSubjectsFromStudent(user);
                    return user;

                }
            } catch (SQLException e) {
                logger.error("ERROR IN SQL QUERY");
                logger.debug(e.getMessage());
            }
        }

        query = "SELECT * FROM teachers INNER JOIN userauth ON teachers.auth = userauth.username WHERE auth = \"" + authName + "\"";
        result = execQuery(query);

        if(result != null){
            try {
                if(result.next()){
                    Teacher user = new Teacher(result.getString("dni"));
                    //Rellenar con la info recibida
                    return user;

                }
            } catch (SQLException e) {
                logger.error("ERROR IN SQL QUERY");
                logger.debug(e.getMessage());
            }
        }

        query = "SELECT * FROM admins INNER JOIN userauth ON admins.auth = userauth.username WHERE auth = \"" + authName + "\"";
        result = execQuery(query);

        if(result != null){
            try {
                if(result.next()){
                    Admin user = new Admin();
                    //Rellenar con la info recibida
                    return user;
                }
            } catch (SQLException e) {
                logger.error("ERROR IN SQL QUERY");
                logger.debug(e.getMessage());
            }
        }

        return null;
    }

    public void getTeacher(Subject subject){
        String query = "SELECT * FROM teachers INNER JOIN subjects ON teachers.dni = subjects.teacher" +
                "WHERE subject.name = \"" + subject.getName() + "\"";

        ResultSet resultSet = execQuery(query);

        if(resultSet == null) return;

        try {
            if(resultSet.next()){
                Teacher teacher = new Teacher(resultSet.getString("teachers.dni"));
                teacher.setName(resultSet.getString("teachers.name"));
                subject.setTeacher(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getStudentsFromSubject(Subject subject) throws SQLException {
        String query = "SELECT * FROM subjects_students " +
                "INNER JOIN students ON subjects_students.dni = students.dni" +
                "INNER JOIN subjects ON subjects_students.subject_name = subjects.name" +
                "WHERE subject.name = \"" + subject.getName() + "\"";

        ResultSet results = execQuery(query);

        if (results != null){
            while(results.next()){
                Student student = new Student();
                student.setDni(DNI.createDNI(results.getString("dni")));
                student.setName(results.getString("name"));
                subject.addStudent(student);
            }
        }
    }

    public void addStudent(Student student, String username){
        String query = "INSERT INTO students(dni, name, subname, username) VALUES(\"" + student.getDni().toString() +"\", \""+ student.getName() + "\", \"" +student.getSubname() +"\", \"" + username + "\");";

        executeUpdate(query);
    }

    public List<Student> getAllStudents() throws SQLException {
        String query = "SELECT * from students;";

        ResultSet resultSet = execQuery(query);

        if (resultSet == null) return new ArrayList<>();

        ArrayList<Student> students = new ArrayList<>();

        while(resultSet.next()){
            Student student = new Student();
            student.setName(resultSet.getString("name"));
            student.setSubname(resultSet.getString("students.subname"));
            student.setDni(DNI.createDNI(resultSet.getString("dni")));
            students.add(student);
        }

        return students;
    }
}
