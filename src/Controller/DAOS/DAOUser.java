package Controller.DAOS;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUser extends DAO {

    public void init() {
        String query = "CREATE TABLE IF NOT EXISTS students (" +
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
                "name VARCHAR(20) NOT NULL," +
                "subname VARCHAR(45) NOT NULL," +
                "dni VARCHAR(9) NOT NULL," +
                "age INT," +
                "auth varchar(60)," +
                "email VARCHAR(50)," +
                "phoneNumber CHAR(10)," +
                "PRIMARY KEY(dni));";

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
        DAOSubject daoSubject = new DAOSubject();

        if(result != null){
            try {
                if(result.next()){
                    Student user = new Student();
                    fillUserData(result, user);
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
                    Teacher user = new Teacher();
                    fillUserData(result, user);
                    daoSubject.getSubjectsFromTeacher(user);
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
                    fillUserData(result, user);
                    return user;
                }
            } catch (SQLException e) {
                logger.error("ERROR IN SQL QUERY");
                logger.debug(e.getMessage());
            }
        }

        return null;
    }

    public void getTeacherFromSubject(Subject subject){
        String query = "SELECT * FROM teachers INNER JOIN subjects ON teachers.dni = subjects.teacher " +
                "WHERE subjects.name = \"" + subject.getName() + "\"";

        ResultSet resultSet = execQuery(query);

        if(resultSet == null) return;

        try {
            if(resultSet.next()){
                Teacher teacher = new Teacher();
                fillUserData(resultSet, teacher);
                teacher.addSubject(subject);
                subject.setTeacher(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudentsFromSubject(Subject subject) throws SQLException {
        String query = "SELECT * FROM subjects_students " +
                "INNER JOIN students ON subjects_students.dni = students.dni " +
                "INNER JOIN subjects ON subjects_students.subject_name = subjects.name " +
                "WHERE subjects.name = \"" + subject.getName() + "\"";

        ResultSet results = execQuery(query);

        ArrayList<Student> students = new ArrayList<>();

        if (results != null){
            while(results.next()){
                Student student = new Student();
                fillUserData(results, student);
                students.add(student);
            }
        }
        return students;
    }

    public void addStudent(Student student){
        String query = "INSERT INTO students(dni, name, subname, auth) VALUES(\"" + student.getDni().toString() +"\", \""+ student.getName() + "\", \"" +student.getSubname() +"\", \"" + student.getAuthInfo().getUser() + "\");";

        executeUpdate(query);
    }

    public void addTeacher(Teacher teacher){
        String query = "INSERT INTO teachers(dni, name, subname, auth) VALUES (\"" + teacher.getDni().toString() +"\", \""+ teacher.getName() + "\", \"" +teacher.getSubname() +"\", \"" + teacher.getAuthInfo().getUser() + "\");";

        executeUpdate(query);
    }

    public List<Student> getAllStudents() throws SQLException {
        String query = "SELECT * from students;";

        ResultSet resultSet = execQuery(query);

        if (resultSet == null) return new ArrayList<>();

        ArrayList<Student> students = new ArrayList<>();

        while(resultSet.next()){
            Student student = new Student();
            fillUserData(resultSet, student);
            students.add(student);
        }

        return students;
    }

    private void fillUserData(ResultSet resultSet, User user){
        try {
            user.setDni(DNI.createDNI(resultSet.getString("dni")));
            user.setName(resultSet.getString("name"));
            user.setSubname(resultSet.getString("subname"));
            user.setEmail(resultSet.getString("email"));
            user.setAge(resultSet.getInt("age"));
            user.setPhoneNumber(resultSet.getInt("phonenumber"));

            String query = "SELECT * FROM userauth WHERE username = '" + resultSet.getString("auth") + "';";
            ResultSet auth = execQuery(query);

            if(auth.next()){
                user.setAuthInfo(new AuthInfo(resultSet.getString("auth"), auth.getString("password")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Teacher> getAllTeachers() throws SQLException {
        String query = "SELECT * FROM teachers;";

        ResultSet resultSet = execQuery(query);

        if(resultSet == null) return new ArrayList<>();

        ArrayList<Teacher> teachers = new ArrayList<>();

        while(resultSet.next()){
            Teacher teacher = new Teacher();
            fillUserData(resultSet, teacher);
            teachers.add(teacher);
        }

        return teachers;
    }

    public void removeStudent(Student student){
        String query = "DELETE FROM students WHERE dni = '"  + student.getDni().toString() + "';";
        executeUpdate(query);
    }

    public void removeTeacher(Teacher teacher){
        String query = "DELETE FROM teachers WHERE dni = '"  + teacher.getDni().toString() + "';";
        executeUpdate(query);
    }

    public void updateStudent(Student student){
        String query = "UPDATE students SET dni = '" + student.getDni().toString() + "', name = '" + student.getName() + "', subname = '" + student.getSubname() + "', auth = '" + student.getAuthInfo().getUser() + "' WHERE dni = '" + student.getDni().toString() + "'";

        executeUpdate(query);
    }

}
